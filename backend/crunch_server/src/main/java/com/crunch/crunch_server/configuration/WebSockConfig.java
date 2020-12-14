package com.crunch.crunch_server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSockConfig implements WebSocketMessageBrokerConfigurer{


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/channel");
        config.setApplicationDestinationPrefixes("/app","/private");
        config.setUserDestinationPrefix("/private");
    }

    //stomp websocket connection endpoint
    //://localhost:3000/ws-stomp
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*")
                .withSockJS();
    }



    // <!-- websocket handler -->
	// <bean id="echoHandler" class="mentor.socketHandler.EchoHandler" />
 
	// <websocket:handlers>
	// 	<websocket:mapping handler="echoHandler" path="/echo" />
	// 	<websocket:handshake-interceptors>
	//          <bean class="org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor"/>
	//       </websocket:handshake-interceptors>
 
	//       <websocket:sockjs/>
	//  </websocket:handlers>
}
