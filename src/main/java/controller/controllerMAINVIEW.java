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
import model.entities.ExtinctionStatut;
import model.entities.animals.Animal;
import view.ADDANIMAL;
import view.ADDBIOME;
import view.ADDNEWHABITAT;
import view.ENDANGEREDANIMALS;
import view.LOGIN;
import view.MAINVIEW;

import java.util.ArrayList;
import java.util.List;

public class controllerMAINVIEW {
    private final MAINVIEW view;
    private final MapAuthenticator authenticator;
    private final AnimalRepository animalRepository;
    private final BiomeDAO biomeDAO;
    private final HabitatDAO habitatDAO;
    private final MammalDAO mammalDAO;
    private final BirdDAO birdDAO;
    private final FishDAO fishDAO;
    private final ReptileDAO reptileDAO;
    private final AmphibianDAO amphibianDAO;
    private final launchApplication application;

    public controllerMAINVIEW(
            MAINVIEW view,
            MapAuthenticator authenticator,
            launchApplication application,
            AnimalRepository animalRepository,
            BiomeDAO biomeDAO,
            HabitatDAO habitatDAO,
            MammalDAO mammalDAO,
            BirdDAO birdDAO,
            FishDAO fishDAO,
            ReptileDAO reptileDAO,
            AmphibianDAO amphibianDAO
    ) {
        this.view = view;
        this.authenticator = authenticator;
        this.animalRepository = animalRepository;
        this.application = application;
        this.biomeDAO = biomeDAO;
        this.habitatDAO = habitatDAO;
        this.mammalDAO = mammalDAO;
        this.birdDAO = birdDAO;
        this.fishDAO = fishDAO;
        this.reptileDAO = reptileDAO;
        this.amphibianDAO = amphibianDAO;

        view.setController(this);
        refreshAnimals();
    }

    public void showAddAnimalForm() {
        ADDANIMAL form = new ADDANIMAL(habitatDAO.findAll());
        new controllerADDANIMAL(
                form,
                mammalDAO,
                birdDAO,
                fishDAO,
                reptileDAO,
                amphibianDAO
        );
        form.setVisible(true);
        refreshAnimals();
    }

    public void showAddBiomeForm() {
        ADDBIOME form = new ADDBIOME();
        new controllerADDBIOME(form, biomeDAO);
        form.setVisible(true);
    }

    public void showAddHabitatForm() {
        ADDNEWHABITAT form = new ADDNEWHABITAT(biomeDAO.findAll());
        new controllerADDHABITAT(form, habitatDAO);
        form.setVisible(true);
    }

    public void showEndangeredAnimals() {
        List<Animal> endangeredAnimals = new ArrayList<>();

        for (Animal animal : animalRepository.findAll()) {
            if (animal.getStatus() == ExtinctionStatut.ENDANGERED) {
                endangeredAnimals.add(animal);
            }
        }

        ENDANGEREDANIMALS dialog = new ENDANGEREDANIMALS(endangeredAnimals);
        dialog.setVisible(true);
    }

    public void refreshAnimals() {
        view.displayAnimals(animalRepository.findAll());
    }

    public void logout() {
        view.dispose();

        application.start();
    }
}
