package Second;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("-t")) {
            System.out.println("Testing:");
            try {
                TestRunner.startMessage = "Positive tests";
                new TestRunner("Resources\\Second\\positiveTest.txt").runTests();
                TestRunner.startMessage = "Negative tests";
                new TestRunner("Resources\\Second\\negativeTest.txt").runTests();
                TestRunner.printResults();
            }
            catch (Exception ex) {
                System.out.println("Something went wrong: " + ex.getMessage());
            }
        }
        else {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the array: ");
                SortedArray array = new SortedArray(scanner.nextLine());
                System.out.println("Enter the key: ");
                int key = scanner.nextInt();
                System.out.println("Result: " + array.binarySearch(key));
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
