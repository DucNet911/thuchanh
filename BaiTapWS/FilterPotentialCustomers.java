package WS;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FilterPotentialCustomers {

    public static List<Customer> solve(List<Customer> customers) {
        List<Customer> result = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer.getTotalSpent() > 5000
                    && customer.getPurchaseCount() >= 5) {

                result.add(customer);
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        String studentCode = "B23DCVT095";
        String qCode = "bxMWQ6mq";

        ObjectService service = new ObjectService(new URL(
                "http://36.50.135.242:2221/ObjectService?wsdl"));

        SoapObjectService port =
                service.getSoapObjectServicePort();

        List<Customer> data =
                port.requestListCustomer(studentCode, qCode);

        List<Customer> result = solve(data);

        for (Customer customer : result) {
            System.out.println(
                    customer.getCustomerId()
                    + " | " + customer.getPurchaseCount()
                    + " | " + customer.getTotalSpent());
        }

        port.submitListCustomer(studentCode, qCode, result);

        System.out.println("Da gui ket qua.");
    }
}