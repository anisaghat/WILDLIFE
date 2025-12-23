package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

public class Bird extends Animal
{
    private float wingspan;

    private boolean canFly; // faire 1 interface; idk à rélféchir

    public float getWingspan() {
        return wingspan;
    }

    public void setWingspan(float wingspan) {
        this.wingspan = wingspan;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public Bird(int id, String imagePath, String name, float height, float weight, ExtinctionStatut status, Habitat habitat, String description, float wingspan, boolean canFly) {
        super(id, imagePath, name, height, weight, status, habitat, description);
        this.wingspan = wingspan;
        this.canFly = canFly;
    }
}
