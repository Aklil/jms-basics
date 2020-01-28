import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class BillingService {

    Connection connection;
    MessageConsumer consumer;

    public void receiveData(String destinationName) throws JMSException {

        setupConnection(destinationName);

        Message message = consumer.receive(2000);
        TextMessage textMessage = (TextMessage) message;
        String data = textMessage.getText();

        connection.close();
        process(data);
    }

    private void process(String data){
        // simulate process
        System.out.println("Processing data in billing service" + data);
    }

    private void setupConnection(String destinationName) throws JMSException {
        // step 1 - get the connection factory
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

        // step 2- create connection from connection factory
        connection = connectionFactory.createConnection();

        // step 3 -create session from connection
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // step 4 - create the destination
        Destination destination = session.createQueue(destinationName);

        // step5 - create the consumer
        consumer = session.createConsumer(destination);

        connection.start();
    }

}
