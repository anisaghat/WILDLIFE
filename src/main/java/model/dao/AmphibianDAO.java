package model.dao;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;
import model.entities.animals.Amphibian;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AmphibianDAO implements IDAO<Amphibian, UUID> {

    private final File file;
    private final List<Amphibian> amphibians = new ArrayList<>();
    private final HabitatDAO habitatDAO;

    public AmphibianDAO(String filePath, HabitatDAO habitatDAO) {
        this.file = new File(filePath);
        this.habitatDAO = habitatDAO;
        loadFromFile();
    }

    private void loadFromFile() {
        amphibians.clear();
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

                UUID habitatId = token[6].isBlank() ? null : UUID.fromString(token[6].trim());
                Habitat habitat = habitatId == null ? null : habitatDAO.findById(habitatId);

                String typeRespiration = token[7].trim();
                boolean aquatique = Boolean.parseBoolean(token[8].trim());
                boolean metamorphose = Boolean.parseBoolean(token[9].trim());
                String description = token[10].trim();

                Amphibian amphibian = new Amphibian(id, imagePath, name, height, weight, status, habitat, typeRespiration, aquatique, metamorphose, description);
                amphibians.add(amphibian);
            }

        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        }
    }

    private void saveAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Amphibian a : amphibians) {
                String idStr = a.getId().toString();
                String imagePathStr = a.getImagePath() == null ? "" : a.getImagePath();
                String nameStr = a.getName() == null ? "" : a.getName();
                String heightStr = String.valueOf(a.getHeight());
                String weightStr = String.valueOf(a.getWeight());
                String statusStr = a.getStatus() == null ? "" : a.getStatus().toString();
                String habitatStr = a.getHabitat() == null ? "" : a.getHabitat().getId().toString();
                String typeRespirationStr = a.getTypeRespiration() == null ? "" : a.getTypeRespiration();
                String aquatiqueStr = String.valueOf(a.isAquatique());
                String metamorphoseStr = String.valueOf(a.isMetamorphose());
                String descriptionStr = a.getDescription() == null ? "" : a.getDescription();
                bw.write(idStr + "," + imagePathStr + "," + nameStr + "," + heightStr + "," + weightStr + "," + statusStr + "," + habitatStr + "," + typeRespirationStr + "," + aquatiqueStr + "," + metamorphoseStr + "," + descriptionStr);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        }
    }

    @Override
    public UUID create(Amphibian amphibian) {
        if (amphibian == null) return null;
        if (findById(amphibian.getId()) != null) return null;

        amphibians.add(amphibian);
        saveAll();
        return amphibian.getId();
    }

    @Override
    public boolean update(Amphibian amphibian) {
        if (amphibian == null) return false;

        for (int i = 0; i < amphibians.size(); i++) {
            if (amphibians.get(i).getId().equals(amphibian.getId())) {
                amphibians.set(i, amphibian);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(UUID id) {
        if (id == null) return false;

        for (int i = 0; i < amphibians.size(); i++) {
            if (amphibians.get(i).getId().equals(id)) {
                amphibians.remove(i);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByObject(Amphibian amphibian) {
        if (amphibian == null) return false;

        if (amphibians.remove(amphibian)) {
            saveAll();
            return true;
        }
        return false;
    }

    @Override
    public Amphibian findById(UUID id) {
        if (id == null) return null;

        for (Amphibian a : amphibians) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public List<Amphibian> findAll() {
        return new ArrayList<>(amphibians);
    }
}
