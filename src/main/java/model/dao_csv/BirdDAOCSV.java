package model.dao_csv;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;
import model.entities.animals.Bird;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BirdDAOCSV implements IDAO<Bird> {

    private final File file;
    private final List<Bird> birds = new ArrayList<>();
    private final HabitatDAOCSV habitatDAO;

    public BirdDAOCSV(String filePath, HabitatDAOCSV habitatDAO) {
        this.file = new File(filePath);
        this.habitatDAO = habitatDAO;
        loadFromFile();
    }

    private void loadFromFile() {
        birds.clear();
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] token = line.split(",", -1);
                if (token.length < 10) continue;

                Integer id = Integer.parseInt(token[0].trim());
                String imagePath = token[1].trim();
                String name = token[2].trim();
                float height = Float.parseFloat(token[3].trim());
                float weight = Float.parseFloat(token[4].trim());
                ExtinctionStatut status = token[5].isBlank() ? null : ExtinctionStatut.valueOf(token[5].trim());

                Integer habitatId = token[6].isBlank() ? null : Integer.parseInt(token[6].trim());
                Habitat habitat = habitatId == null ? null : habitatDAO.findById(habitatId);

                String description = token[7].trim();
                float wingspan = Float.parseFloat(token[8].trim());
                boolean canFly = Boolean.parseBoolean(token[9].trim());

                Bird bird = new Bird(id, imagePath, name, height, weight, status, habitat, description, wingspan, canFly);
                birds.add(bird);
            }

        } catch (IOException e) {
            System.err.println("Erreur IO");
        }
    }

    private void saveAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Bird b : birds) {
                String idStr = b.getId().toString();
                String imagePathStr = b.getImagePath() == null ? "" : b.getImagePath();
                String nameStr = b.getName() == null ? "" : b.getName();
                String heightStr = String.valueOf(b.getHeight());
                String weightStr = String.valueOf(b.getWeight());
                String statusStr = b.getStatus() == null ? "" : b.getStatus().toString();
                String habitatStr = b.getHabitat() == null ? "" : b.getHabitat().getId().toString();
                String descriptionStr = b.getDescription() == null ? "" : b.getDescription();                String wingspanStr = String.valueOf(b.getWingspan());
                String canFlyStr = String.valueOf(b.isCanFly());

                bw.write(idStr + "," + imagePathStr + "," + nameStr + "," + heightStr + "," + weightStr + "," + statusStr + "," + habitatStr + "," + descriptionStr + "," + wingspanStr + "," + canFlyStr);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur IO");
        }
    }

    @Override
    public int create(Bird bird) {
        if (bird == null) return -1;
        if (findById(bird.getId()) != null) return -1;

        birds.add(bird);
        saveAll();
        return bird.getId();
    }

    @Override
    public boolean update(Bird bird) {
        if (bird == null) return false;

        for (int i = 0; i < birds.size(); i++) {
            if (birds.get(i).getId().equals(bird.getId())) {
                birds.set(i, bird);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        if (id == null) return false;

        for (int i = 0; i < birds.size(); i++) {
            if (birds.get(i).getId().equals(id)) {
                birds.remove(i);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByObject(Bird bird) {
        if (bird == null) return false;

        if (birds.remove(bird)) {
            saveAll();
            return true;
        }
        return false;
    }

    @Override
    public Bird findById(Integer id) {
        if (id == null) return null;

        for (Bird b : birds) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public List<Bird> findAll() {
        return new ArrayList<>(birds);
    }
}