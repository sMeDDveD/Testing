package Third;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        try (FileReader fileReader = new FileReader(file);
             Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNextLine()) {
                Account nowAccount = Account.fromString(scanner.nextLine());
                passwordBase.put(nowAccount.getLogin(), nowAccount.getPassword());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String checkLoginPassword(String login, String password) {
        if (numberOfLives == 0) {
            return "There are no attempts for you!";
        }
        if (password.equals(passwordBase.get(login))) {
            return login + ", you are welcome!";
        } else {
            return ((--numberOfLives != 0) ? ("Try again! Attempts left: "
                    + numberOfLives) : "Wrong data! It was the last attempt.");
        }
    }
}
