package model.dao_csv;

import model.entities.Biome;
import model.entities.Habitat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HabitatDAOCSV implements IDAO<Habitat> {

    private final File file;
    private final List<Habitat> habitats = new ArrayList<>();
    private final BiomeDAOCSV biomeDAO;

    public HabitatDAOCSV(String filePath, BiomeDAOCSV biomeDAO) {
        this.file = new File(filePath);
        this.biomeDAO = biomeDAO;
        loadFromFile();
    }

    private void loadFromFile() {
        habitats.clear();
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] token = line.split(",", -1);
                if (token.length < 3) continue;

                Integer id = Integer.parseInt(token[0].trim());
                String name = token[1].trim();

                Integer biomeId = token[2].isBlank() ? null : Integer.parseInt(token[2].trim());
                Biome biome = biomeId == null ? null : biomeDAO.findById(biomeId);

                Habitat habitat = new Habitat(id, name, biome);
                habitats.add(habitat);
            }

        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        }
    }

    private void saveAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Habitat h : habitats) {
                String idStr = h.getId().toString();
                String nameStr = h.getName() == null ? "" : h.getName();
                String biomeStr = h.getBiome() == null ? "" : h.getBiome().getId().toString();

                bw.write(idStr + "," + nameStr + "," + biomeStr);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        }
    }

    @Override
    public int create(Habitat habitat) {
        if (habitat == null) return -1;
        if (findById(habitat.getId()) != null) return -1;

        habitats.add(habitat);
        saveAll();
        return habitat.getId();
    }

    @Override
    public boolean update(Habitat h) {
        if (h == null) return false;

        for (int i = 0; i < habitats.size(); i++) {
            if (habitats.get(i).getId().equals(h.getId())) {
                habitats.set(i, h);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        if (id == null) return false;

        for (int i = 0; i < habitats.size(); i++) {
            if (habitats.get(i).getId().equals(id)) {
                habitats.remove(i);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByObject(Habitat habitat) {
        if (habitat == null) return false;

        if (habitats.remove(habitat)) {
            saveAll();
            return true;
        }
        return false;
    }

    @Override
    public Habitat findById(Integer id) {
        if (id == null) return null;

        for (Habitat h : habitats) {
            if (h.getId().equals(id)) {
                return h;
            }
        }
        return null;
    }

    @Override
    public List<Habitat> findAll() {
        return new ArrayList<>(habitats);
    }
}