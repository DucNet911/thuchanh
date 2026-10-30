package BaiTapRMI;
import RMI.ByteService;
import java.lang.String;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class soLanXuatHienItNhat {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ByteService service = (ByteService) registry.lookup("RMIByteService");

        String studentCode = "B23DCVT095";
        String qCode = "nV5Xyh5a";

        byte[] data = service.requestData(studentCode, qCode);
        int[] count = new int[256];

        for (byte value : data) {
            count[value + 128]++;
        }

        byte result = data[0];
        int min = Integer.MAX_VALUE;

        for (byte value : data) {
            int frequency = count[value + 128];
            if (frequency < min) {
                min = frequency;
                result = value;
            }
        }

        data = new byte[] {result, (byte) min};
        service.submitData(studentCode, qCode, data);
    }
}