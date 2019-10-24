package Third;

public class Account {
    public static final String DELIMITER = " ";

    private String login;
    private String password;

    public static Account fromString(String acc) {
        String[] result = acc.split(DELIMITER, 2);
        return new Account(result[0].trim(), result[1].trim());
    }

    public Account(String aLogin, String aPassword) {
        if (aLogin.length() > 10) {
            throw new IllegalArgumentException("Login was too long!");
        }
        if (aPassword.length() > 8) {
            throw new IllegalArgumentException("Password was too long!");
        }
        login = aLogin;
        password = aPassword;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return login + " " + password;
    }
}
