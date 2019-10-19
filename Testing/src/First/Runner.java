package First;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Runner {
    public static String inputFileName = "INVALID";
    public static String outputFileName = "Resources\\First\\out.txt";

    public static boolean firstStart = false;

    public static String testExpectDelimiter = ":";
    public static double precision = 0.001;

    private static int badTests = 0;
    private static int allTests = 0;

    private static int testCount = 1;

    public static void testRunning() {
        testCount = 1;
        try (FileReader fileReader = new FileReader(inputFileName);
             Scanner inputScanner = new Scanner(fileReader);
             FileWriter fileWriter = new FileWriter(outputFileName, firstStart);
             PrintWriter outputWriter = new PrintWriter(fileWriter)) {

            outputWriter.print("------------- ");
            outputWriter.println(!firstStart ?
                    "Positive tests ------------ " : "Negative tests ------------- ");
            while (inputScanner.hasNextLine()) {
                String data = inputScanner.nextLine();
                StringTokenizer stringTokenizer = new StringTokenizer(data, testExpectDelimiter);
                String testInput = stringTokenizer.nextToken();
                String expectedOutput = stringTokenizer.nextToken();

                Triangle triangle = TriangleParser.parseTriangle(testInput);
                checkResult(expectedOutput, outputWriter, triangle);
                testCount++;
                allTests++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        firstStart = !firstStart;
    }

    private static void checkResult(String expected, PrintWriter o, Triangle triangle) {
        if (expected.trim().equals("null")) {
            if (triangle == null) {
                goodAnswerNegative(o);
                return;
            }
        } else {
            StringTokenizer stringTokenizer =
                    new StringTokenizer(expected, TriangleParser.delimiter);

            int perimeter = Integer.parseInt(stringTokenizer.nextToken().trim());
            double square = Double.parseDouble(stringTokenizer.nextToken().trim());
            String type = stringTokenizer.nextToken().trim();

            if (triangle != null && perimeter == triangle.getPerimeter() &&
                    Math.abs(square - triangle.getArea()) < precision &&
                    type.equalsIgnoreCase(triangle.getTypeOfTriangle().toString())) {
                goodAnswer(o, triangle);
                return;
            }
        }
        badAnswer(o, expected, triangle);
    }

    private static void goodAnswer(PrintWriter o, Triangle t) {
        o.println("Test #" + testCount + " OK ");
        o.println("Description:");
        o.printf("a = %d, b = %d, c = %d \n", t.getA(), t.getB(), t.getC());
        o.println("P, S, Type = " + t + "\n");
    }

    private static void goodAnswerNegative(PrintWriter o) {
        o.println("Test #" + testCount + " OK \n" + TriangleParser.lastError + "\n");
    }

    public static void badAnswer(PrintWriter o, String expected, Triangle triangle) {
        o.printf("Test #%d FAILED: {Expected - [%s], Got - [%s]}\n",
                testCount, expected, triangle);
        badTests++;
    }

    public static void printResults() {
        System.out.printf("All tests - %d, Succeed tests - %d, Failed tests - %d\n",
                allTests, allTests - badTests, badTests);
        System.out.println("For more information check file: Resources\\First\\out.txt");
    }
}
