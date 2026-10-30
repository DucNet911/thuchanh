package WS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RotateCharacters {
    public static List<Integer> solve(List<Integer> characters) {
        List<Integer> result = new ArrayList<>(characters);

        if (!result.isEmpty()) {
            // Phan tu dau da la ma so cua ky tu, vi du 'a' = 97.
            int shift = Math.floorMod(characters.get(0), characters.size());
            Collections.rotate(result, shift);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        String studentCode = "B23DCVT095";
        String qCode = "AN44ec0F";

        CharacterService service = new CharacterService();
        SoapCharacterService port = service.getSoapCharacterServicePort();
        ClientSettings.configure(port);

        List<Integer> data = port.requestCharacter(studentCode, qCode);
        List<Integer> result = solve(data);

        System.out.println("Du lieu: " + data);
        System.out.println("Sau khi xoay: " + result);

        port.submitCharacterCharArray(studentCode, qCode, result);
        System.out.println("Da gui ket qua.");
    }
}
