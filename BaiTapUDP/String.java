package BaiTapUDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class String {
    public static void main(java.lang.String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("36.50.135.242");

        byte[] data = ";B23DCVT095;0TFWQKgX".getBytes();
        socket.send(new DatagramPacket(data, data.length, address, 2208));

        byte[] buffer = new byte[65507];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        java.lang.String response = new java.lang.String(
            packet.getData(), 0, packet.getLength()
        );
        java.lang.String[] parts = response.split(";", 2);
        java.lang.String requestId = parts[0];
        java.lang.String input = parts[1];

        int[] count = new int[65536];
        for (int i = 0; i < input.length(); i++) {
            count[input.charAt(i)]++;
        }

        char character = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            if (count[input.charAt(i)] > count[character]) {
                character = input.charAt(i);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(requestId).append(';').append(character).append(':');

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == character) {
                result.append(i + 1).append(',');
            }
        }

        data = result.toString().getBytes();
        socket.send(new DatagramPacket(data, data.length, address, 2208));
        socket.close();
    }
}