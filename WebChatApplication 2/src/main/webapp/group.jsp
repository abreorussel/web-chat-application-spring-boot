<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>Web Chat App</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body,
        html {
            height: 100%;
            margin: 0;
            background: #7F7FD5;
            background: -webkit-linear-gradient(to right, #91EAE4, #86A8E7, #7F7FD5);
            background: linear-gradient(to right, #91EAE4, #86A8E7, #7F7FD5);
        }
        
        .user_member_body {
        	text-align: center;
        	color: white;
        	overflow-y: auto;
        }
        
        .headingUser {
        	top: 0;
        	text-align: left;
        	color: yellow;
        }
        
        .userList {
        	margin-top: auto;
            margin-bottom: auto;
        }
        
        .user_card {
        	height: 500px;
            border-radius: 15px !important;
            background-color: rgba(0, 0, 0, 0.4) !important;
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
            margin-top: 15px;
            margin-bottom: 5px;
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

        .msg_sender {
            position: absolute;
            left: 0;
            bottom: 43px;
            color: rgb(255, 251, 0);
            font-size: 12px;
        }

        .msg_time {
            position: absolute;
            width: 90px;
            left: 0;
            bottom: -15px;
            color: rgba(255, 255, 255, 0.5);
            font-size: 10px;
        }

        .msg_time_send {
            position: absolute;
            width: 90px;
            right: 0;
            bottom: -15px;
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

        .action_menu ul li:hover {
            cursor: pointer;
            background-color: rgba(0, 0, 0, 0.2);
        }

        /* The popup form - hidden by default */
        .form-popup {
            display: none;
            position: fixed;
            margin-left: auto;
            margin-right: auto;
            border: 3px solid #f1f1f1;
            z-index: 9;
        }

        /* Add styles to the form container */
        .form-container {
            max-width: 300px;
            padding: 10px;
            background-color: white;
        }

        /* Full-width input fields */
        .form-container input[type=text],
        .form-container input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            border: none;
            background: #f1f1f1;
        }

        /* When the inputs get focus, do something */
        .form-container input[type=text]:focus,
        .form-container input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        /* Set a style for the submit/login button */
        .form-container .btn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            border: none;
            cursor: pointer;
            width: 100%;
            margin-bottom: 10px;
            opacity: 0.8;
        }

        /* Add a red background color to the cancel button */
        .form-container .cancel {
            background-color: red;
        }

        /* Add some hover effects to buttons */
        .form-container .btn:hover,
        .open-button:hover {
            opacity: 1;
        }

        .groups {
            line-height: 80px;
            width: 200px;
            font-size: 20px;
            border-radius: 12px;
            background-color: white;
            color: black;
            border: 2px solid #008CBA;
            font-family: tahoma;
            margin-top: 1px;
            margin-right: 2px;
            position: absolute;
            top: 0;
            left: 270px;

        }

        .groups:hover {
            background-color: #008CBA;
            color: white;

        }
        
        .logout {
        	line-height: 80px;
            width: 200px;
            font-size: 20px;
            border-radius: 12px;
            background-color: red;
            color: black;
            border: 2px solid #008CBA;
            font-family: tahoma;
            margin-top: 1px;
            margin-right: 2px;
            position: absolute;
            top: 0;
            right: 270px;
        }
        
        .logout:hover {
            background-color: white;
            color: red;

        }

        .create {
            line-height: 80px;
            width: 200px;
            font-size: 20px;
            border-radius: 12px;
            background-color: white;
            color: black;
            border: 2px solid #008CBA;
            font-family: tahoma;
            margin-top: 1px;
            margin-right: 2px;
            position: absolute;
            top: 0;
            right: 270px;

        }

        .create:hover {
            background-color: #008CBA;
            color: white;
        }

        @media(max-width: 576px) {
            .contacts_card {
                margin-bottom: 15px !important;
            }
        }
    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</head>

<body>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Chat</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
            integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
            integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css"
            href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
        <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="stomp.js"> </script>
    </head>
    <!--Coded With Love By Mutiullah Samim-->

    <body onload="getMyUserId()">
        <div class="container-fluid h-100">
            <div class="row justify-content-center h-100">
            		
                    <button type="submit" class="groups" onclick="backToChat()">MY Chats</button>
                <form action="/logout" >
                    <button type="submit" class="logout" >LOGOUT</button>
               	</form>
                
                <div class="col-md-4 col-xl-3 chat">
                    <div class="card mb-sm-3 mb-md-0 contacts_card">
                        
                        <input id="groupNameInput" type="text">Enter Your Group Name</input>
						<button onclick="createGroup()">Create Group</button>
                        <div class="card-header">
                            <div class="input-group">
                                <button onclick="getGroups()">Get All Groups</button>
                            </div>
                        </div>
                        <div class="card-body contacts_body">

                        </div>
                        <div class="card-footer"></div>
                    </div>
                </div>
                <div class="col-md-8 col-xl-6 chat">
                	<div id = "userHeading" class = "headingUser">
                	</div>
                	<!-- <h2 id = "userHeading" class = "headingUser"></h2> -->
                    <div class="card">
                        <div class="card-header msg_head">
                            <div class="d-flex bd-highlight">
                                <div class="img_cont">
                                    <img src="contact.png" class="rounded-circle user_img">
                                </div>
                                <div class="user_info">
                                    <span id="chattingWith"> Web Chat</span>
                                    <select id="mySelect">
									    <!-- <option value = "Apple">Apple</option> -->
									    
									  </select>
									<button onclick="addMember()">Add User</button>
									
									<button onclick = "leaveGroup()" style="background-color:#ff6347;text-align:center;position:absolute;right: 40px;">Leave</button>
                                </div>
                            </div>
                        </div>
                        <div class="card-body msg_card_body">


                        </div>
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
                <div class = "col-md-4 col-xl-2 userList">
                	
                	
                	<div class = "card mb-sm-3 mb-md-0 user_card">
                		<div>
                			<h2 id = "memberHeading" style="color:white;text-align:center;font-family:'Copperplate',Fantasy">Members</h2>
                		</div>
                		<div class= "user_member_body">
                		
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
//         = ${user.userId};
        var chat_Id;
        var toUserName;
        var userName;
//         = "${user.userName}";
        var otherUser;
        var userAppend;
        var groupName;

        

        function backToChat(){
		location.href = "/getChatPage";
		}

        $(document).ready(function () {
            getMembersList();
        });


        /* $(document).ready(function () {
            $("#sendMsg").click(function () {
                var msg = $("#inputMsg").val();
                if (msg.length > 0) {
                    sendMsg(MY_ID, msg, chat_Id);
                    $("#inputMsg").val('');
                }
            });

        }); */
        
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
				    url: '/groupChat/attachment/'+chat_Id,
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
		
	

        function addMyId() {
            MY_ID = $("#myId").val();
            getUser(MY_ID);
        }

        function getMyUserId() {
            var url = "/user/getMyUserId";
            fetch(url).then((response) => {
                return response.json();
            }).then((data) => {
                console.log("MyUserID:-" + data.userId);
                MY_ID = data.userId;
                userName = data.userName; 
             
				loggedAs(data.userFullName);

            }).catch((err) => {
                console.log("error occured at getMyUserId()");
            })

        }


        function getUserById(toUserId) {
            var url = "/user/getUserById/" + toUserId;
            fetch(url).then((response) => {
                return response.json();
            }).then((data) => {

                toUserName = data.fullName;
              

            }).catch((err) => {
                console.log("error occured at getUserById()");
            })

        }

// -----------------------------------------------------------------------------------------------------
        function createGroup(){
			let groupNameInput = $("#groupNameInput").val();
			console.log(groupNameInput);
	
			fetch('/group/add/' + userName + "/" + groupNameInput, {
				method: "POST",
			})
			.then(response => response.json())
			.then(json => {
				console.log(json);
				if(json.groupName == null){
					alert("Group Already Exists !");
				}else{
					alert("Group Created !");
				}
			})
			.then(() => console.log("Group Created"))
		}
        
        
        function leaveGroup() {
			
        	getUser(MY_ID);
			var url = "/group/remove-member/"+groupName+"/"+userName;
			
			fetch(url, {
    			method: "PUT",
    		})
    		.then(response => response.json())
    		.then(json => console.log(json));
			console.log("removed user "+userName+" from "+groupName);
			window.location.reload();
			
		}

        function getMembersList() {
        	console.log("getting contacts");
    		//getMyUserId();
    		fetch('/user/getAllUsers').then((response)=>{
    			return response.json();
    		}).then((data)=>{
    			console.log(data);
    			
    			let userNameInput = document.getElementById("mySelect");
    			for(var i=0;i<data.length;i++){
    				
    				let user=data[i];
    				var option = document.createElement("option");

                    if(user.userId == MY_ID) {
                        continue;
                    } else {
                        option.text = user.userName;
    				    userNameInput.add(option);
                    }
    			}
    			
    		}).catch((err)=>{
    			console.log("error occured getAllContacts()");
    		})
        }
        
        
        function addMember(){
	
        	//let userNameInput = $("#userNameInput").val();
        	let userNameInput = document.getElementById("mySelect");
        	let selectedUser = userNameInput.options[userNameInput.selectedIndex].text;
    		console.log(selectedUser);
			console.log(groupName);
    		fetch("/group/add-member/"+groupName+ "/" +selectedUser, {
    			method: "PUT",
    		})
    		.then(response => response.json())
    		.then(json => {
    			if(json.groupName == null){
    				alert("Member Already Exists !");
    			}else{
    				displayMembers(json.groupId);
    				alert("Member Added !");
    			}
    			
    		});
		}
         
        // ours
        function getUser(userId) {
            var url = "/user/getUserById/" + userId + "";

            fetch(url)
                .then(res => res.json())
                .then(data => userName = data.userName)
                .then(() => console.log(userName))
        }

        //ours
        async function getUserForReturn(userId) {
            var url = "/user/getUserById/" + userId + "";

            await fetch(url)
                .then(res => res.json())
                .then(data => otherUser = data.userName )
                .then(() => console.log(otherUser))

            return otherUser;
        }

        // test
        async function getUserForResponse(userId) {
            var url = "/user/getUserById/" + userId + "";

            await fetch(url)
                .then(res => res.json())
                .then(data => userAppend = data.userFullName )
                .then(() => console.log(userAppend))

            return userAppend;
        }


        var currentGroupId;

        //ours
        function getGroups() {
            console.log("getting groups");
            console.log(userName);

            fetch("/user/get-all-groups/" + userName)
                .then(res => res.json())
                .then(data => {
                    $(".contacts_body").empty();
                    for (var i = 0; i < data.length; i++) {

                        var group = data[i];

                        var groupInfo = document.createElement("input");
                        
                        groupInfo.setAttribute("type", "button");
                        groupInfo.setAttribute("value", group.groupName);
                        //var connectMethod="connect("+MY_ID+","+user.userId+")"
                        console.log(group.groupId);
                        var connectMethod="connectGroup("+MY_ID+","+group.groupId+")"
                        groupInfo.setAttribute("onclick",connectMethod);
                        groupInfo.setAttribute("class", "btn btn-lg btn-primary btn-block");
                        /* if(user.userId==MY_ID){  
                        continue;
                        } */
                        $(".contacts_body").append(groupInfo);
                    }
                }).then(() => console.log(":("))	
        }
        
        
        
        //logged in as
        function loggedAs(userName){
        	
        	$(".headingUser").empty();
        	var userInfo = document.createElement("h2");
        	var text = document.createTextNode("Logged in as "+userName);
        	userInfo.appendChild(text);
        	$(".headingUser").append(userInfo);
            	
  
           }
        
        function displayMembers(groupId){
        	 var groupResult = getGroup(groupId);

             groupResult.then(function(result) {
            	 var url = "/group/get-members/"+result;
                 
             	
             	fetch(url)
             		.then(res => res.json())
                     .then(data => {
                     	$(".user_member_body").empty();
                     	for(let i =0 ; i<data.length; i++){
                     		
                         	var memberInfo = document.createElement("h4");
                         	var text = document.createTextNode(data[i].userFullName);
                         	memberInfo.appendChild(text);
                         	//memberInfo.setAttribute("value" , data[i].userName);
                         	console.log(data[i].userName);
                         	$(".user_member_body").append(memberInfo);
                     	}
                     	
                     })
                     .then(() => console.log("fetched users"))
               
             });
   
            }

                
        
        
        
        function connectGroup(myId, groupId) {
            //getUserById(toUserId);
            //var url = "http://localhost:8081/getConversation/" + myId + "/" + toUserId + "";

            disconnect();
			
            
            
            var groupResult = getGroup(groupId);
            console.log(groupResult);

            groupResult.then(function(result) {
                console.log(result);

                var chatHeader = result;
                groupName = result;
	            document.getElementById("chattingWith").textContent = chatHeader;
	            displayMembers(groupId);
            });
            

            console.log(myId, groupId)
            //getUserById(toUserId);
           /*  fetch(url).then((response) => {
                return response.json();
            }).then((data) => {
                console.log(data); */
                //chat_Id = data.conversationId;
				chat_Id = groupId;
	
	               //var chatHeader = "Chat With " + toUserName;
				
	               //connectToChat(data.conversationId);
	               connectToChat(chat_Id);
	               $(".msg_card_body").empty();
	               getGroupMessageHistory(chat_Id);


            //})
            /* .catch((err) => {
                console.log("error occured at connect()");
                console.log(err);
            }) */

        }
        
        async function getGroup(groupId) {
            var url = "/group/getById/" + groupId;

            await fetch(url)
                .then(res => res.json())
                .then(data => groupName = data.groupName)
                .then(() => console.log(groupName))

            return groupName;
        }
        
        
        function getGroupMessageHistory(chat_Id) {
            var url = "/getGroupMessages/" + chat_Id + "";
            console.log(chat_Id)

            fetch(url).then((response) => {
                return response.json();
            }).then((data) => {


                for (var i = 0; i < data.length; i++) {

                    var message = data[i];

                    appendOldMessages(message);
                }
            }).catch((err) => {
                console.log("error occured at getAllMessages()");
            })

        }

// -----------------------------------------------------------------------------------------------------


        function getAllMessageHistory(chat_Id) {
            var url = "/getAllMessages/" + chat_Id + "";
            console.log(chat_Id)

            fetch(url).then((response) => {
                return response.json();
            }).then((data) => {


                for (var i = 0; i < data.length; i++) {

                    var message = data[i];

                    appendOldMessages(message);
                }
            }).catch((err) => {
                console.log("error occured at getAllMessages()");
            })

        }

        function appendOldMessages(data) {
            var respMsg = data.messageContent;
           
            var dateStr = data.sentAt;
            
            var res = dateStr.substring(0, 16);

            var respAttach = data.attachmentPath;
		
		    if(respAttach!=null){
			    respAttach = `<div><a target="_blank" href='`+respAttach+`'>`+ respAttach.substring(respAttach.lastIndexOf("/")+1) +`</div></a>`;
		    }
            
			//var dateStr = de.getYear() + ":" + de.getMonth() + ":" + de.getDate() + ":" + de.getHours() + ":" + de.getMinutes();
            var msgFromSelf = `<div class="d-flex justify-content-end mb-4">
                                    <div class="msg_cotainer_send"><div>`+
                                    respMsg +
                                    (respAttach ??	"")
                                    + `</div><span class="msg_time_send">` + res + `</span>
                                    </div>
                                </div>`;
            var msgFromOth = `<div class="d-flex justify-content-start mb-4">
            	
                <div class="msg_cotainer"><div>`+
                    `<span class="msg_sender">` + data.messageAuthor + `</span>`
                    + 
                    respMsg +
                    (respAttach ??	"")
                    + `</div><span class="msg_time">` + res + `</span>
                </div>
            </div>`;

            var msg = "";
            if (MY_ID == data.messageAuthorId) {
                msg = msgFromSelf;
            } else {
                msg = msgFromOth;
            }
            $(".msg_card_body").append(msg);             
        }

        function connect(myId, toUserId) {
            getUserById(toUserId);
            var url = "/getConversation/" + myId + "/" + toUserId + "";
            console.log(myId, toUserId)
            getUserById(toUserId);
            fetch(url).then((response) => {
                return response.json();
            }).then((data) => {
                console.log(data);
                chat_Id = data.conversationId;


                var chatHeader = "Chat With " + toUserName;
                document.getElementById("chattingWith").textContent = chatHeader;
                connectToChat(data.conversationId);
                $(".msg_card_body").empty();
                getAllMessageHistory(chat_Id);


            })/* .catch((err) => {
                console.log("error occured at connect()");
                console.log(err);
            }) */

        }

        function getAllGroups() {
            console.log("getting groups");
//             getMyUserId();
			getUser(MY_ID);
            console.log(MY_ID);

            console.log(userName);
            fetch("/get-all-groups/" + userName).then((response) => {
                return response.json();
            }).then((data) => {
                console.log(data);
                $(".contacts_body").empty();
                for (var i = 0; i < data.length; i++) {

                    var group = data[i];

                    var groupInfo = document.createElement("input");
                    groupInfo.setAttribute("type", "button");
                    groupInfo.setAttribute("value", group.groupName);
                    /* var connectMethod="connect("+MY_ID+","+user.userId+")"
                    groupInfo.setAttribute("onclick",connectMethod); */
                    groupInfo.setAttribute("class", "btn btn-lg btn-primary btn-block");
                    /* if(user.userId==MY_ID){  
                    continue;
                    } */
                    $(".contacts_body").append(groupInfo);

                }

            }).catch((err) => {
                console.log("error occured getAllGroups()");
            })
        }


        let stompClient;
        function connectToChat(chatId) {
            console.log("connecting to chat...");
            let socket = new SockJS(url);
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log("connected to: " + frame);
                stompClient.subscribe("/topic/messages/" + chatId, function (response) {
                    let data = JSON.parse(response.body);
                    console.log("Response From Server :");
                    console.log(data);
                    appendResponse(data, data.content);
                });
            });
        }

        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect(function() {
                     console.log('disconnected...');
                });
            }
        }

        function sendMsg(from, text, convoId) {
            console.log("Sending msg ...");
            stompClient.send("/app/chat/group/" + convoId, {}, JSON.stringify(
                {
                    chatId: convoId,
                    author: from,
                    content: text
                }
            ));
        }

        function appendResponse(data, respMsg) {

            var d = new Date();

            var respAttach = data.filePath;
		
		    if(respAttach!=null){
			    respAttach = `<div><a target="_blank" href='`+respAttach+`'>`+ respAttach.substring(respAttach.lastIndexOf("/")+1) +`</div></a>`;
		    }
        	
            var dateStr = d.getHours() + ":" + d.getMinutes();
            //var dateStr = d.getTime();
			//var dateStr = d.getYear() + ":" + d.getMonth() + ":" + d.getDate() + "T" + d.getHours() + ":" + d.getMinutes();
			 
            var othUser = othUser = getUserForResponse(data.author);
                console.log(othUser);

            othUser.then(function(result) {
                    console.log(result);

                    var msgFromSelf = `<div class="d-flex justify-content-end mb-4">
                    <div class="msg_cotainer_send"><div>`+
                    respMsg +
                    (respAttach ??	"")
                     + `</div><span class="msg_time_send">` + dateStr + `</span>
                    </div>
                </div>`;

            var msgFromOth = `<div class="d-flex justify-content-start mb-4">
                    <div class="msg_cotainer"><div>`+
                        `<span class="msg_sender">` + result + `</span>`
                         + 
                        respMsg +
                        (respAttach ??	"")
                        + `</div><span class="msg_time">` + dateStr + `</span>
                    </div>
                </div>`;

            var msg = "";

            if (MY_ID == data.author) {
                console.log(data.author + " author id");
                msg = msgFromSelf;
             } else {
                 msg = msgFromOth;
             }
                $(".msg_card_body").append(msg);
            });
        }


        // function appendResponse(data, respMsg) {

        //     var d = new Date();
        //     var dateStr = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();

        //     var othUser;
        //     var appendOthUser;

        //     var msgFromSelf = `<div class="d-flex justify-content-end mb-4">
        //             <div class="msg_cotainer_send">`+
        //             respMsg
        //              + `<span class="msg_time_send">` + dateStr + `</span>
        //             </div>
        //         </div>`;

        //     var msgFromOth = `<div class="d-flex justify-content-start mb-4">
        //             <div class="msg_cotainer">`+
        //                 `<span class="msg_sender">` + appendOthUser + `</span>`
        //                  +
        //                 respMsg
        //                 + `<span class="msg_time">` + dateStr + `</span>
        //             </div>
        //         </div>`;

        //     var msg = "";

        //     if (MY_ID == data.author) {
        //         console.log(data.author + " author id");
        //         msg = msgFromSelf;
        //     } else {
        //         othUser = getUserForResponse(data.author);
        //         console.log(othUser);

        //         othUser.then(function(result) {
        //             console.log(result);

        //             appendOthUser = result;
        //         });
        //         msg = msgFromOth;
        //     }
        //     $(".msg_card_body").append(msg);
        // }
    </script>
</body>

</html>