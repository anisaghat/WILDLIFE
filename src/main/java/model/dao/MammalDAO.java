package model.dao;

import model.entities.ExtinctionStatut;
import model.entities.Habitat;
import model.entities.animals.Mammal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MammalDAO implements IDAO<Mammal, UUID> {

    private final File file;
    private final List<Mammal> mammals = new ArrayList<>();
    private final HabitatDAO habitatDAO;

    public MammalDAO(String filePath, HabitatDAO habitatDAO) {
        this.file = new File(filePath);
        this.habitatDAO = habitatDAO;
        loadFromFile();
    }

    private void loadFromFile() {
        mammals.clear();
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] token = line.split(",", -1);
                if (token.length < 9) continue;

                UUID id = UUID.fromString(token[0].trim());
                String imagePath = token[1].trim();
                String name = token[2].trim();
                float height = Float.parseFloat(token[3].trim());
                float weight = Float.parseFloat(token[4].trim());
                ExtinctionStatut status = token[5].isBlank() ? null : ExtinctionStatut.valueOf(token[5].trim());

                UUID habitatId = token[6].isBlank() ? null : UUID.fromString(token[6].trim());
                Habitat habitat = habitatId == null ? null : habitatDAO.findById(habitatId);

                String description = token[7].trim();
                float dureeGestation = Float.parseFloat(token[8].trim());

                Mammal mammal = new Mammal(id, imagePath, name, height, weight, status, habitat, description, dureeGestation);
                mammals.add(mammal);
            }

        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        }
    }

    private void saveAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Mammal m : mammals) {
                String idStr = m.getId().toString();
                String imagePathStr = m.getImagePath() == null ? "" : m.getImagePath();
                String nameStr = m.getName() == null ? "" : m.getName();
                String heightStr = String.valueOf(m.getHeight());
                String weightStr = String.valueOf(m.getWeight());
                String statusStr = m.getStatus() == null ? "" : m.getStatus().toString();
                String habitatStr = m.getHabitat() == null ? "" : m.getHabitat().getId().toString();
                String descriptionStr = m.getDescription() == null ? "" : m.getDescription();                String dureeGestationStr = String.valueOf(m.getDureeGestation());

                bw.write(idStr + "," + imagePathStr + "," + nameStr + "," + heightStr + "," + weightStr + "," + statusStr + "," + habitatStr + "," + descriptionStr + "," + dureeGestationStr);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        }
    }

    @Override
    public UUID create(Mammal mammal) {
        if (mammal == null) return null;
        if (findById(mammal.getId()) != null) return null;

        mammals.add(mammal);
        saveAll();
        return mammal.getId();
    }

    @Override
    public boolean update(Mammal mammal) {
        if (mammal == null) return false;

        for (int i = 0; i < mammals.size(); i++) {
            if (mammals.get(i).getId().equals(mammal.getId())) {
                mammals.set(i, mammal);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(UUID id) {
        if (id == null) return false;

        for (int i = 0; i < mammals.size(); i++) {
            if (mammals.get(i).getId().equals(id)) {
                mammals.remove(i);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByObject(Mammal mammal) {
        if (mammal == null) return false;

        if (mammals.remove(mammal)) {
            saveAll();
            return true;
        }
        return false;
    }

    @Override
    public Mammal findById(UUID id) {
        if (id == null) return null;

        for (Mammal m : mammals) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Mammal> findAll() {
        return new ArrayList<>(mammals);
    }
}
