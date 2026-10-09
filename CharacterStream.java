import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CharacterStream {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("36.50.135.242", 2208), 5000);
            socket.setSoTimeout(5000);

            try (
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
                );
                BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
                )
            ) {
                writer.write("B23DCVT095;P9nQrA8r");
                writer.newLine();
                writer.flush();

                String data = reader.readLine();
                if (data == null) {
                    throw new EOFException("Server da dong ket noi.");
                }

                StringJoiner result = new StringJoiner(", ");
                for (String item : data.split(",")) {
                    String domain = item.trim();
                    if (domain.endsWith(".edu")) {
                        result.add(domain);
                    }
                }

                writer.write(result.toString());
                writer.newLine();
                writer.flush();
            }
        }
    }
}