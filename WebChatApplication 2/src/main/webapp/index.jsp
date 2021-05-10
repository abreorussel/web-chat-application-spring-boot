<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>

<title>Web Chat App</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
body, html {
	height: 100%;
	margin: 0;
	background: #7F7FD5;
	background: -webkit-linear-gradient(to right, #91EAE4, #86A8E7, #7F7FD5);
	background: linear-gradient(to right, #91EAE4, #86A8E7, #7F7FD5);
}

.chat {
	margin-top: auto;
	margin-bottom: auto;
}

.card {
	height: 500px;
	border-radius: 15px !important;
	background-color: rgba(0, 0, 0, 0.4) !important;
}

.contacts_body {
	padding: 0.75rem 0 !important;
	overflow-y: auto;
	white-space: nowrap;
}

.msg_card_body {
	overflow-y: auto;
	height: auto;
}

.card-header {
	border-radius: 15px 15px 0 0 !important;
	border-bottom: 0 !important;
}

.card-footer {
	border-radius: 0 0 15px 15px !important;
	border-top: 0 !important;
}

.container {
	align-content: center;
}

.search {
	border-radius: 15px 0 0 15px !important;
	background-color: rgba(0, 0, 0, 0.3) !important;
	border: 0 !important;
	color: white !important;
}

.search:focus {
	box-shadow: none !important;
	outline: 0px !important;
}

.type_msg {
	background-color: rgba(0, 0, 0, 0.3) !important;
	border: 0 !important;
	color: white !important;
	height: 60px !important;
	overflow-y: auto;
}

.type_msg:focus {
	box-shadow: none !important;
	outline: 0px !important;
}

.attach_btn {
	border-radius: 15px 0 0 15px !important;
	background-color: rgba(0, 0, 0, 0.3) !important;
	border: 0 !important;
	color: white !important;
	cursor: pointer;
}

.send_btn {
	border-radius: 0 15px 15px 0 !important;
	background-color: rgba(0, 0, 0, 0.3) !important;
	border: 0 !important;
	color: white !important;
	cursor: pointer;
}

.search_btn {
	border-radius: 0 15px 15px 0 !important;
	background-color: rgba(0, 0, 0, 0.3) !important;
	border: 0 !important;
	color: white !important;
	cursor: pointer;
}

.contacts {
	list-style: none;
	padding: 0;
}

.contacts li {
	width: 100% !important;
	padding: 5px 10px;
	margin-bottom: 15px !important;
}

.active {
	background-color: rgba(0, 0, 0, 0.3);
}

.user_img {
	height: 40px;
	width: 40px;
	border: 1.5px solid #f5f6fa;
}

.user_img_msg {
	height: 40px;
	width: 40px;
	border: 1.5px solid #f5f6fa;
}

.img_cont {
	position: relative;
	height: 40px;
	width: 40px;
}

.img_cont_msg {
	height: 40px;
	width: 40px;
}

.online_icon {
	position: absolute;
	height: 15px;
	width: 15px;
	background-color: #4cd137;
	border-radius: 50%;
	bottom: 0.2em;
	right: 0.4em;
	border: 1.5px solid white;
}

.offline {
	background-color: #c23616 !important;
}

.user_info {
	margin-top: auto;
	margin-bottom: auto;
	margin-left: 15px;
}

.user_info span {
	font-size: 20px;
	color: white;
}

.user_info p {
	font-size: 10px;
	color: rgba(255, 255, 255, 0.6);
}

.video_cam {
	margin-left: 50px;
	margin-top: 5px;
}

.video_cam span {
	color: white;
	font-size: 20px;
	cursor: pointer;
	margin-right: 20px;
}

.msg_cotainer {
	margin-top: auto;
	margin-bottom: auto;
	margin-left: 10px;
	border-radius: 25px;
	background-color: #82ccdd;
	padding: 10px;
	position: relative;
}

.msg_cotainer_send {
	margin-top: auto;
	margin-bottom: auto;
	margin-right: 10px;
	border-radius: 25px;
	background-color: #78e08f;
	padding: 10px;
	position: relative;
}

.msg_time {
	position: absolute;
	left: 0;
	bottom: -15px;
	width: auto;
	color: rgba(255, 255, 255, 0.5);
	font-size: 10px;
}

.msg_time_send {
	position: absolute;
	right: 0;
	bottom: -15px;
	width: auto;
	color: rgba(255, 255, 255, 0.5);
	font-size: 10px;
}

.msg_head {
	position: relative;
}

#action_menu_btn {
	position: absolute;
	right: 10px;
	top: 10px;
	color: white;
	cursor: pointer;
	font-size: 20px;
}

.action_menu {
	z-index: 1;
	position: absolute;
	padding: 15px 0;
	background-color: rgba(0, 0, 0, 0.5);
	color: white;
	border-radius: 15px;
	top: 30px;
	right: 15px;
	display: none;
}

.action_menu ul {
	list-style: none;
	padding: 0;
	margin: 0;
}

.action_menu ul li {
	width: 100%;
	padding: 10px 15px;
	margin-bottom: 5px;
}

.action_menu ul li i {
	padding-right: 10px;
}

.mybtn {
	-webkit-border-radius: 13;
	-moz-border-radius: 13;
	border-radius: 22px;
	border: none;
	font-family: Georgia;
	color: #ffffff;
	font-size: 12px;
	background: #007bff;
	padding: 5px 10px 5px 10px;
	text-decoration: bold;
}

.mybtn:hover {
	background: #1a64d4;
	text-decoration: none;
}

.sendbtn {
	-webkit-border-radius: 13;
	-moz-border-radius: 13;
	border-radius: 3px;
	border: none;
	font-family: Georgia;
	color: #ffffff;
	font-size: 12px;
	background: #007bff;
	padding: 5px 10px 5px 10px;
	text-decoration: bold;
}

.sendbtn:hover {
	background: #1a64d4;
	text-decoration: none;
}

.action_menu ul li:hover {
	cursor: pointer;
	background-color: rgba(0, 0, 0, 0.2);
}

@media ( max-width : 576px) {
	.contacts_card {
		margin-bottom: 15px !important;
	}
}
</style>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!DOCTYPE html>
<html>
<head>
<title>Chat</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="stomp.js"> </script>
</head>
<body onload="javascript:getMyUserId()">
	<div class="container-fluid h-100">
		<div class="row justify-content-center h-100">
			<div class="col-md-4 col-xl-3 chat">
				<div class="card mb-sm-3 mb-md-0 contacts_card">
					<form action="/logout">
						<button class="mybtn" type="submit">LogOut</button>
					</form>
					<div class="card-header">
						<div class="input-group">
							&nbsp&nbsp&nbsp
							<button class="mybtn" onclick="getAllContacts()">Get All
								Contacts</button>
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

							<button class="mybtn" onClick="getAllGroups()" type="button"
								class="groups">MY GROUPS</button>



						</div>
					</div>
					<div class="card-body contacts_body"></div>
					<div class="card-footer"></div>
				</div>
			</div>
			<div class="col-md-8 col-xl-6 chat">
				<div class="card">
					<div class="card-header msg_head">
						<div class="d-flex bd-highlight">
							<div class="img_cont">
								<img src="contact.png" class="rounded-circle user_img">
							</div>
							<div class="user_info">
								<span id="chattingWith"> Web Chat</span>

							</div>
						</div>
					</div>
					<div id="msgCardBody" class="card-body msg_card_body"></div>
					<div class="card-footer">
						<div class="input-group">

							<textarea id="inputMsg" class="form-control type_msg"
								placeholder="Type your message..."></textarea>
							<input class="sendbtn" type="file" id="attachment"
								class="form-control" name="attachment" />
							<button class="sendbtn" id="sendMsg">Send</button>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
/**
 * 
 */	
	const url = '/chat';
	var MY_ID;
	var chat_Id;
	var toUserName;
	var stompClient;
	var subscribedChatsId=[];
	var scrollHeight=0;
	
	
	$(document).ready(function(){
		
		
		$("#sendMsg").click(function(){
			var msg=$("#inputMsg").val();
			
			if($("#attachment")[0].files.length > 0)
			{
				console.log("with attachment")
				var attachmentData = new FormData();
				attachmentData.append('chatId',chat_Id)
				attachmentData.append('author',MY_ID)
				attachmentData.append('content',msg)
				attachmentData.append('attachment',$("#attachment")[0].files[0])
				
				$.ajax({
				    method: 'POST',
				    data: attachmentData,
				    url: '/chat/attachment/'+chat_Id,
				    cache: false,
				    contentType: false,
				    processData: false,
				    success: function(data){
				        
				    }
				});
				$("#inputMsg").val('');
				$("#attachment").val("")
			}
			else{
				
				if(msg.length>0){
					sendMsg(MY_ID, msg,chat_Id);
					$("#inputMsg").val('');
				}
			}
		});
		
	});
	
	function getAllGroups(){
		location.href = "/getGroupChatPage";
	}
	
	async function getMyUserId(){
		var url="/user/getMyUserId";
		await fetch(url).then((response)=>{
			return response.json();
		}).then((data)=>{
			
			console.log("MyUserID:-" +data.userId);
			MY_ID=data.userId;
	
			
		}).catch((err)=>{
			console.log("error occured at getMyUserId()");
		})
	
	}
	
	
async function getUserById(toUserId){
		var url="/user/getUserById/"+toUserId;
 		await fetch(url).then((response)=>{
			return response.json();
		}).then((data)=>{
			
			toUserName=data.userFullName;
			
			
		}).catch((err)=>{
			console.log("error occured at getUserById()");
		})
	
	}
	
	async function getAllMessageHistory(chat_Id){
		var url="/message/getAllMessages/"+chat_Id+"";
		console.log(chat_Id)
		
		await fetch(url).then((response)=>{
			return response.json();
		}).then((data)=>{
			
			scrollHeight=0;
             for(var i=0;i<data.length;i++){
				
				var message=data[i];
				
				appendOldMessages(message);
				
             }
             
             console.log("scrollHeight="+scrollHeight);
			 $(".msg_card_body").animate({ scrollTop: scrollHeight }, 100); 
			 
		}).catch((err)=>{
			console.log("error occured at getAllMessages()");
		})
		
		return;
	}	
	
function appendOldMessages(data){
		var respMsg=data.messageContent;
		var respAttach = data.attachmentPath;
		
		if(respAttach!=null){
			respAttach = `<div><a target="_blank" href='`+respAttach+`'>`+ respAttach.substring(respAttach.lastIndexOf("/")+1) +`</div></a>`;
		}
		
		var dateStr=data.sentAt;
		var msgFromSelf = `<div class="d-flex justify-content-end mb-4">
								<div class="msg_cotainer_send"><div>`+
									respMsg +
									(respAttach ??	"")								
									+`</div><span class="msg_time_send">`+dateStr+`</span>
								</div>
							</div>`;
							
		var msgFromOth =`<div class="d-flex justify-content-start mb-4">
		<div class="msg_cotainer"><div>`+
		respMsg+
		(respAttach ??	"")
		+`</div><span class="msg_time">`+dateStr+`</span>
		</div>
	</div>`;
	
	
	
	var msg ="";
	if(MY_ID == data.messageAuthorId )	{
		msg=msgFromSelf;
	}else{
		msg=msgFromOth;
	}
		$(".msg_card_body").append(msg);
		scrollHeight = scrollHeight + 150;
		
		
		
	}
	async function connect(myId,toUserId,userDisplayPicture){
		await getUserById(toUserId);
		
		var url="/getConversation/"+myId+"/"+toUserId+"";
		console.log(myId,toUserId)
		await fetch(url).then((response)=>{
			return response.json();
		}).then((data)=>{
			console.log(data);
			chat_Id=data.conversationId;
			
			console.log(toUserName);
			
			var chatHeader="Chat With "+toUserName;
			document.getElementById("chattingWith").innerHTML=chatHeader;
			document.getElementsByClassName("user_img")[0].src="/src/main/resources/static/images/" + toUserId+ "/userProfile/" + userDisplayPicture;
			if(!subscribedChatsId.includes(chat_Id)){
				console.log(subscribedChatsId);
				connectToChat(data.conversationId);
			}
		
			$(".msg_card_body").empty();
			
			
			getAllMessageHistory(chat_Id);
			
			
			
			
			
			
		}).catch((err)=>{
			console.log("error occured at connect()");
		})
		
	}
		
	async function getAllContacts(){
		console.log("getting contacts");
		 await getMyUserId();
		await fetch("/user/getAllUsers").then((response)=>{
			return response.json();
		}).then((data)=>{
			console.log(data);
			$(".contacts_body").empty();
			for(var i=0;i<data.length;i++){
				
				var user=data[i];
				
				var userinfo= document.createElement("input");
				userinfo.setAttribute("type","button");
				userinfo.setAttribute("value",user.userFullName);
				var connectMethod="connect("+MY_ID+","+user.userId+",'"+user.userDisplayPicture+"')"
				userinfo.setAttribute("onclick",connectMethod);
				//userinfo.onclick=connect(MY_ID,user.userId);
				userinfo.setAttribute("class","btn btn-lg btn-primary btn-block");
					if(user.userId==MY_ID){  
					continue;
					}
					$(".contacts_body").append(userinfo);
					
			
			}
			
		}).catch((err)=>{
			console.log("error occured getAllContacts()");
		})
		
	}
	
	
	
	
	
	function connectToChat(chatId){
		console.log("connecting to chat...");
		let socket = new SockJS(url);
		stompClient = Stomp.over(socket);
		stompClient.connect({},function(frame){
			console.log("connected to: "+ frame);
			stompClient.subscribe("/topic/messages/" + chatId, function(response){
				let data = JSON.parse(response.body);
				console.log("Response From Server :");
				console.log(data);
				appendResponse(data, data.content);
				
			});
		});
		subscribedChatsId.push(chatId);
	}
	
	function sendMsg(from,text,convoId){
		console.log("Sending msg ...");
		stompClient.send("/app/chat/"+convoId, {}, JSON.stringify(
			{
					chatId:convoId,
					author:from,
					content:text		
			}
		)	);
		
		
	}
	
	
	
	function appendResponse(data,respMsg){
		
		var d = new Date();
		var dateStr=d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		
		var respAttach = data.filePath;
		
		if(respAttach!=null){
			respAttach = `<div><a target="_blank" href='`+respAttach+`'>`+ respAttach.substring(respAttach.lastIndexOf("/")+1) +`</div></a>`;
		}
		
		var msgFromSelf = `<div class="d-flex justify-content-end mb-4">
								<div class="msg_cotainer_send"><div>`+
									respMsg+
									(respAttach ??	"")
									+`</div><span  class="msg_time_send">`+dateStr+`</span>
								</div>
							</div>`;
		var msgFromOth =`<div class="d-flex justify-content-start mb-4">
		<div class="msg_cotainer"><div>`+
		respMsg+
		(respAttach ??	"")
		+`</div><span   class="msg_time_send">`+dateStr+`</span>
		</div>
	</div>`;
		
	var msg ="";
	
	if(MY_ID == data.author )	{
		console.log(data.author+" author id");
		msg=msgFromSelf;
	}else{
		msg=msgFromOth;
	}
		$(".msg_card_body").append(msg);
		scrollHeight = scrollHeight + 150;
		 $(".msg_card_body").animate({ scrollTop: scrollHeight }, 100); 
		
		
		
		
		
	}
</script>
</body>
</html>
