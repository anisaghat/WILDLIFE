package controller;

import app.AppNavigator;
import model.dao.AnimalDAO;
import model.entities.ExtinctionStatut;
import model.entities.animals.Animal;
import view.MAINVIEW;

import java.util.ArrayList;
import java.util.List;

public class controllerMAINVIEW {
    private final MAINVIEW view;
    private final AppNavigator navigator;
    private final AnimalDAO animalDAO;

    public controllerMAINVIEW(MAINVIEW view, AppNavigator navigator) {
        this.view = view;
        this.navigator = navigator;
        this.animalDAO = new AnimalDAO();

        view.setController(this);
    }

    public void showEndangeredAnimals() {
        List<Animal> endangeredAnimals = new ArrayList<>();

        for (Animal animal : animalDAO.getAllAnimals()) {
            if (animal.getStatus() == ExtinctionStatut.ENDANGERED) {
                endangeredAnimals.add(animal);
            }
        }

        navigator.showEndangeredAnimals(endangeredAnimals);
    }
}
