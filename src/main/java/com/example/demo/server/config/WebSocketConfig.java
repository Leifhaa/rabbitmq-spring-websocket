package com.example.demo.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${spring.rabbitmq.username}")
    private String rabbitmqUser;

    @Value("${spring.rabbitmq.password}")
    private String rabbitmqPw;

    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${spring.rabbitmq.virtual-host}")
    private String vhost;


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config.enableStompBrokerRelay("/exchange")
                .setRelayHost(rabbitmqHost)
                .setRelayPort(61613)
                .setClientLogin(rabbitmqUser)
                .setClientPasscode(rabbitmqPw)
                .setVirtualHost(vhost)
                //See 26.4.9 Connect to Broker (https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/html/websocket.html) on why system is required.
                .setSystemLogin(rabbitmqUser)
                .setSystemPasscode(rabbitmqPw);
        config.setApplicationDestinationPrefixes("/app");

        //Enable dot seperator instead of slashes in paths
        config.setPathMatcher(new AntPathMatcher("."));
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }
}
