public class RunnerRLEBWT {
    public static void main(String[] args) {
        String str = "what are the indications for getting a digoxin level?";
        RLEBWT rlebwt = new RLEBWT();
        String encoding = rlebwt.encoding(str);
        System.out.println(encoding);
        String decoding = rlebwt.decoding(encoding);
        System.out.println(decoding);
    }
}
