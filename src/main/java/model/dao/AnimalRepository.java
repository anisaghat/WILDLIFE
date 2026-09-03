package model.dao;

import model.entities.animals.*;

import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {

    private final MammalDAO mammalDAO;
    private final BirdDAO birdDAO;
    private final FishDAO fishDAO;
    private final ReptileDAO reptileDAO;
    private final AmphibianDAO amphibianDAO;

    public AnimalRepository(
            MammalDAO mammalDAO,
            BirdDAO birdDAO,
            FishDAO fishDAO,
            ReptileDAO reptileDAO,
            AmphibianDAO amphibianDAO
    ) {
        this.mammalDAO = mammalDAO;
        this.birdDAO = birdDAO;
        this.fishDAO = fishDAO;
        this.reptileDAO = reptileDAO;
        this.amphibianDAO = amphibianDAO;
    }

    public List<Animal> findAll() {

        List<Animal> animals = new ArrayList<>();

        animals.addAll(mammalDAO.findAll());
        animals.addAll(birdDAO.findAll());
        animals.addAll(fishDAO.findAll());
        animals.addAll(reptileDAO.findAll());
        animals.addAll(amphibianDAO.findAll());

        return animals;
    }


}