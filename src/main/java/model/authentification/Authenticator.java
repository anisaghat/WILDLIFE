package model.authentification;

public class Authenticator {
    public boolean authenticate(String username, String password) {
        if (isLoginExists(username)) {
            String storedPassword = getPassword(username);
            return storedPassword != null && storedPassword.equals(password);
        }
        return false;
    }

    protected boolean isLoginExists(String username) {
        return false;
    }

    protected String getPassword(String username) {
        return null;
    }

    protected boolean register(String username, String password) {
        return false;
    }
}
