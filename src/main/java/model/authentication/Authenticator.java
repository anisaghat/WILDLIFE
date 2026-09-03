package model.authentication;

public abstract class Authenticator {
    public final boolean authenticate(String username, String password) {
        if (isLoginExists(username)) {
            String storedPassword = getPassword(username);
            return storedPassword != null && storedPassword.equals(password);
        }
        return false;
    }

    public abstract boolean isLoginExists(String username) ;

    protected abstract String getPassword(String username) ;

    public abstract boolean register(String username, String password) ;
}


// ici ça utilise bien le patron Template Method car on peut l'utiliser avec 2 classes différentes
// les méthodes abstraites seront implémentées différemment selon la classe