package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

import java.io.Serializable;
import java.util.UUID;

public class Reptile extends Animal  implements Serializable  {

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

    public Reptile(UUID id, String imagePath, String name, float height, float weight, ExtinctionStatut status, Habitat habitat, String description, String reproduction, boolean venomous) {
        super(id, imagePath, name, height, weight, status, habitat, description);
        this.reproduction = reproduction;
        this.venomous = venomous;
    }
    @Override
    public String toString() {
        return super.toString() +
                ", Reptile{" +
                "reproduction='" + reproduction + '\'' +
                ", venomous=" + venomous +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reptile reptile)) return false;

        if (isVenomous() != reptile.isVenomous()) return false;
        return super.equals(reptile) && (getReproduction() != null ? getReproduction().equals(reptile.getReproduction()) : reptile.getReproduction() == null);
    }

    @Override
    public int hashCode() {
        int result = getReproduction() != null ? getReproduction().hashCode() : 0;
        result = 31 * result + (isVenomous() ? 1 : 0);
        return result;
    }
}
