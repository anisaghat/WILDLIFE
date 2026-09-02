package model.dao;

import model.entities.Biome;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BiomeDAO implements IDAO<Biome, UUID> {

    private final File file;
    private final List<Biome> biomes = new ArrayList<>();

    public BiomeDAO(String filePath) {
        this.file = new File(filePath);
        loadFromFile();
    }

    private void loadFromFile() {
        biomes.clear();
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] token = line.split(",", -1);
                if (token.length < 6) continue;

                UUID id = UUID.fromString(token[0]);
                String name = token[1];
                String description = token[2];
                String temperature = token[3];
                String humidity = token[4];
                String dominantVegetation = token[5];

                Biome biome = new Biome(name, description, temperature, humidity, dominantVegetation);
                biome.setId(id);

                biomes.add(biome);
            }
        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        }
    }

    private void saveAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Biome b : biomes) {
                String idStr = b.getId().toString();
                String nameStr = b.getName() == null ? "" : b.getName();
                String descriptionStr = b.getDescription() == null ? "" : b.getDescription();
                String temperatureStr = b.getTemperature() == null ? "" : b.getTemperature();
                String humidityStr = b.getHumidity() == null ? "" : b.getHumidity();
                String dominantVegetationStr = b.getDominantVegetation() == null ? "" : b.getDominantVegetation();

                bw.write(idStr + "," + nameStr + "," + descriptionStr + "," + temperatureStr + "," + humidityStr + "," + dominantVegetationStr);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        }
    }

    @Override
    public UUID create(Biome biome) {
        if (biome == null) return null;
        if (biome.getId() == null) {
            biome.setId(UUID.randomUUID());
        }

        if(findById(biome.getId()) != null) return null;

        biomes.add(biome);
        saveAll();
        return biome.getId();
    }

    @Override
    public boolean update(Biome b) {
        for (int i = 0; i < biomes.size(); i++) {
            if (biomes.get(i).getId().equals(b.getId())) {
                biomes.set(i, b);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(UUID id) {
        for (int i = 0; i < biomes.size(); i++) {
            if (biomes.get(i).getId().equals(id)) {
                biomes.remove(i);
                saveAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByObject(Biome biome) {
        if (biomes.remove(biome)) {
            saveAll();
            return true;
        }
        return false;
    }

    @Override
    public Biome findById(UUID id) {
        for (Biome b : biomes) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public List<Biome> findAll() {
        return biomes;
    }

}
