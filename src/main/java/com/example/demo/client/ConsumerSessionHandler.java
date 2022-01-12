package com.example.demo.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is an implementation for <code>StompSessionHandlerAdapter</code>.
 * Once a connection is established, We subscribe to /topic/messages and
 * send a sample message to server.
 *
 * @author Kalyan
 */
public class ConsumerSessionHandler extends StompSessionHandlerAdapter {

    private Logger logger = LogManager.getLogger(ConsumerSessionHandler.class);

    private static String topic = "/exchange/amq.topic";

    private static String name = "consumer";

    private int messageCounter = 0;

    private StompSession session;

    private StompSession.Subscription subscription;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());

        this.session = session;
        subscribe();
    }

    private void subscribe(){
        subscription = session.subscribe(topic + "/chat", this);
    }

    private void unsubscribe(){
        subscription.unsubscribe();
        subscription = null;
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Got an exception on session handler: " + name, exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }


    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Object msg = payload;
        logger.info("Received : " + payload);
        synchronized (this){
            messageCounter++;
            if (messageCounter > 10){
                unsubscribe();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscribe();
                messageCounter = 0;
            }
        }
    }

}