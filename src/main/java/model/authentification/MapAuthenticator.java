package model.authentification;

import java.util.HashMap;
import java.util.Map;

public class MapAuthenticator extends Authenticator{
    final private Map<String, String> mapData = new HashMap<>();


    public MapAuthenticator()
    {
        mapData.put("utilisateur1", "motdepasse1");
    }

    @Override
    protected boolean isLoginExists(String username)
    {
        return mapData.containsKey(username);
    }

    @Override
    protected String getPassword(String username)
    {
        return mapData.get(username);
    }


    @Override
    protected boolean register(String username, String password) {
        if (!mapData.containsKey(username)) {
            mapData.put(username, password);
            return true;
        }
        return false;
    }
}
