package Third;

public class Main {
    public static void main(String[] args) {
        TestRunner testRunner = new TestRunner("Resources\\Third\\tests.txt");
        testRunner.runTests();
        TestRunner.printResults();
    }
}
