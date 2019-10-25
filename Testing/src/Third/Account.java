package Third;

public class Account {
    public static final String DELIMITER = " ";

    private String login;
    private String password;

    public Account(String aLogin, String aPassword) {
        if (aLogin.length() == 0)
            throw new IllegalArgumentException("Login was empty!");
        if (aPassword.length() == 0)
            throw new IllegalArgumentException("Password was empty");
        if (aLogin.length() > 10) {
            throw new IllegalArgumentException("Login was too long!");
        }
        if (aPassword.length() > 8) {
            throw new IllegalArgumentException("Password was too long!");
        }
        login = aLogin;
        password = aPassword;
    }

    public static Account fromString(String acc) {
        if (acc == null)
            throw new NullPointerException("Login-password string was null!");
        String[] result = acc.split(DELIMITER, 2);
        return new Account(result[0].trim(), result[1].trim());
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
