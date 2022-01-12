package com.example.demo.client;

import java.util.Scanner;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;


public class StompClient {
    public static void main(String[] args) {

        //Create a producer session which should post alot of messages on the exchange
        ProducerSessionHandler producerSession = new ProducerSessionHandler();
        CreateStompClient(producerSession);

        //Create a consumer session which should subscribe to messages on the exchange
        ConsumerSessionHandler consumerSession = new ConsumerSessionHandler();
        CreateStompClient(consumerSession);


        new Scanner(System.in).nextLine(); // Don't close immediately.
    }

    private static void CreateStompClient(StompSessionHandler sessionHandler){
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new StringMessageConverter());

        stompClient.connect("ws://localhost:8080/chat/websocket", sessionHandler);
    }
}
