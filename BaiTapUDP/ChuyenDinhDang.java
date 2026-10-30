package BaiTapUDP;
import UDP.Customer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.String;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class ChuyenDinhDang {
    public static void main(java.lang.String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("36.50.135.242");

        byte[] data = ";B23DCVT095;TqaKSt7v".getBytes(StandardCharsets.UTF_8);
        socket.send(new DatagramPacket(data, data.length, address, 2209));

        byte[] buffer = new byte[65507];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        ObjectInputStream input = new ObjectInputStream(
            new ByteArrayInputStream(
                packet.getData(), packet.getOffset() + 8, packet.getLength() - 8
            )
        );
        Customer customer = (Customer) input.readObject();
        input.close();

        String[] words = customer.name.trim().toLowerCase(Locale.ROOT).split("\\s+");
        StringBuilder name = new StringBuilder(
            words[words.length - 1].toUpperCase(Locale.ROOT)
        );
        StringBuilder userName = new StringBuilder();

        if (words.length > 1) {
            name.append(", ");
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (i > 0) {
                name.append(' ');
            }
            name.append(Character.toUpperCase(words[i].charAt(0)));
            name.append(words[i].substring(1));
            userName.append(words[i].charAt(0));
        }
        userName.append(words[words.length - 1]);

        String[] date = customer.dayOfBirth.split("-");
        customer.name = name.toString();
        customer.dayOfBirth = date[1] + "/" + date[0] + "/" + date[2];
        customer.userName = userName.toString();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(packet.getData(), packet.getOffset(), 8);

        ObjectOutputStream objectOutput = new ObjectOutputStream(output);
        objectOutput.writeObject(customer);
        objectOutput.flush();

        data = output.toByteArray();
        socket.send(new DatagramPacket(data, data.length, address, 2209));

        objectOutput.close();
        socket.close();
    }
}