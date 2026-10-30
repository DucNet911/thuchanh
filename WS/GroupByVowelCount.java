package WS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GroupByVowelCount {
    private static int countVowels(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if ("aeiouAEIOU".indexOf(word.charAt(i)) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static List<String> solve(List<String> words) {
        // Xep cac nhom theo so nguyen am tang dan.
        Map<Integer, List<String>> groups = new TreeMap<>();

        for (String word : words) {
            int count = countVowels(word);
            groups.computeIfAbsent(count, k -> new ArrayList<>()).add(word);
        }

        List<String> result = new ArrayList<>();
        for (List<String> group : groups.values()) {
            Collections.sort(group);
            result.add(String.join(", ", group));
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String studentCode = "B23DCVT095";
        String qCode = "rGybEkjd";

        CharacterService service = new CharacterService();
        SoapCharacterService port = service.getSoapCharacterServicePort();
        ClientSettings.configure(port);

        List<String> data = port.requestStringArray(studentCode, qCode);
        List<String> result = solve(data);

        System.out.println("Du lieu: " + data);
        System.out.println("Cac nhom: " + result);

        port.submitCharacterStringArray(studentCode, qCode, result);
        System.out.println("Da gui ket qua.");
    }
}
