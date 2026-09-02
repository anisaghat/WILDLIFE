package controller;

import model.dao.BiomeDAO;
import model.entities.Biome;
import view.ADDBIOME;

public class controllerADDBIOME {

    private final ADDBIOME view;
    private final BiomeDAO biomeDAO;

    public controllerADDBIOME(ADDBIOME view, BiomeDAO biomeDAO) {
        this.view = view;
        this.biomeDAO = biomeDAO;

        view.getSaveButton().addActionListener(
                e -> saveBiome()
        );
    }

    private void saveBiome() {

        String name = view.getBiomeName();
        String description = view.getDescription();
        String humidity = view.getHumidity();
        String temperature = view.getTemperature();
        String dominantVegetation = view.getDominantVegetation();

        Biome biome = new Biome(name, description, temperature, humidity, dominantVegetation);
        biomeDAO.create(biome);
        view.dispose();
    }

}
