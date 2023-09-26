import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LZWCompression {
    public static ArrayList<Integer> compress(String fileName) {
        HashMap<String, Integer> dict = new HashMap<>();
        for (int i = 0; i < 255; i++) {
            char c = (char) i;
            dict.put(c + "", i);
        }

        String content = readFile(fileName);
        char[] chars = content.toCharArray();
        ArrayList<Integer> l = new ArrayList<>();

        int count = 256;
        String b = "";
        for (int i = 0; i < chars.length; i++) {
            String a = chars[i] + "";
            if (dict.containsKey((b + a))) {
                b += a;
            } else {
                l.add(dict.get(b));
                dict.put(b + a, count);
                count++;
                b = a;
            }
        }
        if (!b.equals("")) {
            l.add(dict.get(b));
        }
        return l;
    }

    public static String decode(ArrayList<Integer> l) {
        HashMap<Integer, String> dict = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            char c = (char) i;
            dict.put(i, c + "");
        }

        StringBuilder sb = new StringBuilder(dict.get(l.get(0)));
        int count = 256;
        char c = (char) l.get(0).intValue();
        String a = c + "";
        for (int i : l) {
            String b = "";
            if (dict.containsKey(i)) {
                b += dict.get(i);
                sb.append(dict.get(i));
                dict.put(count, b + a);
                a = b;
            } else {
                sb.append(a);
                dict.put(count, a);
                count++;
            }
        }

        return sb.toString();
    }

    private static String readFile(String fileName) {
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