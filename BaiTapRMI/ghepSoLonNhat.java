package BaiTapRMI;
import WS.DataService;
import WS.SoapDataService;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;


public class ghepSoLonNhat {
    public static void main(String[] args) throws Exception {
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();

        String studentCode = "B23DCVT095";
        String qCode = "U1QlBuI6";

        List<Integer> data = port.getData(studentCode, qCode);
        List<String> numbers = new ArrayList<>();

        for (int number : data) {
            numbers.add(String.valueOf(number));
        }

        numbers.sort((a, b) -> (b + a).compareTo(a + b));
        String result = String.join("", numbers);

        if (!numbers.isEmpty() && numbers.get(0).equals("0")) {
            result = "0";
        }

        port.submitDataString(studentCode, qCode, result);
    }
}