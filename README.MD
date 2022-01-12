#How to run:
## 1. Set the following environment according to the rabbitMQ server (alternatively, just change property file):
RABBITMQ_USERNAME
RABBITMQ_PASSWORD
RABBITMQ_HOST
RABBITMQ_VHOST 

## 2. Start the spring boot application (SpringRabbitmqWebsocketApplication)
Either start it by an IDE inside the server package or enter target folder and run the command:
``java -jar demo-0.0.1-SNAPSHOT.jar
``


## 3. Start the Stomp clients simulator (ClientApp)
Either start it from an IDE inside the client package or enter the target folder and run the command:
``
java -cp demo-0.0.1-SNAPSHOT.jar -Dloader.main=com.example.demo.client.ClientApp org.springframework.boot.loader.PropertiesLauncher
``


# The error
Eventually the following error will occur (note that it might take anywhere from few seconds to few minutes):
``Received : There is no current subscription with tag 'T_...'.``
It will cause the websocket to break.


![Alt text](images/img.png?raw=true "Title")


(Please also note that the following error is not the issue:)

``
No handler for: MESSAGE...
``


###Credits
The client implementation is inspired by baeldung (https://www.baeldung.com/websockets-api-java-spring-client)
