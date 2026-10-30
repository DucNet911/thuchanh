package BaiTapUDP;
import UDP.Student;
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

public class ChuanHoaEmail {
    public static void main(java.lang.String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("36.50.135.242");

        byte[] data = ";B23DCVT095;F0cmRIwj".getBytes(StandardCharsets.UTF_8);
        socket.send(new DatagramPacket(data, data.length, address, 2209));

        byte[] buffer = new byte[65507];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        ObjectInputStream input = new ObjectInputStream(
            new ByteArrayInputStream(
                packet.getData(), packet.getOffset() + 8, packet.getLength() - 8
            )
        );
        Student student = (Student) input.readObject();
        input.close();

        String[] words = student.name.trim().toLowerCase(Locale.ROOT).split("\\s+");
        StringBuilder name = new StringBuilder();

        for (String word : words) {
            if (name.length() > 0) {
                name.append(' ');
            }
            name.append(Character.toUpperCase(word.charAt(0)));
            name.append(word.substring(1));
        }

        StringBuilder email = new StringBuilder(words[words.length - 1]);
        for (int i = 0; i < words.length - 1; i++) {
            email.append(words[i].charAt(0));
        }
        email.append("@ptit.edu.vn");

        student.name = name.toString();
        student.email = email.toString();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(packet.getData(), packet.getOffset(), 8);

        ObjectOutputStream objectOutput = new ObjectOutputStream(output);
        objectOutput.writeObject(student);
        objectOutput.flush();

        data = output.toByteArray();
        socket.send(new DatagramPacket(data, data.length, address, 2209));

        objectOutput.close();
        socket.close();
    }
}