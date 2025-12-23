package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

public abstract class Animal
{
    private int id;
    private String imagePath;
    private String name;
    private float height;
    private float weight;
    private ExtinctionStatut status;
    private Habitat habitat;

    private String description;

    public Animal(int id, String imagePath, String name, float height, float weight, ExtinctionStatut status, Habitat habitat, String description) {
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.status = status;
        this.habitat = habitat;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public ExtinctionStatut getStatus() {
        return status;
    }

    public void setStatus(ExtinctionStatut status) {
        this.status = status;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }
}
