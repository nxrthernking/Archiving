import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW {
    private String str;

    public Map<String, Integer> pairs = new HashMap<>();
    public Map<Integer, String> decodepairs = new HashMap<>();

    private int nextNumber = 38;

    private int NextNumber() {
        nextNumber++;
        return nextNumber - 1;
    }

    private int _nextNumberDecoding = 38;

    private int NextNumberDecoding() {
        _nextNumberDecoding++;
        return _nextNumberDecoding - 1;
    }

    public LZW(String str) {
        this.str = str;
        InitializeDictionary();
    }

    public List<Integer> Encoding() {
        List<Integer> code = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int temCode = 0;
        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append(str.charAt(i));
            if (pairs.containsKey(stringBuilder.toString())) {
                temCode = pairs.get(stringBuilder.toString());
            } else {
                pairs.put(stringBuilder.toString(), NextNumber());
                code.add(temCode);
                stringBuilder.delete(0, stringBuilder.length());
                i--;
            }

            if (i == str.length() - 1) {
                String s = String.valueOf(str.charAt(i));
                code.add(pairs.get(String.valueOf(str.charAt(i))));
            }
        }

        return code;
    }

    public String Decoding(List<Integer> code)
    {
        int pW = 0;
        int cW = 1;
        StringBuilder result = new StringBuilder();
        StringBuilder stringPW = new StringBuilder();
        StringBuilder stringCW = new StringBuilder();
        for (int i = 0; i < code.size(); i++)
        {
            cW = code.get(i);
            pW = cW;
            result.append(decodepairs.get(cW));
            if (i == 0)
            {
                stringCW.append(decodepairs.get(cW));
                stringPW.append(stringCW);
            }
            else
            {
                stringCW.delete(0,stringCW.length());
                stringCW.append(decodepairs.get(cW));
                decodepairs.put(NextNumberDecoding(), stringPW + stringCW.toString());
            }
        }

        return result.toString();
    }



    private void InitializeDictionary() {
        pairs.put("a", 1);
        pairs.put("b", 2);
        pairs.put("c", 3);
        pairs.put("d", 4);
        pairs.put("e", 5);
        pairs.put("f", 6);
        pairs.put("g", 7);
        pairs.put("h", 8);
        pairs.put("i", 9);
        pairs.put("j", 10);
        pairs.put("k", 11);
        pairs.put("l", 12);
        pairs.put("m", 13);
        pairs.put("n", 14);
        pairs.put("o", 15);
        pairs.put("p", 16);
        pairs.put("q", 17);
        pairs.put("r", 18);
        pairs.put("s", 19);
        pairs.put("t", 20);
        pairs.put("u", 21);
        pairs.put("v", 22);
        pairs.put("w", 23);
        pairs.put("x", 24);
        pairs.put("y", 25);
        pairs.put("z", 26);
        pairs.put(" ", 27);
        pairs.put("1", 28);
        pairs.put("2", 29);
        pairs.put("3", 30);
        pairs.put("4", 31);
        pairs.put("5", 32);
        pairs.put("6", 33);
        pairs.put("7", 34);
        pairs.put("8", 35);
        pairs.put("9", 36);
        pairs.put("|", 37);

        decodepairs.put(1, "a");
        decodepairs.put(2, "b");
        decodepairs.put(3, "c");
        decodepairs.put(4, "d");
        decodepairs.put(5, "e");
        decodepairs.put(6, "f");
        decodepairs.put(7, "g");
        decodepairs.put(8, "h");
        decodepairs.put(9, "i");
        decodepairs.put(10, "j");
        decodepairs.put(11, "k");
        decodepairs.put(12, "l");
        decodepairs.put(13, "m");
        decodepairs.put(14, "n");
        decodepairs.put(15, "o");
        decodepairs.put(16, "p");
        decodepairs.put(17, "q");
        decodepairs.put(18, "r");
        decodepairs.put(19, "s");
        decodepairs.put(20, "t");
        decodepairs.put(21, "u");
        decodepairs.put(22, "v");
        decodepairs.put(23, "w");
        decodepairs.put(24, "x");
        decodepairs.put(25, "y");
        decodepairs.put(26, "z");
        decodepairs.put(27, " ");
        decodepairs.put(28, "1");
        decodepairs.put(29, "2");
        decodepairs.put(30, "3");
        decodepairs.put(31, "4");
        decodepairs.put(32, "5");
        decodepairs.put(33, "6");
        decodepairs.put(34, "7");
        decodepairs.put(35, "8");
        decodepairs.put(36, "9");
        decodepairs.put(37, "|");
    }
}
