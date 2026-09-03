package controller;

import model.dao.AmphibianDAO;
import model.dao.BirdDAO;
import model.dao.FishDAO;
import model.dao.MammalDAO;
import model.dao.ReptileDAO;
import model.entities.ExtinctionStatut;
import model.entities.Habitat;
import model.entities.animals.Amphibian;
import model.entities.animals.Animal;
import model.entities.animals.Bird;
import model.entities.animals.Fish;
import model.entities.animals.Mammal;
import model.entities.animals.Reptile;
import view.ADDANIMAL;

import java.util.UUID;

public class controllerADDANIMAL {
    private final ADDANIMAL view;
    private final MammalDAO mammalDAO;
    private final BirdDAO birdDAO;
    private final FishDAO fishDAO;
    private final ReptileDAO reptileDAO;
    private final AmphibianDAO amphibianDAO;
    private Animal animalToEdit;

    public controllerADDANIMAL(
            ADDANIMAL view,
            MammalDAO mammalDAO,
            BirdDAO birdDAO,
            FishDAO fishDAO,
            ReptileDAO reptileDAO,
            AmphibianDAO amphibianDAO
    ) {
        this.view = view;
        this.mammalDAO = mammalDAO;
        this.birdDAO = birdDAO;
        this.fishDAO = fishDAO;
        this.reptileDAO = reptileDAO;
        this.amphibianDAO = amphibianDAO;

        view.getSaveButton().addActionListener(
                e -> saveAnimal()
        );
    }

    public controllerADDANIMAL (
            ADDANIMAL view,
            MammalDAO mammalDAO,
            BirdDAO birdDAO,
            FishDAO fishDAO,
            ReptileDAO reptileDAO,
            AmphibianDAO amphibianDAO,
            Animal animalToEdit
    ) {
        this(view, mammalDAO, birdDAO, fishDAO, reptileDAO, amphibianDAO);
        this.animalToEdit = animalToEdit;
        view.getSaveButton().addActionListener(
                e -> saveAnimal()
        );
    }

    private void saveAnimal() {
        Animal animal = null;
        // la ligne en dessous dit en gros si ya 1 animaltoedit tu reprends son id sinon t'en crée un nv pcq cv dire qu'on est en mode création
        UUID animalId = animalToEdit != null ? animalToEdit.getId() : UUID.randomUUID();

        String name = view.getAnimalNameFromForm();
        String height = view.getAnimalHeightFromForm();
        String weight = view.getAnimalWeightFromForm();
        String description = view.getAnimalDescriptionFromForm();
        String imagePath = view.getAnimalImagePathFromForm();
        String species = view.getSelectedSpecies();
        String status = view.getSelectedStatus();
        Habitat habitat = view.getSelectedHabitat();
        float animalHeight = Float.parseFloat(height);
        float animalWeight = Float.parseFloat(weight);
        ExtinctionStatut extinctionStatut = ExtinctionStatut.valueOf(status);

        switch(species) {
            case "mammal":
                float gestation = Float.parseFloat(view.getGestationFromForm());
                animal = new Mammal(
                        animalId,
                        imagePath,
                        name,
                        animalHeight,
                        animalWeight,
                        extinctionStatut,
                        habitat,
                        description,
                        gestation
                );
                break;

            case "bird":
                float wingspan = Float.parseFloat(view.getWingspanFromForm());
                boolean canFly = view.getCanFlyFromForm();
                animal = new Bird(
                        animalId,
                        imagePath,
                        name,
                        animalHeight,
                        animalWeight,
                        extinctionStatut,
                        habitat,
                        description,
                        wingspan,
                        canFly
                );
                break;

            case "fish":
                String waterType = view.getWaterTypeFromForm();
                String bones = view.getBonesFromForm();
                String depth = view.getDepthFromForm();
                animal = new Fish(
                        animalId,
                        imagePath,
                        name,
                        animalHeight,
                        animalWeight,
                        extinctionStatut,
                        depth,
                        habitat,
                        description,
                        waterType,
                        bones
                );
                break;

            case "reptile":
                String reproduction = view.getReproductionFromForm();
                boolean venomous = view.getVenomousFromForm();
                animal = new Reptile(
                        animalId,
                        imagePath,
                        name,
                        animalHeight,
                        animalWeight,
                        extinctionStatut,
                        habitat,
                        description,
                        reproduction,
                        venomous
                );
                break;

            case "amphibian":
                String respiration = view.getRespirationFromForm();
                boolean aquatic = view.getAquaticFromForm();
                boolean metamorphosis = view.getMetamorphosisFromForm();
                animal = new Amphibian(
                        animalId,
                        imagePath,
                        name,
                        animalHeight,
                        animalWeight,
                        extinctionStatut,
                        habitat,
                        respiration,
                        aquatic,
                        metamorphosis,
                        description
                );
                break;
        }

        if (animal instanceof Mammal mammal) {
            if (animalToEdit != null) {
                mammalDAO.update(mammal);
            } else {
                mammalDAO.create(mammal);
            }
        } else if (animal instanceof Bird bird) {
            if (animalToEdit != null) {
                birdDAO.update(bird);
            } else {
                birdDAO.create(bird);
            }
        } else if (animal instanceof Fish fish) {
            if (animalToEdit != null) {
                fishDAO.update(fish);
            } else {
                fishDAO.create(fish);
            }
        } else if (animal instanceof Reptile reptile) {
            if (animalToEdit != null) {
                reptileDAO.update(reptile);
            } else {
                reptileDAO.create(reptile);
            }
        } else if (animal instanceof Amphibian amphibian) {
            if (animalToEdit != null) {
                amphibianDAO.update(amphibian);
            } else {
                amphibianDAO.create(amphibian);
            }
        }

        if (animal != null) {
            view.dispose();
        }
    }
}
