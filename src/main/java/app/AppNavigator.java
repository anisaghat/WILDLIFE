package app;

import controller.controllerLOGIN;
import model.authentication.MapAuthenticator;
import view.ADDANIMAL;
import view.ADDBIOME;
import view.LOGIN;
import view.MAINVIEW;

public class AppNavigator {

    private final MapAuthenticator authenticator;
    public AppNavigator(MapAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    public void showLogin() { // utile par exemple après la déconnexion pour re-afficher la login page mais pr l'instant elle me sert à rien en vrai
        LOGIN view = new LOGIN();
        new controllerLOGIN(view, authenticator, this);
        view.setVisible(true);
    }

    public void showMainWindow() {
        MAINVIEW main = new MAINVIEW(this);
        main.setVisible(true);
    }

    public void showAddAnimalForm()
    {
        ADDANIMAL form = new ADDANIMAL();
        form.setVisible(true);
    }

    public void showAddBiomeForm()
    {
        ADDBIOME form = new ADDBIOME();
        form.setVisible(true);
    }

}
