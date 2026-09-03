package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

import java.io.Serializable;
import java.util.UUID;

public class Bird extends Animal implements Serializable
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

    public Bird(UUID id, String imagePath, String name, float height, float weight, ExtinctionStatut status, Habitat habitat, String description, float wingspan, boolean canFly) {
        super(id, imagePath, name, height, weight, status, habitat, description);
        this.wingspan = wingspan;
        this.canFly = canFly;
    }
    @Override
    public String toString() {
        return super.toString() +
                ", Bird{" +
                "wingspan=" + wingspan +
                ", canFly=" + canFly +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bird bird)) return false;

        if (Float.compare(getWingspan(), bird.getWingspan()) != 0) return false;
        return isCanFly() == bird.isCanFly();
    }

    @Override
    public int hashCode() {
        int result = (getWingspan() != 0.0f ? Float.floatToIntBits(getWingspan()) : 0);
        result = 31 * result + (isCanFly() ? 1 : 0);
        return result;
    }
}
