package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

public class Reptile extends Animal {

    private String reproduction; // ovipare, vivipare, ovovivipare
    private boolean venomous;

    public String getReproduction() {
        return reproduction;
    }

    public void setReproduction(String reproduction) {
        this.reproduction = reproduction;
    }

    public boolean isVenomous() {
        return venomous;
    }

    public void setVenomous(boolean venomous) {
        this.venomous = venomous;
    }

    public Reptile(int id, String imagePath, String name, float height, float weight, ExtinctionStatut status, Habitat habitat, String description, String reproduction, boolean venomous) {
        super(id, imagePath, name, height, weight, status, habitat, description);
        this.reproduction = reproduction;
        this.venomous = venomous;
    }
}
