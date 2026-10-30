package WS;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RotateCharacters {

    public static List<Integer> solve(List<Integer> characters) {
        List<Integer> result = new ArrayList<>(characters);

        if (!result.isEmpty()) {
            // Các phần tử đã là mã số ký tự, ví dụ 'a' = 97.
            int shift = Math.floorMod(
                    characters.get(0), characters.size());

            // Số dương tương ứng với xoay sang phải.
            Collections.rotate(result, shift);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        String studentCode = "B23DCVT095";
        String qCode = "AN44ec0F";

        CharacterService service = new CharacterService(new URL(
                "http://36.50.135.242:2221/CharacterService?wsdl"));

        SoapCharacterService port =
                service.getSoapCharacterServicePort();

        List<Integer> data =
                port.requestCharacter(studentCode, qCode);

        List<Integer> result = solve(data);

        System.out.println("Du lieu: " + data);
        System.out.println("Sau khi xoay: " + result);

        port.submitCharacterCharArray(studentCode, qCode, result);

        System.out.println("Da gui ket qua.");
    }
}