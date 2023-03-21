package com.activemq.sender;
 
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
 
public class MessageSender {
     
    //URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
     
    // default broker URL is : tcp://localhost:61616"
    private static String subject = "JCG_QUEUE"; // Queue Name.You can create any/many queue names as per your requirement. 
     
    public static void main(String[] args) throws JMSException {        
        // Getting JMS connection from the server and starting it
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

	      // Create a queue
	      Destination destination = session.createQueue("MyFirstQueue");

	      // Create a producer specific to queue
	      MessageProducer producer = session.createProducer(destination);

	      Scanner input = new Scanner(System.in);
	      String response;
	      do {
	         System.out.println("Enter message: ");
	         response = input.nextLine();
	         // Create a message object
	         TextMessage msg = session.createTextMessage(response);

	         // Send the message to the queue
	         producer.send(msg);

	      } while (!response.equalsIgnoreCase("Quit"));
	      input.close();
         
        //System.out.println("JCG printing@@ '" + message.getText() + "'");
        connection.close();
    }
}
