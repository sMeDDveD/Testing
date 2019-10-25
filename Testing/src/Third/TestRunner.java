package Third;

import java.io.*;
import java.util.Scanner;

public class TestRunner {
    public static String delimiter = ";";

    public static String startMessage = "Tests";
    public static String outputFile = "Testing\\Resources\\Third\\out.txt";
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
                String[] strings = line.split(delimiter, 4);

                String testFileName = strings[0];
                String login = strings[1];
                String password = strings[2];
                String expectedAnswer = strings[3];

                Verificator verificator;
                String errorMessage = "INVALID";
                try {
                    verificator = new Verificator(testFileName);
                } catch (Exception e) {
                    errorMessage = e.getMessage();
                    verificator = null;
                }
                Account account;
                try {
                    account = new Account(login, password);
                } catch (Exception ex) {
                    errorMessage = ex.getMessage();
                    account = null;
                }

                checkResult(writerOutput, account, verificator,
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

    private void checkResult(PrintWriter o, Account account, Verificator verificator,
                             String expected, String error) {
        if (account == null || verificator == null) {
            if (error.equalsIgnoreCase(expected)) {
                goodAnswerNegative(o, error);
            } else {
                badAnswer(o, expected, error);
            }
        } else {
            String result = verificator.checkLoginPassword(account.getLogin(), account.getPassword());
            if (result.equalsIgnoreCase(expected)) {
                goodAnswer(o, account);
            } else {
                badAnswer(o, expected, result);
            }
        }
    }

    private void goodAnswerNegative(PrintWriter o, String error) {
        o.printf("Test #%d OK\n", testCount);
        o.printf("Description: \n\t%s \n\n", error);
    }

    private void goodAnswer(PrintWriter o, Account acc) {
        o.printf("Test #%d OK\n", testCount);
        //o.printf("Description: \n\t%s \n", acc);
        //o.printf("\tkey: %d\n", key);
        //o.printf("\tresult: %s\n\n", bs.getBsResult());
    }

    private void badAnswer(PrintWriter o, String expected, String got) {
        o.printf("Test #%d FAILED: {Expected - [%s], Got - [%s]}\n\n",
                testCount, expected, got);
        badTestCounter++;
    }
}

