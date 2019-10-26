package Third;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("-t")) {
            TestRunner testRunner = new TestRunner("Testing\\Resources\\Third\\tests.txt");
            testRunner.runTests();
            TestRunner.printResults();
        } else {
            Scanner scanner = new Scanner(System.in);
            Verificator verificator = null;
            try {
                verificator = new Verificator("Testing\\Resources\\Third\\test1.txt");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                return;
            }
            while (verificator.getNumberOfLives() != 0) {
                System.out.println("Enter your login and password: (login password)");
                String accString = scanner.nextLine();
                try {
                    Account account = Account.fromString(accString);
                    System.out.println(verificator.checkLoginPassword(account.getLogin(), account.getPassword()));
                    if (verificator.isOK) {
                        return;
                    }
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        }
    }
}
