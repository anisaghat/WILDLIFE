package model.dao;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;
import model.entities.animals.Reptile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReptileDAO implements IDAO<Reptile, UUID> {

    private final File file;
    private final List<Reptile> reptiles = new ArrayList<>();
    private final HabitatDAO habitatDAO;

    public ReptileDAO(String filePath, HabitatDAO habitatDAO) {
        this.file = new File(filePath);
        this.habitatDAO = habitatDAO;
        loadFromFile();
    }

    private void loadFromFile() {
        reptiles.clear();
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] token = line.split(",", -1);
                if (token.length < 10) continue;

                UUID id = UUID.fromString(token[0].trim());
                String imagePath = token[1].trim();
                String name = token[2].trim();
                float height = Float.parseFloat(token[3].trim());
                float weight = Float.parseFloat(token[4].trim());
                ExtinctionStatut status = token[5].isBlank() ? null : ExtinctionStatut.valueOf(token[5].trim());

                UUID habitatId = token[6].isBlank() ? null : UUID.fromString(token[6].trim());
                Habitat habitat = habitatId == null ? null : habitatDAO.findById(habitatId);

                String description = token[7].trim();
                String reproduction = token[8].trim();
                boolean venomous = Boolean.parseBoolean(token[9].trim());

                Reptile reptile = new Reptile(id, imagePath, name, height, weight, status, habitat, description, reproduction, venomous);
                reptiles.add(reptile);
            }

        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        }
    }

    private void saveAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Reptile r : reptiles) {
                String idStr = r.getId().toString();
                String imagePathStr = r.getImagePath() == null ? "" : r.getImagePath();
                String nameStr = r.getName() == null ? "" : r.getName();
                String heightStr = String.valueOf(r.getHeight());
                String weightStr = String.valueOf(r.getWeight());
                String statusStr = r.getStatus() == null ? "" : r.getStatus().toString();
                String habitatStr = r.getHabitat() == null ? "" : r.getHabitat().getId().toString();
                String descriptionStr = r.getDescription() == null ? "" : r.getDescription();                String reproductionStr = r.getReproduction() == null ? "" : r.getReproduction();
                String venomousStr = String.valueOf(r.isVenomous());

                bw.write(idStr + "," + imagePathStr + "," + nameStr + "," + heightStr + "," + weightStr + "," + statusStr + "," + habitatStr + "," + descriptionStr + "," + reproductionStr + "," + venomousStr);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        }
    }

    @Override
    public UUID create(Reptile reptile) {
        if (reptile == null) return null;
        if (findById(reptile.getId()) != null) return null;

        reptiles.add(reptile);
        saveAll();
        return reptile.getId();
    }

    @Override
    public boolean update(Reptile reptile) {
        if (reptile == null) return false;

        for (int i = 0; i < reptiles.size(); i++) {
            if (reptiles.get(i).getId().equals(reptile.getId())) {
                reptiles.set(i, reptile);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(UUID id) {
        if (id == null) return false;

        for (int i = 0; i < reptiles.size(); i++) {
            if (reptiles.get(i).getId().equals(id)) {
                reptiles.remove(i);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByObject(Reptile reptile) {
        if (reptile == null) return false;

        if (reptiles.remove(reptile)) {
            saveAll();
            return true;
        }
        return false;
    }

    @Override
    public Reptile findById(UUID id) {
        if (id == null) return null;

        for (Reptile r : reptiles) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public List<Reptile> findAll() {
        return new ArrayList<>(reptiles);
    }
}
