import java.util.HashMap;

public class ShannonFanoRunner {
    public static void main(String[] args) {
        String str = "what are the indications for getting a digoxin level?";

        String password = "~password";

        String givenString = str + password;

        ShannonFano sfc = new ShannonFano(givenString);

        HashMap<Character, String> compressedResult = sfc.getCompressedResult();

        compressedResult.values().forEach(System.out::print);

        String decode = sfc.decode(compressedResult);
        System.out.println();
        System.out.println(decode);
        System.out.println(removePassword(decode));
    }

    private static String removePassword(String givenString){
        StringBuilder builder = new StringBuilder(givenString);
        int i = builder.indexOf("~");
        builder.delete(i, builder.length());
        return builder.toString();
    }
}
