package controller;

import app.launchApplication;
import model.authentication.Authenticator;
import model.authentication.MapAuthenticator;
import model.dao.*;
import model.entities.ExtinctionStatut;
import model.entities.animals.*;
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
    private final Authenticator authenticator;
    private final AnimalRepository animalRepository;
    private final BiomeDAO biomeDAO;
    private final HabitatDAO habitatDAO;
    private final MammalDAO mammalDAO;
    private final BirdDAO birdDAO;
    private final FishDAO fishDAO;
    private final ReptileDAO reptileDAO;
    private final AmphibianDAO amphibianDAO;
    private final launchApplication application;

    private final AnimalBinaryDAO animalBinary = new AnimalBinaryDAO("data/backup/animals.dat");

    public controllerMAINVIEW(
            MAINVIEW view,
            Authenticator authenticator,
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

    public void deleteAnimal(Animal animal) {
        if (animal == null) return;

        switch (animal.getClass().getSimpleName()) {
            case "Mammal" -> mammalDAO.deleteByObject((Mammal) animal);
            case "Bird" -> birdDAO.deleteByObject((Bird) animal);
            case "Fish" -> fishDAO.deleteByObject((Fish) animal);
            case "Reptile" -> reptileDAO.deleteByObject((Reptile) animal);
            case "Amphibian" -> amphibianDAO.deleteByObject((Amphibian) animal);
        }

        refreshAnimals();
    }

    public void editAnimal(Animal animal) {
        if (animal == null) return;

        ADDANIMAL form = new ADDANIMAL(habitatDAO.findAll(), animal);
        new controllerADDANIMAL(
                form,
                mammalDAO,
                birdDAO,
                fishDAO,
                reptileDAO,
                amphibianDAO,
                animal
        );

        form.setVisible(true);
        refreshAnimals();
    }


    public void exportAnimalsToBinary() {
        List<Animal> allAnimals = animalRepository.findAll();
        animalBinary.saveToFile(allAnimals);
    }

    public void importAnimalsFromBinary() {
        List<Animal> importedAnimals = animalBinary.loadFromFile();
        if (importedAnimals != null) {
            for (Animal animal : importedAnimals) {
                switch (animal.getClass().getSimpleName()) {
                    case "Mammal" -> mammalDAO.create((Mammal) animal);
                    case "Bird" -> birdDAO.create((Bird) animal);
                    case "Fish" -> fishDAO.create((Fish) animal);
                    case "Reptile" -> reptileDAO.create((Reptile) animal);
                    case "Amphibian" -> amphibianDAO.create((Amphibian) animal);
                }
            }
            refreshAnimals();
        }
    }
}
