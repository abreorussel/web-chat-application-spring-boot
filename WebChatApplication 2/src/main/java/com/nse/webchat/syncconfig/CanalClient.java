package com.nse.webchat.syncconfig;

import java.net.InetSocketAddress;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.Message;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.client.*;
 

@Slf4j
@Component
public class CanalClient {
	
	private static final String THREAD_NAME_PREFIX="canalStart-";

	
	/**
	* initialization
	* Single thread startup canal client
	*/
	@PostConstruct
	public void init() {
		
	// You need to start a new thread to execute canal service
		Thread initThread = new CanalStartThread();
		initThread.setName(THREAD_NAME_PREFIX);
		initThread.start();
	}
	
	
	// Service threads - Used for Initiating the Thread
	public class CanalStartThread extends Thread {
	@Override
		public void run() {
		
			// Setting the connections using data from canal.properties
	       CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),  
	               11111), "example", "", "");  
	       int batchSize = 1000;  
	       try {  
	           connector.connect();  
	           connector.subscribe(".*\\..*");  
	           connector.rollback();    
	           while (true) {  
	               Message message = connector.getWithoutAck(batchSize); // Get the specified amount of data  
	               long batchId = message.getId();  
	               int size = message.getEntries().size();  
	               if (batchId == -1 || size == 0) {  
	                   try {  
	                       Thread.sleep(1000);  
	                   } catch (InterruptedException e) {  
	                       e.printStackTrace();  
	                   }  
	               } else {  
	                   printEntry(message.getEntries());  
	               }  
	               connector.ack(batchId); // Submit confirmation  
	               // connector.rollback(batchId); // Processing failed, roll back data  
	           }  
	       } finally {  
	           connector.disconnect();  
	       }  
		
		}
	}
	
		// Logging data onto the console
	   private static void printEntry( List<Entry> entrys) {  
	       for (Entry entry : entrys) {  
	           if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {  
	               continue;  
	           }  
	           RowChange rowChage = null;  
	           try {  
	               rowChage = RowChange.parseFrom(entry.getStoreValue());  
	           } catch (Exception e) {  
	               throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),  
	                       e);  
	           }  
	           EventType eventType = rowChage.getEventType();  
	           System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",  
	                   entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),  
	                   entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),  
	                   eventType));  
	           
	           
	 
	           for (RowData rowData : rowChage.getRowDatasList()) {  
	               if (eventType == EventType.DELETE) {  
	                   redisDelete(rowData.getBeforeColumnsList());  
	               
	               } else if (eventType == EventType.INSERT) {  
	                   redisInsert(rowData.getAfterColumnsList());  
	               
	               } else if (eventType == EventType.UPDATE){  
	                   System.out.println("-------> before");  
	                   printColumn(rowData.getBeforeColumnsList());  
	                   System.out.println("-------> after");
	                   //System.out.println(rowData.getAfterColumnsCount());
	                   redisUpdate(rowData.getAfterColumnsList());  
	                   printColumn(rowData.getAfterColumnsList());  
	               
	               } else {
	            	   
	            	   printColumn(rowData.getAfterColumnsList());
	            	   redisGet(rowData.getAfterColumnsList());
	            	   //printColumn(rowData.getAfterColumnsList());
	               }
	           }  
	       }  
	   }  
	
	   
	   private static void printColumn( List<Column> columns) {  
	       for (Column column : columns) {  
	           System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());  
	       }  
	   }  
	 
	   // Inserting the data from MySQL to Redis in batches
	  private static void redisInsert( List<Column> columns){
	      JSONObject json=new JSONObject();
	      for (Column column : columns) {  
	          json.put(column.getName(), column.getValue());  
	       }  
	      if(columns.size()>0){
	          RedisUtil.stringSet(columns.get(0).getName()+ ":"+ columns.get(0).getValue(),json.toJSONString());
	      }
	   }
	  
	  // Getting the data from MySQL to Redis in batches
	  private static void redisGet (List<Column> columns) {
		  
		  JSONObject json = new JSONObject();
		  for (Column column : columns) {
			  
			  json.put(column.getName(), column.getValue());
		  }
		  
		  if (columns.size() > 0) {
			  
			  RedisUtil.stringGet("user_id:"+columns.get(0));
		  }
		  
	  }	
	  
	 
	  // Updating the data from MySQL to Redis in batches
	  private static  void redisUpdate( List<Column> columns){
	      JSONObject json=new JSONObject();
	      for (Column column : columns) {  
	          json.put(column.getName(), column.getValue());  
	       }  
	      if(columns.size()>0){
	          RedisUtil.stringSet(columns.get(0).getName()+ ":"+ columns.get(0).getValue(),json.toJSONString());
	      }
	  }
	 
	  // Deleting the data from MySQL to Redis in batches
	   private static  void redisDelete( List<Column> columns){
	       JSONObject json=new JSONObject();
	          for (Column column : columns) {  
	              json.put(column.getName(), column.getValue());  
	           }  
	          if(columns.size()>0){
	              RedisUtil.delKey(columns.get(0).getName()+ ":"+columns.get(0).getValue());
	          }
	   }   
	
	
	}
