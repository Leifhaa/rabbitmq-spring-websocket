package com.example.demo.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

/*
This handler will simply send very frequent messages to an topic.
 */
public class ProducerSessionHandler extends StompSessionHandlerAdapter {

    private Logger logger = LogManager.getLogger(ConsumerSessionHandler.class);

    private static String topic = "/exchange/amq.topic";

    private static String name = "producer";

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
        session.subscribe(topic + "/chat", this);

        while (true){
            session.send(topic + "/chat", getSampleMessage());
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Got an exception on session handler: " + name, exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
        logger.info("Received : " + msg.getText() + " from : " + msg.getFrom());
    }

    /**
     * A sample message instance.
     * @return instance of <code>Message</code>
     */
    private String getSampleMessage() {
        return "Hello from " + name;
    }
}
