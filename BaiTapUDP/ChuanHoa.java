package BaiTapUDP;
import java.lang.String;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class ChuanHoa {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("36.50.135.242");

        String message = ";B23DCVT095;4ZbN7Kil";
        byte[] sendData = message.getBytes(StandardCharsets.UTF_8);

        DatagramPacket sendPacket = new DatagramPacket(
            sendData, sendData.length, address, 2208
        );
        socket.send(sendPacket);

        byte[] buffer = new byte[65507];
        DatagramPacket receivePacket = new DatagramPacket(
            buffer, buffer.length
        );
        socket.receive(receivePacket);

        String response = new String(
            receivePacket.getData(),
            receivePacket.getOffset(),
            receivePacket.getLength(),
            StandardCharsets.UTF_8
        );

        String[] parts = response.split(";", 2);
        String requestId = parts[0];
        String input = parts[1];

        StringBuilder normalized = new StringBuilder();
        boolean first = true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (Character.isWhitespace(c)) {
                normalized.append(c);
                first = true;
            } else {
                if (first) {
                    normalized.append(Character.toUpperCase(c));
                } else {
                    normalized.append(Character.toLowerCase(c));
                }
                first = false;
            }
        }

        String result = requestId + ";" + normalized;
        sendData = result.getBytes(StandardCharsets.UTF_8);

        sendPacket = new DatagramPacket(
            sendData, sendData.length, address, 2208
        );
        socket.send(sendPacket);

        socket.close();
    }
}