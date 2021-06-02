import java.util.ArrayList;
import java.util.List;

public class RLEBWT {
    private List<Integer> code = new ArrayList<>();

    private StringBuilder codeString = new StringBuilder();

    public RLEBWT() {
    }


    public String encoding(String toCoding) {
        String bwt = EncodingBWT(toCoding);
        return EncodingRLE(bwt);
    }

    private String EncodingRLE(String str) {
        var stringBuilder = new StringBuilder();

        var counter = 1;
        char letter;
        for (int i = 0; i < str.length(); i++) {
            letter = str.charAt(i);
            if (i + 1 < str.length() && str.charAt(i + 1) == letter) {
                counter++;
            } else {
                stringBuilder.append(counter).append(letter);
                counter = 1;
            }
        }

        return stringBuilder.toString();
    }

    private String EncodingBWT(String str) {
        str = str + "|";
        char[][] matrix = new char[str.length()][];
        for (int i = 0; i < str.length(); i++) {
            matrix[i] = new char[str.length()];
        }

        for (int i = 0; i < str.length(); i++) {
            int codeStep = i;
            for (int j = 0; j < str.length(); j++) {
                matrix[i][j] = str.charAt(codeStep);
                codeStep++;
                if (codeStep == str.length()) {
                    codeStep = 0;
                }
            }
        }

        SortMatrix(matrix);

        return GetLastColumn(matrix);
    }


    public String decoding(String toDecoding) {
        var rle = DecodingRLE(toDecoding);
        var result = DecodingBWT(rle);
        return result.substring(0, result.length() - 1);
    }

    public String DecodingBWT(String codeString) {
        char[][] matrix = new char[codeString.length()][];
        for (int i = 0; i < codeString.length(); i++) {
            matrix[i] = new char[codeString.length()];
        }

        for (int j = codeString.length() - 1; j >= 0; j--) {
            for (int i = 0; i < codeString.length(); i++) {
                matrix[i][j] = codeString.charAt(i);
            }

            SortDecodingMatrix(matrix, j);
        }

        return GetDecodingResult(matrix);
    }

    private String DecodingRLE(String codeString) {
        var strResult = new StringBuilder();
        for (int i = 0; i < codeString.length(); i += 2) {
            for (int j = 0; j < Integer.parseInt(String.valueOf(codeString.charAt(i))); j++) {
                strResult.append(codeString.charAt(i + 1));
            }
        }

        return strResult.toString();
    }

    private String GetDecodingResult(char[][] matrix) {
        var result = new StringBuilder();
        var row = -1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][matrix.length - 1] == '|') {
                row = i;
            }
        }

        for (int j = 0; j < matrix.length; j++) {
            result.append(matrix[row][j]);
        }

        return result.toString();
    }

    private void SortDecodingMatrix(char[][] matrix, int column) {
        for (int count = 0; count < matrix.length; count++) {
            for (int i = 1; i < matrix.length; i++) {
                for (int j = column; j < matrix.length; j++) {
                    if (matrix[i - 1][j] < matrix[i][j]) {
                        break;
                    }

                    if (matrix[i - 1][j] > matrix[i][j]) {
                        var tmp = matrix[i - 1];
                        matrix[i - 1] = matrix[i];
                        matrix[i] = tmp;
                        break;
                    }
                }
            }
        }
    }


    private String GetLastColumn(char[][] matrix) {
        var str = new StringBuilder();

        for (int j = 0; j < matrix.length; j++) {
            str.append(matrix[j][matrix.length - 1]);
        }

        return str.toString();
    }

    private void SortMatrix(char[][] matrix) {
        for (int step = 0; step < matrix.length; step++) {
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i - 1][j] < matrix[i][j]) {
                        break;
                    }

                    if (matrix[i - 1][j] > matrix[i][j]) {
                        var tmp = matrix[i - 1];
                        matrix[i - 1] = matrix[i];
                        matrix[i] = tmp;
                        break;
                    }
                }
            }
        }
    }
}
