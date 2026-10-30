package BaiTapRMI;
import RMI.DataService;
import java.lang.String;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Locale;


public class thongKeDaySo {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService service = (DataService) registry.lookup("RMIDataService");

        String studentCode = "B23DCVT095";
        String qCode = "K5KQMsOR";

        String data = (String) service.requestData(studentCode, qCode);
        String[] parts = data.split(",");
        int n = parts.length;
        double[] numbers = new double[n];
        double sum = 0;

        for (int i = 0; i < n; i++) {
            numbers[i] = Double.parseDouble(parts[i].trim());
            sum += numbers[i];
        }

        double average = sum / n;
        double sumSquared = 0;

        for (double number : numbers) {
            double difference = number - average;
            sumSquared += difference * difference;
        }

        double stddev = Math.sqrt(sumSquared / n);

        Arrays.sort(numbers);
        int index = (int) Math.ceil(n * 0.95) - 1;
        double p95 = numbers[index];

        String result = String.format(Locale.US,
                "average=%.2f;stddev=%.2f;p95=%.2f", average, stddev, p95);

        service.submitData(studentCode, qCode, result);
    }
}