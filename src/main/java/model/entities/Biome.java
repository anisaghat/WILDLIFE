package model.entities;

public class Biome {

    private String name;
    private String description;
    private String temperature;
    private String humidity; // précipitation??
    private String dominantVegetation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDominantVegetation() {
        return dominantVegetation;
    }

    public void setDominantVegetation(String dominantVegetation) {
        this.dominantVegetation = dominantVegetation;
    }

    public Biome(String name, String description, String temperature, String humidity, String dominantVegetation) {
        this.name = name;
        this.description = description;
        this.temperature = temperature;
        this.humidity = humidity;
        this.dominantVegetation = dominantVegetation;
    }
}
