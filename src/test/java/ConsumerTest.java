import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsumerTest {

    @Test
    public void testProducer(){
        Producer producer = new Producer();

        String queueName = "basics.q";
        String payload = "JMS Hello World";

        producer.send(queueName, payload);

        Consumer consumer = new Consumer();

        String message = consumer.receiveMessage(queueName);

        assertEquals(payload, message);

    }
}
