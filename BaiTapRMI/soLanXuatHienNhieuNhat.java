package BaiTapRMI;
import RMI.ByteService;
import java.lang.String;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class soLanXuatHienNhieuNhat {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ByteService service = (ByteService) registry.lookup("RMIByteService");

        String studentCode = "B23DCVT095";
        String qCode = "hcgxtdSK";

        byte[] data = service.requestData(studentCode, qCode);
        int[] count = new int[256];

        for (byte value : data) {
            count[value + 128]++;
        }

        byte result = data[0];
        int max = 0;

        for (byte value : data) {
            int frequency = count[value + 128];
            if (frequency > max) {
                max = frequency;
                result = value;
            }
        }

        data = new byte[] {result, (byte) max};
        service.submitData(studentCode, qCode, data);
    }
}