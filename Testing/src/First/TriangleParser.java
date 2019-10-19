package First;

import java.util.StringTokenizer;

public class TriangleParser {

    public static String lastError = "INVALID";
    public static String delimiter = ",";
    public static Triangle parseTriangle(String toParse) {
        Triangle answer;

        StringTokenizer tokenizer = new StringTokenizer(toParse, delimiter);

        if (tokenizer.countTokens() != 3) {
            lastError = "Incorrect number of parameters";
            return null;
        }

        short[] sides = new short[3];
        try {
            for (int i = 0; i < 3; i++) {
                sides[i] = Short.parseShort(tokenizer.nextToken().trim());
            }
        }
        catch (NumberFormatException e) {
            lastError = "Parameters do not match type Short: " + e.getMessage();
            return null;
        }

        try {
            answer = new Triangle(sides[0], sides[1], sides[2]);
        }
        catch (IllegalArgumentException e) {
            lastError = e.getMessage();
            return null;
        }

        return answer;
    }
}
