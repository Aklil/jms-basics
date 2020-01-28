import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.awt.*;

public class AirtimeService {

    public void process(String destinationName, String data) throws JMSException {
        // simulating process
        System.out.println("Processing Data in Airtime: " + data);

        //notify activemq
        notify(destinationName, data);
    }


    public void notify(String destinationName, String payload) throws JMSException {
        // TODO
        // step 1 - get the connection factory
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

        // step 2- create connection from connection factory
        Connection connection = connectionFactory.createConnection();

        // step 3 -create session from connection
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // step 4 - create the destination
        Destination destination = session.createQueue(destinationName);

        // step 5 - create messageProducer from session
         MessageProducer producer =  session.createProducer(destination);

        // step 6 - create the messagge
        TextMessage message = session.createTextMessage(payload);

        producer.send(message);

        connection.close();
    }

}
