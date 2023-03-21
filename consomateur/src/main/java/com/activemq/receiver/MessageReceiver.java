package com.activemq.receiver;



import java.io.Console;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
 
public class MessageReceiver {
 
    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"
 
    // Name of the queue we will receive messages from
    private static String subject = "JCG_QUEUE";
 
    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("amqp://localhost:5672");
        Connection connection = connectionFactory.createConnection("niang","passer123");
        connection.start();
 
        // Creating session for seding messages
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
 
        // Create a session
	      Session s = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

	      // Create a queue
	      Destination destination = session.createQueue("MyFirstQueue");

	      // Create a consumer specific to queue
	      MessageConsumer consumer = session.createConsumer(destination);

	      Console c = System.console();
	      String response;
	      do {      	
	         // Receive the message
	         Message msg = consumer.receive();
	         response = ((TextMessage) msg).getText();

	         System.out.println("Received = "+response);

	      } while (!response.equalsIgnoreCase("Quit"));

	      // Close the connection
        connection.close();
    }
}
