package model.entities.animals;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;

public class Amphibian extends Animal {

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

    public Amphibian(int id, String imagePath, String name, float height, float weight, ExtinctionStatut status, Habitat habitat, String typeRespiration, boolean aquatique, boolean metamorphose, String description) {
        super(id, imagePath, name, height, weight, status, habitat, description);
        this.typeRespiration = typeRespiration;
        this.aquatique = aquatique;
        this.metamorphose = metamorphose;
    }
}
