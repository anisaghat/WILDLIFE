package model.entities;

import java.util.UUID;

public class Biome {

    private UUID id;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Biome{" +
                "name='" + name + '\'' +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", dominantVegetation='" + dominantVegetation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Biome biome)) return false;

        if (getName() != null ? !getName().equals(biome.getName()) : biome.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(biome.getDescription()) : biome.getDescription() != null)
            return false;
        if (getTemperature() != null ? !getTemperature().equals(biome.getTemperature()) : biome.getTemperature() != null)
            return false;
        if (getHumidity() != null ? !getHumidity().equals(biome.getHumidity()) : biome.getHumidity() != null)
            return false;
        return getDominantVegetation() != null ? getDominantVegetation().equals(biome.getDominantVegetation()) : biome.getDominantVegetation() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getTemperature() != null ? getTemperature().hashCode() : 0);
        result = 31 * result + (getHumidity() != null ? getHumidity().hashCode() : 0);
        result = 31 * result + (getDominantVegetation() != null ? getDominantVegetation().hashCode() : 0);
        return result;
    }
}
