package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Amphibian extends Animal implements Serializable {

    // les amphibiens ont une double vie eau/terre

    private String typeRespiration; // cutanée, pulmonaire, mixte

    private boolean aquatique;

    private boolean metamorphose;

    public String getTypeRespiration() {
        return typeRespiration;
    }

    public void setTypeRespiration(String typeRespiration) {
        this.typeRespiration = typeRespiration;
    }

    public boolean isAquatique() {
        return aquatique;
    }

    public void setAquatique(boolean aquatique) {
        this.aquatique = aquatique;
    }

    public boolean isMetamorphose() {
        return metamorphose;
    }

    public void setMetamorphose(boolean metamorphose) {
        this.metamorphose = metamorphose;
    }

    public Amphibian(UUID id, String imagePath, String name, float height, float weight, ExtinctionStatut status, Habitat habitat, String typeRespiration, boolean aquatique, boolean metamorphose, String description) {
        super(id, imagePath, name, height, weight, status, habitat, description);
        this.typeRespiration = typeRespiration;
        this.aquatique = aquatique;
        this.metamorphose = metamorphose;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Amphibian{" +
                "typeRespiration='" + typeRespiration + '\'' +
                ", aquatique=" + aquatique +
                ", metamorphose=" + metamorphose +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amphibian amphibian)) return false;
        return isAquatique() == amphibian.isAquatique() && isMetamorphose() == amphibian.isMetamorphose() && Objects.equals(getTypeRespiration(), amphibian.getTypeRespiration());
    }

}
