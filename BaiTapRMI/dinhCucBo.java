package BaiTapRMI;
import RMI.DataService;
import java.lang.String;
import java.lang.reflect.Array;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;



public class dinhCucBo {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService service = (DataService) registry.lookup("RMIDataService");

        String studentCode = "B23DCVT095";
        String qCode = "6P10M58I";

        Object response = service.requestData(studentCode, qCode);
        int n = Array.getLength(response);
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = ((Number) Array.get(response, i)).intValue();
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean left = i == 0 || numbers[i] > numbers[i - 1];
            boolean right = i == n - 1 || numbers[i] > numbers[i + 1];

            if (left && right) {
                result.add(i + 1);
            }
        }

        service.submitData(studentCode, qCode, result);
    }
}