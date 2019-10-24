package Third;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Verificator {
    public static final int MAX_FILE_SIZE = 512;

    private Map<String, String> passwordBase = new LinkedHashMap<>();
    private short numberOfLives = 3;

    public Verificator(String aFileName) throws FileNotFoundException {
        File file = new File(aFileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Invalid name of the file!");
        }
        if (file.length() == 0) {
            throw new IllegalArgumentException("File was empty");
        }
        if (file.length() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File was too big");
        }

        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            Account nowAccount = Account.fromString(scanner.nextLine());
            passwordBase.put(nowAccount.getLogin(), nowAccount.getPassword());
        }
    }

    public String checkLoginPassword(String login, String password) {
        if (numberOfLives == 0) {
            return "There are no attempts for you!";
        }
        if (password.equals(passwordBase.get(login))) {
            return login + ", you are welcome!";
        }
        else {
            return ((--numberOfLives != 0) ? ("Try again! Attempts left: "
                            + numberOfLives) : "It was the last attempt");
        }
    }
}
