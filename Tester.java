import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) throws Exception {
        // ArrayList<Integer> codes = LZWCompression.compressToCodes("testing");

        // System.out.println("WORKS");
        // for (Integer code : codes) {
        // System.out.print(code + ", ");
        // }

        ArrayList<boolean[]> bins = LZWCompression.compress("testing");
        System.out.println(LZWCompression.decode(bins));

        // ArrayList<boolean[]> bins = LZWCompression.compress("testing");
        // StringBuilder sb = new StringBuilder();
        // for (boolean[] bits : bins) {
        // for (boolean bit : bits) {
        // if (bit) {
        // sb.append("1");
        // } else {
        // sb.append("0");
        // }
        // }
        // // sb.append(", ");
        // }
        // System.out.println(sb.toString());
    }
}
