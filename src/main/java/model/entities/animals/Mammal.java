package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

import java.io.Serializable;
import java.util.UUID;

public class Mammal extends Animal implements Serializable
{
    private float dureeGestation;

    public float getDureeGestation() {
        return dureeGestation;
    }

    public void setDureeGestation(float dureeGestation) {
        this.dureeGestation = dureeGestation;
    }

    public Mammal(UUID id, String imagePath, String name, float height, float weight, ExtinctionStatut status, Habitat habitat, String description, float dureeGestation) {
        super(id, imagePath, name, height, weight, status, habitat, description);
        this.dureeGestation = dureeGestation;
    }
    @Override
    public String toString() {
        return super.toString() +
                ", Mammal{" +
                "dureeGestation=" + dureeGestation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mammal mammal)) return false;

        return Float.compare(getDureeGestation(), mammal.getDureeGestation()) == 0;
    }

    @Override
    public int hashCode() {
        return (getDureeGestation() != 0.0f ? Float.floatToIntBits(getDureeGestation()) : 0);
    }
}
