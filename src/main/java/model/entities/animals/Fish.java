package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

import java.io.Serializable;
import java.util.UUID;

public class Fish extends Animal implements Serializable {

    private String typeOfWater; // eau douce ou salée
    private String bones; // poisson osseux ou cartilagineux
    private  String depth; // profondeur à laquelle il vit?

    public String getTypeOfWater() {
        return typeOfWater;
    }

    public void setTypeOfWater(String typeOfWater) {
        this.typeOfWater = typeOfWater;
    }

    public String getBones() {
        return bones;
    }

    public void setBones(String bones) {
        this.bones = bones;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public Fish(UUID id, String imagePath, String name, float height, float weight, ExtinctionStatut status, String depth, Habitat habitat, String description, String typeOfWater, String bones) {
        super(id, imagePath, name, height, weight, status, habitat, description);
        this.typeOfWater = typeOfWater;
        this.bones = bones;
        this.depth = depth;
    }
    @Override
    public String toString() {
        return super.toString() +
                ", Fish{" +
                "typeOfWater='" + typeOfWater + '\'' +
                ", bones='" + bones + '\'' +
                ", depth='" + depth + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fish fish)) return false;

        if (getTypeOfWater() != null ? !getTypeOfWater().equals(fish.getTypeOfWater()) : fish.getTypeOfWater() != null)
            return false;
        if (getBones() != null ? !getBones().equals(fish.getBones()) : fish.getBones() != null) return false;
        return getDepth() != null ? getDepth().equals(fish.getDepth()) : fish.getDepth() == null;
    }

    @Override
    public int hashCode() {
        int result = getTypeOfWater() != null ? getTypeOfWater().hashCode() : 0;
        result = 31 * result + (getBones() != null ? getBones().hashCode() : 0);
        result = 31 * result + (getDepth() != null ? getDepth().hashCode() : 0);
        return result;
    }
}
