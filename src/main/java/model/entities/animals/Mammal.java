package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

public class Mammal extends Animal
{
    private float dureeGestation;

    public float getDureeGestation() {
        return dureeGestation;
    }

    public void setDureeGestation(float dureeGestation) {
        this.dureeGestation = dureeGestation;
    }

    public Mammal(int id, String imagePath, String name, float height, float weight, ExtinctionStatut status, Habitat habitat, String description, float dureeGestation) {
        super(id, imagePath, name, height, weight, status, habitat, description);
        this.dureeGestation = dureeGestation;
    }
}
