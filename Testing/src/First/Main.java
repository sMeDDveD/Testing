package First;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("BlackBoxTesting:");
            try {
                Runner.inputFileName = "Resources\\First\\positiveTest.txt";
                Runner.testRunning();
                Runner.inputFileName = "Resources\\First\\negativeTest.txt";
                Runner.testRunning();
            } catch (Exception ex) {
                System.out.println("Wrong expectations format: " + ex.getMessage());
                ex.printStackTrace();
            }
            Runner.printResults();
        } else if (args.length == 3) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input sides: (a, b, c)");
            Triangle answer;
            try {
                answer = TriangleParser.parseTriangle(scanner.nextLine());
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
            System.out.println(answer);
            System.out.printf("a = %s, b = %s, c = %s" +
                            "\n",
                    Objects.requireNonNull(answer).getA(), answer.getB(), answer.getC());
            System.out.printf("S = %.3f, P = %d, Type = %s",
                    answer.getArea(), answer.getPerimeter(), answer.getTypeOfTriangle());
        }
        else {
            System.out.println("WhiteBoxTesting:");
            try {
                Runner.inputFileName = "Resources\\First\\whiteBox.txt";
                Runner.testRunning();
            } catch (Exception ex) {
                System.out.println("Wrong expectations format: " + ex.getMessage());
                ex.printStackTrace();
            }
            Runner.printResults();
        }
    }
}
