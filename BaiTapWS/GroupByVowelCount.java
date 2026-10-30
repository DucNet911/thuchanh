package WS;

import java.net.URL;
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
        // TreeMap sắp xếp nhóm theo số nguyên âm tăng dần.
        Map<Integer, List<String>> groups = new TreeMap<>();

        for (String word : words) {
            int count = countVowels(word);

            groups.computeIfAbsent(
                    count, k -> new ArrayList<>()).add(word);
        }

        List<String> result = new ArrayList<>();

        for (List<String> group : groups.values()) {
            Collections.sort(group);

            // Dấu phẩy và khoảng trắng giống cách trình bày trong ví dụ.
            result.add(String.join(", ", group));
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        String studentCode = "B23DCVT095";
        String qCode = "rGybEkjd";

        CharacterService service = new CharacterService(new URL(
                "http://36.50.135.242:2221/CharacterService?wsdl"));

        SoapCharacterService port =
                service.getSoapCharacterServicePort();

        List<String> data =
                port.requestStringArray(studentCode, qCode);

        List<String> result = solve(data);

        System.out.println("Du lieu: " + data);
        System.out.println("Cac nhom: " + result);

        port.submitCharacterStringArray(studentCode, qCode, result);

        System.out.println("Da gui ket qua.");
    }
}           