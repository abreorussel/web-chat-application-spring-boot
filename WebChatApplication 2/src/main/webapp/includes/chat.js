/**
 * 
 */	
	const url = 'http://localhost:8081';
	
	let stompClient;
	
	function connectToChat(chatId){
		console.log("connecting to chat...");
		let socket = new SockJS(url);
		stompClient = Stomp.over(socket);
		stompClient.connect({},function(){
			console.log("connected to: "+ frame);
			stompClient.subscribe("/topic/messages/" + chatId, function(){
				let data = JSON.parse(response.body);
				console.log(data);
			});
		});
	}
	
	function sendMsg(from,text){
		stompClient.send("/app/chat/"+chatId, {}, JSON.stringify(
			{id:1,
					chatId:1234,
					senderId:"A101",
					recipientId:"S101",
					recipientName:"Smith",
					content:"Hello from Ajit!"		
			}
		)	);
	}