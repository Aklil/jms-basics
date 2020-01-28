import javax.jms.JMSException;

public class StartupService {

    public static void main(String[] args) throws JMSException {

        String destinationName = "test.q";
        String data = "JMS test data";

        AirtimeService airtimeService = new AirtimeService();
        airtimeService.process(destinationName, data);

        BillingService billingService = new BillingService();
        billingService.receiveData(destinationName);

    }

}
