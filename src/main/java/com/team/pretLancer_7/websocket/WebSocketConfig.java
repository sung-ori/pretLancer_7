// package com.team.pretLancer_7.websocket;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
// import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
// import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// @Configuration
// @EnableWebSocketMessageBroker
// public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
    
//     @Override
//     public void registerStompEndpoints(final StompEndpointRegistry registry) {
//         registry.addEndpoint("/websocket")
//                 .setHandshakeHandler(new UserHandshakeHandler())
//                 .setAllowedOriginpatterns("*")
//                 .withSockJS();
//     }

//     @Override
//     public void configureMessageBroker(final MessageBrokerRegistry registry) {
//         registry.enableSimpleBroker("/sub"); // 3번 우체통
//         registry.setApplicationDestinationPrezixes("/pub"); // 2번 집배원
//     }
// }
