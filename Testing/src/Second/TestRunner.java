package Second;

import java.io.*;
import java.util.Scanner;

public class TestRunner {
    public static String delimiter = ":";

    public static String startMessage = "Tests";
    public static String outputFile = "Resources\\Second\\out.txt";
    public static boolean needToAppend;

    private static int allTestCounter = 0;
    private static int badTestCounter = 0;

    private String inputFile;
    private int testCount = 1;


    public TestRunner(String inputFileName) {
        if (!(new File(inputFileName)).isFile())
            throw new IllegalArgumentException("File '" + inputFileName + "' does not exist");

        inputFile = inputFileName;
    }

    public static void printResults() {
        System.out.printf("All tests - %d, Succeed tests - %d, Failed tests - %d\n",
                allTestCounter, allTestCounter - badTestCounter, badTestCounter);
        System.out.println("For more information check file:" + outputFile);
    }

    void runTests() {
        try (FileReader fileReader = new FileReader(inputFile);
             Scanner scannerInput = new Scanner(fileReader);
             FileWriter fileWriter = new FileWriter(outputFile, needToAppend);
             PrintWriter writerOutput = new PrintWriter(fileWriter)) {

            needToAppend = !needToAppend;
            writerOutput.println("-------------" + startMessage + "-------------");

            while (scannerInput.hasNextLine()) {
                String line = scannerInput.nextLine();
                String[] strings = line.split(delimiter, 3);

                String arrayLine = strings[0];
                String keyLine = strings[1];
                String expectedAnswer = strings[2];

                SortedArray bs;
                int key = 0;
                String errorMessage = "INVALID";
                try {
                    bs = new SortedArray(arrayLine);
                    key = SortedArray.keyParse(keyLine);

                } catch (Exception e) {
                    errorMessage = e.getMessage();
                    bs = null;
                }
                checkResult(writerOutput, bs, key,
                        expectedAnswer, errorMessage);
                testCount++;
                allTestCounter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkResult(PrintWriter o, SortedArray sortedArray, int key,
                             String expected, String error) {
        if (sortedArray == null) {
            if (error.equalsIgnoreCase(expected)) {
                goodAnswerNegative(o, error);
            } else {
                badAnswer(o, expected, error);
            }
        } else {
            String result = sortedArray.binarySearch(key).toString();
            if (result.equalsIgnoreCase(expected)) {
                goodAnswer(o, sortedArray, key);
            } else {
                badAnswer(o, expected, result);
            }
        }
    }

    private void goodAnswerNegative(PrintWriter o, String error) {
        o.printf("Test #%d OK\n", testCount);
        o.printf("Description: \n\t%s \n\n", error);
    }

    private void goodAnswer(PrintWriter o, SortedArray bs, int key) {
        o.printf("Test #%d OK\n", testCount);
        o.printf("Description: \n\t%s \n", bs);
        o.printf("\tkey: %d\n", key);
        o.printf("\tresult: %s\n\n", bs.getBsResult());
    }

    private void badAnswer(PrintWriter o, String expected, String got) {
        o.printf("Test #%d FAILED: {Expected - [%s], Got - [%s]}\n\n",
                testCount, expected, got);
        badTestCounter++;
    }
}
