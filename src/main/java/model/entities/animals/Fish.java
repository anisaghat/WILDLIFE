package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

public class Fish extends Animal {

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

    public Fish(int id, String imagePath, String name, float height, float weight, ExtinctionStatut status, String depth, Habitat habitat, String description, String typeOfWater, String bones) {
        super(id, imagePath, name, height, weight, status, habitat, description);
        this.typeOfWater = typeOfWater;
        this.bones = bones;
        this.depth = depth;
    }
}
