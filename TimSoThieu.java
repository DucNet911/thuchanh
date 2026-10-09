import java.lang.String;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class TimSoThieu {
    public static void main(java.lang.String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("36.50.135.242");

        byte[] data = ";B23DCVT095;AZAcGqzC".getBytes(StandardCharsets.UTF_8);
        socket.send(new DatagramPacket(data, data.length, address, 2207));

        byte[] buffer = new byte[65507];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String response = new String(
            packet.getData(), packet.getOffset(), packet.getLength(),
            StandardCharsets.UTF_8
        );

        String[] parts = response.split(";", 3);
        int n = Integer.parseInt(parts[1].trim());
        boolean[] present = new boolean[n + 1];

        if (!parts[2].trim().isEmpty()) {
            for (String item : parts[2].split(",")) {
                int value = Integer.parseInt(item.trim());
                if (value >= 1 && value <= n) {
                    present[value] = true;
                }
            }
        }

        StringJoiner missing = new StringJoiner(",");
        for (int i = 1; i <= n; i++) {
            if (!present[i]) {
                missing.add(String.valueOf(i));
            }
        }

        data = (parts[0] + ";" + missing).getBytes(StandardCharsets.UTF_8);
        socket.send(new DatagramPacket(data, data.length, address, 2207));

        socket.close();
    }
}