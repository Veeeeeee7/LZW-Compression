import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LZWCompression {
    HashMap<String, Integer> dict;

    public LZWCompression() {
        dict = new HashMap<>();
        for (int i = 0; i < 255; i++) {
            char c = (char) i;
            dict.put(Character.toString(c), i);
        }
    }

    public ArrayList<Integer> compress(String fileName) {
        String content = readFile(fileName);
        char[] chars = content.toCharArray();
        ArrayList<Integer> l = new ArrayList<>();

        int index = 0;
        int count = 256;
        while (index < chars.length) {
            String a = chars[index] + "";
            while (index < chars.length && dict.containsKey(a)) {
                index++;
                a += chars[index];
            }
            index--;
            String b = a.substring(0, a.length() - 1);

            l.add(dict.get(b));
            dict.put(a, count);
            count++;
        }
        return l;
    }

    private String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                content.append(reader.nextLine() + "\n");
            }
            if (content.length() != 0) {
                content.deleteCharAt(content.length() - 1);
            }
            reader.close();
            return content.toString();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "";
    }
}