import java.lang.String;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MaxMin {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("36.50.135.242");

        byte[] data = ";B23DCVT095;huOvDKvc".getBytes();
        socket.send(new DatagramPacket(data, data.length, address, 2207));

        byte[] buffer = new byte[65507];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String response = new String(packet.getData(), 0, packet.getLength());
        String[] parts = response.split(";", 2);
        String requestId = parts[0];
        String[] numbers = parts[1].split(",");

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (String number : numbers) {
            int value = Integer.parseInt(number.trim());
            max = Math.max(max, value);
            min = Math.min(min, value);
        }

        data = (requestId + ";" + max + "," + min).getBytes();
        socket.send(new DatagramPacket(data, data.length, address, 2207));

        socket.close();
    }
}