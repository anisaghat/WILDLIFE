package model.dao;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;
import model.entities.animals.Fish;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FishDAO implements IDAO<Fish, UUID> {

    private final File file;
    private final List<Fish> fishes = new ArrayList<>();
    private final HabitatDAO habitatDAO;

    public FishDAO(String filePath, HabitatDAO habitatDAO) {
        this.file = new File(filePath);
        this.habitatDAO = habitatDAO;
        loadFromFile();
    }

    private void loadFromFile() {
        fishes.clear();
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] token = line.split(",", -1);
                if (token.length < 11) continue;

                UUID id = UUID.fromString(token[0].trim());
                String imagePath = token[1].trim();
                String name = token[2].trim();
                float height = Float.parseFloat(token[3].trim());
                float weight = Float.parseFloat(token[4].trim());
                ExtinctionStatut status = token[5].isBlank() ? null : ExtinctionStatut.valueOf(token[5].trim());
                String depth = token[6].trim();

                UUID habitatId = token[7].isBlank() ? null : UUID.fromString(token[7].trim());
                Habitat habitat = habitatId == null ? null : habitatDAO.findById(habitatId);

                String description = token[8].trim();
                String typeOfWater = token[9].trim();
                String bones = token[10].trim();

                Fish fish = new Fish(id, imagePath, name, height, weight, status, depth, habitat, description, typeOfWater, bones);
                fishes.add(fish);
            }

        } catch (IOException e) {
            System.err.println("Erreur IO");
        }
    }

    private void saveAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Fish f : fishes) {
                String idStr = f.getId().toString();
                String imagePathStr = f.getImagePath() == null ? "" : f.getImagePath();
                String nameStr = f.getName() == null ? "" : f.getName();
                String heightStr = String.valueOf(f.getHeight());
                String weightStr = String.valueOf(f.getWeight());
                String statusStr = f.getStatus() == null ? "" : f.getStatus().toString();
                String depthStr = f.getDepth() == null ? "" : f.getDepth();
                String habitatStr = f.getHabitat() == null ? "" : f.getHabitat().getId().toString();
                String descriptionStr = f.getDescription() == null ? "" : f.getDescription();                String typeOfWaterStr = f.getTypeOfWater() == null ? "" : f.getTypeOfWater();
                String bonesStr = f.getBones() == null ? "" : f.getBones();

                bw.write(idStr + "," + imagePathStr + "," + nameStr + "," + heightStr + "," + weightStr + "," + statusStr + "," + depthStr + "," + habitatStr + "," + descriptionStr + "," + typeOfWaterStr + "," + bonesStr);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur IO");
        }
    }

    @Override
    public UUID create(Fish fish) {
        if (fish == null) return null;
        if (findById(fish.getId()) != null) return null;

        fishes.add(fish);
        saveAll();
        return fish.getId();
    }

    @Override
    public boolean update(Fish fish) {
        if (fish == null) return false;

        for (int i = 0; i < fishes.size(); i++) {
            if (fishes.get(i).getId().equals(fish.getId())) {
                fishes.set(i, fish);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(UUID id) {
        if (id == null) return false;

        for (int i = 0; i < fishes.size(); i++) {
            if (fishes.get(i).getId().equals(id)) {
                fishes.remove(i);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByObject(Fish fish) {
        if (fish == null) return false;

        if (fishes.remove(fish)) {
            saveAll();
            return true;
        }
        return false;
    }

    @Override
    public Fish findById(UUID id) {
        if (id == null) return null;

        for (Fish f : fishes) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    @Override
    public List<Fish> findAll() {
        return new ArrayList<>(fishes);
    }
}
