import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChuanHoa {
    public static void main(String[] args) throws Exception {
        String serverIp = "36.50.135.242";
        int port = 2208;
        String studentCode = "B23DCVT095"; 
        String qCode = "4ZbN7Ki1";

        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(serverIp);
        socket.setSoTimeout(5000);

        String requestMessage = ";" + studentCode + ";" + qCode;
        byte[] sendData = requestMessage.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        socket.send(sendPacket);

        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);

        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        
        String[] parts = receivedMessage.split(";", 2);
        if (parts.length == 2) {
            String requestId = parts[0];
            String data = parts[1];
            
            String[] words = data.trim().split("\\s+");
            StringBuilder normalizedData = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                if (words[i].length() > 0) {
                    normalizedData.append(Character.toUpperCase(words[i].charAt(0)))
                                  .append(words[i].substring(1).toLowerCase());
                    if (i < words.length - 1) {
                        normalizedData.append(" ");
                    }
                }
            }
            
            String responseMessage = requestId + ";" + normalizedData.toString();
            byte[] responseData = responseMessage.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, address, port);
            socket.send(responsePacket);
        }

        socket.close();
    }
}