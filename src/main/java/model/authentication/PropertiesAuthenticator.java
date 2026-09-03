package model.authentication;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesAuthenticator extends Authenticator {

    private Properties users;
    private final String filePath;

    public PropertiesAuthenticator(String filePath) {
        this.users = new Properties();
        this.filePath = filePath;
        loadUsers();
    }

    public void saveUsers() {
        try (FileOutputStream output =  new FileOutputStream(filePath)) {
            users.store(output, "User credentials");
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    public void loadUsers() {
        try (FileInputStream input = new FileInputStream(filePath)) {
            users.load(input);
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }


    @Override
    public boolean isLoginExists(String username) {
        return users.containsKey(username);
    }

    @Override
    protected String getPassword(String username) {
        return users.getProperty(username);
    }

    @Override
    public boolean register(String username, String password) {

        if (!isLoginExists(username)) {
            users.setProperty(username, password);
            saveUsers();
            return true;
        }
        return false;
    }
}
