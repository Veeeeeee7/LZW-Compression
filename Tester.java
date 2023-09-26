import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        // String a = "BOB";
        // System.out.println(a.substring(0, a.length() - 1));
        LZWCompression l = new LZWCompression();
        ArrayList<Integer> a = l.compress("test");

        for (Integer i : a) {
            System.out.print(i + " s ");
        }
    }
}
