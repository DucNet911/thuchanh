package BaiTapRMI;
import RMI.CharacterService;
import java.lang.String;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class chuyenSangLaMa {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

        String studentCode = "B23DCVT095";
        String qCode = "XY9L5Hdz";

        String data = service.requestCharacter(studentCode, qCode);
        int number = Integer.parseInt(data.trim());

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                result.append(symbols[i]);
                number -= values[i];
            }
        }

        service.submitCharacter(studentCode, qCode, result.toString());
    }
}