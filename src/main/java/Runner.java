import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Runner {
    public static void main(String[] args) {
        archive();
        unzip();
    }


    private static void archive(){
        try(Scanner scanner = new Scanner(new File("src/main/resources/in"))){
            int count = 0;
            int tomCount = 2;
            BufferedWriter writer = new BufferedWriter(new FileWriter("out1.txt"));
            while (scanner.hasNext()){
                String in = scanner.nextLine();
                count++;
                if(count > 3){
                    writer = new BufferedWriter(new FileWriter("out" + tomCount + ".txt"));
                    tomCount++;
                    count = 1;
                }
                LZW lzw = new LZW(in);
                List<Integer> encoding = lzw.Encoding();
                for (Integer i:encoding) {
                    writer.write(i + " ");
                }
                writer.write("\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void unzip(){
        LZW lzw = new LZW("");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/file.txt"));
            for (int i = 1; i <= 3; i++){
                Scanner scanner = new Scanner(new File("out" + i + ".txt"));
                while (scanner.hasNext()){
                    String in  = scanner.nextLine();
                    String[] s = in.split(" ");
                    List<Integer> collect = Arrays.stream(s).map(Integer::valueOf).collect(Collectors.toList());
                    String decoding = lzw.Decoding(collect);
                    writer.write(decoding);
                    writer.write("\n");
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
