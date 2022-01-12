package com.example.demo;

import com.example.demo.client.ClientApp;
import com.example.demo.client.StompClientsSimulation;
import com.example.demo.server.SpringRabbitmqWebsocketApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringRabbitmqWebsocketApplication.class)
class SpringRabbitmqWebsocketApplicationTests {

	@Test
	void testRabbitMq() throws InterruptedException {

	}

}
