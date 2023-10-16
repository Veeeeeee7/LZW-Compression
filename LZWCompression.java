import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LZWCompression {
    private static final int MAX = 4096;
    private static final int BITS = 12;

    public static ArrayList<Integer> compressToCodes(String fileName) throws Exception {
        HashMap<String, Integer> dict = new HashMap<>();
        for (int i = 0; i < 255; i++) {
            char c = (char) i;
            dict.put(c + "", i);
        }

        String content = Utils.writeFileToString(fileName);
        char[] chars = content.toCharArray();
        ArrayList<Integer> l = new ArrayList<>();

        int count = 256;
        String b = "";
        for (int i = 0; i < chars.length; i++) {
            if (count > MAX) {
                for (char c : b.toCharArray()) {
                    System.out.println(c + " : " + dict.get(c + ""));
                    l.add(dict.get(c + ""));
                }
                for (int j = i; j < chars.length; j++) {
                    System.out.println(chars[j] + " : " + dict.get(chars[j] + ""));
                    l.add(dict.get(chars[j] + ""));
                }
                return l;
            }
            String a = chars[i] + "";
            if (dict.containsKey((b + a))) {
                b += a;
            } else {
                l.add(dict.get(b));
                dict.put(b + a, count);
                System.out.println(count + " : " + b + a);
                count++;
                b = a;
            }
        }
        if (!b.equals("")) {
            l.add(dict.get(b));
        }
        return l;
    }

    public static ArrayList<boolean[]> compress(String fileName) throws Exception {
        ArrayList<Integer> codes = compressToCodes(fileName);
        ArrayList<boolean[]> bins = new ArrayList<>();

        for (int code : codes) {
            boolean[] ret = new boolean[BITS];
            for (int i = 0; i < BITS; i++) {
                ret[BITS - 1 - i] = (1 << i & code) != 0;
            }
            bins.add(ret);
        }
        return bins;
    }

    public static String decodeCodes(ArrayList<Integer> l) {
        HashMap<Integer, String> dict = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            char c = (char) i;
            dict.put(i, c + "");
        }

        StringBuilder sb = new StringBuilder(dict.get(l.get(0)));
        int count = 256;
        char c = (char) l.get(0).intValue();
        String a = c + "";
        if (!dict.containsKey(l.get(1))) {
            dict.put(count, a + a);
        }
        for (int i = 1; i < l.size(); i++) {
            if (count > MAX) {
                for (int j = i; j < l.size(); j++) {
                    System.out.println(dict.get(l.get(j)) + " : " + l.get(j).toString());
                    sb.append(dict.get(l.get(j)));
                }
                return sb.toString();
            }
            String b = "";
            if (dict.containsKey(l.get(i))) {
                b += dict.get(l.get(i));
                sb.append(dict.get(l.get(i)));
                dict.put(count, a + b.charAt(0));
                System.out.println(count + " : " + a + b.charAt(0));
                a = b;
                count++;
            } else {
                sb.append(a);
                dict.put(count, a);
                System.out.println(count + " : " + a);
                count++;
            }
        }

        return sb.toString();
    }

    public static String decode(ArrayList<boolean[]> l) {
        ArrayList<Integer> codes = new ArrayList<>();
        for (boolean[] bits : l) {
            int sum = 0;
            int base = 0;
            for (int i = BITS - 1; i >= 0; i--) {
                int current = bits[i] ? 1 : 0;
                sum += current * Math.pow(2, base);
                base++;
            }
            codes.add(sum);
        }

        return decodeCodes(codes);
    }

}