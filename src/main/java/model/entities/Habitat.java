package model.entities;

import java.util.UUID;

public class Habitat {

    private UUID id;
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
    public UUID getId() { return id; }

    public void setId(UUID n) { id = n; }

    public Habitat(String name, Biome biome) {
        this.id=UUID.randomUUID();
        this.name=name;
        this.biome=biome;
    }

    @Override
    public String toString()
    {
        return "Habitat{" +
                "name='" + name + '\'' +
                "," + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Habitat habitat)) return false;

        if (getName() != null ? !getName().equals(habitat.getName()) : habitat.getName() != null) return false;
        return getBiome() != null ? getBiome().equals(habitat.getBiome()) : habitat.getBiome() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getBiome() != null ? getBiome().hashCode() : 0);
        return result;
    }
}

