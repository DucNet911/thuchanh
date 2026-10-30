package WS;

import java.net.URL;
import java.util.List;

public class SumArray {

    public static int solve(List<Integer> numbers) {
        long sum = 0;

        for (int number : numbers) {
            sum += number;
        }

        // Server nhận int; báo lỗi nếu tổng vượt giới hạn int.
        return Math.toIntExact(sum);
    }

    public static void main(String[] args) throws Exception {
        String studentCode = "B23DCVT095";
        String qCode = "a9bSLb7z";

        DataService service = new DataService(new URL(
                "http://36.50.135.242:2221/DataService?wsdl"));

        SoapDataService port = service.getSoapDataServicePort();

        List<Integer> data = port.getData(studentCode, qCode);
        int result = solve(data);

        System.out.println("Du lieu: " + data);
        System.out.println("Tong: " + result);

        port.submitDataInt(studentCode, qCode, result);

        System.out.println("Da gui ket qua.");
    }
}