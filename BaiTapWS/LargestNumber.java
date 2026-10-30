package WS;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LargestNumber {

    public static String solve(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }

        List<String> parts = new ArrayList<>();

        for (int number : numbers) {
            if (number < 0) {
                throw new IllegalArgumentException(
                        "Bai ghep so yeu cau so khong am.");
            }

            parts.add(String.valueOf(number));
        }

        // a đứng trước b nếu a + b lớn hơn b + a.
        parts.sort((a, b) -> (b + a).compareTo(a + b));

        // Tất cả phần tử đều bằng 0.
        if (parts.get(0).equals("0")) {
            return "0";
        }

        return String.join("", parts);
    }

    public static void main(String[] args) throws Exception {
        String studentCode = "B23DCVT095";
        String qCode = "U1Q1BuI6";

        DataService service = new DataService(new URL(
                "http://36.50.135.242:2221/DataService?wsdl"));

        SoapDataService port = service.getSoapDataServicePort();

        List<Integer> data = port.getData(studentCode, qCode);
        String result = solve(data);

        System.out.println("Du lieu: " + data);
        System.out.println("Ket qua: " + result);

        port.submitDataString(studentCode, qCode, result);

        System.out.println("Da gui ket qua.");
    }
}