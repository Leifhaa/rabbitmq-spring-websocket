package com.example.demo.client;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

public class StompClientsSimulation implements Runnable {

    public StompClientsSimulation() {
    }

    public ConsumerSessionHandler consumerSessionHandler;

    public void run() {
        //Create a producer session which should post alot of messages on the exchange
        ProducerSessionHandler producerSession = new ProducerSessionHandler();
        CreateStompClient(producerSession);

        //Create a consumer session which should subscribe to messages on the exchange
        consumerSessionHandler = new ConsumerSessionHandler();
        CreateStompClient(consumerSessionHandler);
    }


    private void CreateStompClient(StompSessionHandler sessionHandler){
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new StringMessageConverter());

        stompClient.connect("ws://localhost:8080/chat/websocket", sessionHandler);
    }
}
