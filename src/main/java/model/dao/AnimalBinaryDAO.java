package model.dao;

import model.entities.animals.Animal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalBinaryDAO {

    private final File file;

    public AnimalBinaryDAO (String filePath) {
        this.file = new File(filePath);
    }

    public void saveToFile(List<Animal> animals) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(animals);
        } catch (IOException e) {
            System.err.println("Erreur IO lors de la sauvegarde des animaux");
        }
    }

    public List<Animal> loadFromFile() {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Animal>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur IO lors du chargement des animaux");
            return new ArrayList<>();
        }
    }

}
