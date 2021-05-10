package com.nse.webchat.syncconfig;


import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class RedisUtil<T> {
    private static String ip = "localhost";
    private static int port = 6379;
    private static int timeout = 10000;
    private static JedisPool pool = null;
    // expire date
    protected static int  expireTime = 3*60;
 
 
    static {
        JedisPoolConfig config = new JedisPoolConfig();
//                 config.setMaxTotal(1024);//The maximum number of connections
//                 config.setMaxIdle(200);//The maximum number of idle instances
//                 config.setMaxWaitMillis(10000);//The maximum time for the connection to wait for the connection pool, milliseconds
//                 config.setTestOnBorrow(true);//When Borrow an instance, whether to advance the date operation
 
        pool = new JedisPool(config, ip, port, timeout);
 
    }
 
    //Get Jedis connection
    public static synchronized Jedis getJedis() {
        if (pool != null) {
            return pool.getResource();
        } else {
            return null;
        }
    }
 
         //Close the redis connection
    public static void close(final Jedis redis) {
        if (redis != null) {
            redis.close();
        }
    }
    

    // Checking whether the key exists or not
	public static boolean existKey(String key) {
	  
		Jedis jedis = null;
		boolean isBroken = false;
	  
		try {
			
			jedis = getJedis();
			
			// Selects the DB
		    jedis.select(0);
		    return jedis.exists(key);
		} 
		
		catch (Exception e) {
	      
			isBroken = true;
		} 
		
		finally {
			
	      close(jedis);
		}
		
	  return false;
	}

	
	
	// Deleting the user for the key
	
	public static void delKey(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
	  
		try {
			
	      jedis = getJedis();
	      jedis.select(0);
	      jedis.del(key);
	      
		} 
		
		catch (Exception e) {
	      
			isBroken = true;
		} 
		
		finally {
		  close(jedis);
	  }
		
	}
	
	// Selecting the user for the key
	public static String stringGet(String key) {
		  Jedis jedis = null;
		  boolean isBroken = false;
		  String lastVal = null;
		  
		  try {
		      jedis = getJedis();
		      jedis.select(0);
		      lastVal = jedis.get(key);
		      jedis.expire(key, expireTime);
		  } 
		  
		  catch (Exception e) {
			  
			  isBroken = true;
		  } 
		  
		  finally {
			  
			  close(jedis);
		  }
		  
	  return lastVal;
	}
	
	// Inserting the user for the key
	public static String stringSet(String key, String value) {
		  Jedis jedis = null;
		  boolean isBroken = false;
		  String lastVal = null;
		  
		  try {
			  
		      jedis = getJedis();
		      jedis.select(0);
		      lastVal = jedis.set(key, value);
		      jedis.expire(key, expireTime);
		  } 
		  
		  catch (Exception e) {
			  
		      e.printStackTrace();
		      isBroken = true;
		  } 
		  
		  finally {
			  close(jedis);
		  }
		  
	  return lastVal;
	}
	
	// Inserting the user in hash format for the key
//	public static void hashSet(String key, String field, String value) {
//		  boolean isBroken = false;
//		  Jedis jedis = null;
//		  
//		  try {
//			  
//		      jedis = getJedis();
//		      if (jedis != null) {
//		          jedis.select(0);
//		          jedis.hset(key, field, value);
//		          jedis.expire(key, expireTime);
//		      }
//		  } 
//		  
//		  catch (Exception e) {
//			  
//			  isBroken = true;
//		  } 
//		  
//		  finally {
//			  close(jedis);
//		  }
//	}
}