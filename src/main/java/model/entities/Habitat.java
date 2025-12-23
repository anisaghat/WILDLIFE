package model.entities;

public class Habitat {

    private String name;
    private Biome biome;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public Habitat(String name, Biome biome) {
        this.name = name;
        this.biome = biome;
    }


}

