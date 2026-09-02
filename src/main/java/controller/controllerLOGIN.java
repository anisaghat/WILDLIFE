package controller;

import app.launchApplication;
import model.authentication.MapAuthenticator;
import model.dao.AmphibianDAO;
import model.dao.AnimalRepository;
import model.dao.BiomeDAO;
import model.dao.BirdDAO;
import model.dao.FishDAO;
import model.dao.HabitatDAO;
import model.dao.MammalDAO;
import model.dao.ReptileDAO;
import view.LOGIN;
import view.MAINVIEW;

public class controllerLOGIN
{
    private final LOGIN view;
    private final MapAuthenticator model;

    private final launchApplication application;

    public controllerLOGIN(
            LOGIN view,
            MapAuthenticator model,
            launchApplication application

    ) {
        this.view = view;
        this.model = model;
        this.application = application;


        view.addLoginListener(e -> handleLoginAttempt());
    }

    public void handleLoginAttempt()
    {
        boolean isNewChecked = view.getNew();
        String username = view.getUsername();
        String password = view.getPassword();

        if(isNewChecked)
        {
            boolean registred = model.register(username,password);

            if(registred)
            {
                view.showMessage("account created successfully!");
                showMainWindow();
            }
            else {
                view.showMessage("username already exists");
            }
        }
        else
        {
            if(!model.isLoginExists(username))
            {
                view.showMessage("username does not exist. plz check the new user box");
                return;
            }

            if(model.authenticate(username,password))
            {
                showMainWindow();
            }
            else
            {
                view.showMessage("password is incorrect");
            }
        }
    }

    private void showMainWindow() {
        view.dispose();

        application.showMainWindow();
    }
}
