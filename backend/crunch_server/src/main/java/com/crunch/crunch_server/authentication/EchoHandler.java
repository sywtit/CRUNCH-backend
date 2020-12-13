package com.crunch.crunch_server.authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EchoHandler extends TextWebSocketHandler{
    // //login list
    // List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
    // //1 by 1
    // // 1대1
	// Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>();
    
    // //success to connect to server
	// @Override
	// public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	// 	sessions.add(session);
		
	// 	// String senderEmail = getEmail(session);
	// 	// userSessionsMap.put(senderEmail , session);
	// }
}
