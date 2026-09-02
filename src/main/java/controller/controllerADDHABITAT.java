package controller;

import model.dao.HabitatDAO;
import model.entities.Biome;
import model.entities.Habitat;
import view.ADDNEWHABITAT;

import javax.swing.*;

public class controllerADDHABITAT {
    private final ADDNEWHABITAT view;
    private final HabitatDAO habitatDAO;

    public controllerADDHABITAT(ADDNEWHABITAT view, HabitatDAO habitatDAO) {
        this.view = view;
        this.habitatDAO = habitatDAO;

        view.getSaveButton().addActionListener(
                e -> saveHabitat()
        );
    }

    private void saveHabitat() {
        String name = view.getName();
        Biome biome = view.getSelectedBiome();

        Habitat habitat = new Habitat(name, biome);
        habitatDAO.create(habitat);
        view.dispose();
    }

}
