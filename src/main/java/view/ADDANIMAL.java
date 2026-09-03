package view;

import model.entities.Habitat;
import model.entities.animals.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ADDANIMAL extends JDialog
{
    private JTextField nameField;
    private JTextField heightField;
    private JTextField weightField;
    private JComboBox statusComboBox;
    JComboBox<Habitat> habitatComboBox;
    private JTextField descriptionField;
    private JComboBox speciesComboBox;
    private JTextField imagepathField;
    private JButton saveButton;

    // Champs spécifiques Mammal
    private JTextField gestationField;

    // Champs spécifiques Bird
    private JTextField wingspanField;
    private JCheckBox canFlyCheckBox;

    // Champs spécifiques Fish
    private JComboBox<String> waterTypeComboBox;
    private JComboBox<String> bonesComboBox;
    private JTextField depthField;

    // Champs spécifiques Reptile
    private JComboBox<String> reproductionComboBox;
    private JCheckBox venomousCheckBox;

    // Champs spécifiques Amphibian
    private JComboBox<String> respirationComboBox;
    private JCheckBox aquaticCheckBox;
    private JCheckBox metamorphosisCheckBox;

    // Panel permettant de changer les champs selon l'espèce
    private JPanel specificFieldsPanel;
    private CardLayout cardLayout;


    public JButton getSaveButton() {
        return saveButton;
    }

    public String getAnimalNameFromForm() {
        return nameField.getText();
    }

    public String getAnimalHeightFromForm() {
        return heightField.getText();
    }

    public String getAnimalWeightFromForm() {
        return weightField.getText();
    }

    public String getAnimalDescriptionFromForm() {
        return descriptionField.getText();
    }

    public String getAnimalImagePathFromForm() {
        return imagepathField.getText();
    }

    public String getAnimalHabitat() {
        return habitatComboBox.getSelectedItem().toString();
    }

    public Habitat getSelectedHabitat() {
        return (Habitat) habitatComboBox.getSelectedItem();
    }

    public String getSelectedStatus() {
        return statusComboBox.getSelectedItem().toString();
    }

    public String getSelectedSpecies() {
        return speciesComboBox.getSelectedItem().toString();
    }


    // ---------- Mammal ----------

    public String getGestationFromForm() {
        return gestationField.getText();
    }


    // ---------- Bird ----------

    public String getWingspanFromForm() {
        return wingspanField.getText();
    }

    public boolean getCanFlyFromForm() {
        return canFlyCheckBox.isSelected();
    }


    // ---------- Fish ----------

    public String getWaterTypeFromForm() {
        return waterTypeComboBox.getSelectedItem().toString();
    }

    public String getBonesFromForm() {
        return bonesComboBox.getSelectedItem().toString();
    }

    public String getDepthFromForm() {
        return depthField.getText();
    }


    // ---------- Reptile ----------

    public String getReproductionFromForm() {
        return reproductionComboBox.getSelectedItem().toString();
    }

    public boolean getVenomousFromForm() {
        return venomousCheckBox.isSelected();
    }


    // ---------- Amphibian ----------

    public String getRespirationFromForm() {
        return respirationComboBox.getSelectedItem().toString();
    }

    public boolean getAquaticFromForm() {
        return aquaticCheckBox.isSelected();
    }

    public boolean getMetamorphosisFromForm() {
        return metamorphosisCheckBox.isSelected();
    }


    public ADDANIMAL(List<Habitat> habitats)
    {
        setTitle("Add an animal");
        setModal(true);
        setSize(450, 600);
        setLocationRelativeTo(null);

        initComponents(habitats);
    }

    // Surcharge du constructeur pour l'édition d'un animal existant
    public ADDANIMAL( List<Habitat> habitats, Animal animalToEdit)
    {
        setTitle("Edit an animal");
        setModal(true);
        setSize(450, 600);
        setLocationRelativeTo(null);

        initComponents(habitats);
        fillWithAnimalData(animalToEdit);
    }


    public void initComponents(List<Habitat> habitats)
    {
        statusComboBox = new JComboBox<>();
        speciesComboBox = new JComboBox<>();

        statusComboBox.addItem("EXTINCT");
        statusComboBox.addItem("ENDANGERED");
        statusComboBox.addItem("VULNERABLE");
        statusComboBox.addItem("LEAST_CONCERN");

        speciesComboBox.addItem("amphibian");
        speciesComboBox.addItem("fish");
        speciesComboBox.addItem("bird");
        speciesComboBox.addItem("mammal");
        speciesComboBox.addItem("reptile");

        nameField = new JTextField(15);
        heightField = new JTextField(15);
        weightField = new JTextField(15);
        habitatComboBox = new JComboBox<>();
        for (Habitat habitat : habitats) {
            habitatComboBox.addItem(habitat);
        }
        descriptionField = new JTextField(15);
        imagepathField = new JTextField(15);
        imagepathField.setEditable(false);

        saveButton = new JButton("Save");
        JButton browseImageButton = new JButton("Browse...");

        browseImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

                File selectedFilePath = fileChooser.getSelectedFile();
                Path projectPath = Paths.get("").toAbsolutePath();
                Path imagePath = selectedFilePath.toPath().toAbsolutePath();

                Path relativePath = projectPath.relativize(imagePath);
                imagepathField.setText(relativePath.toString());

            }
        });


        // =========================
        // Champs communs
        // =========================

        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Height:"));
        panel.add(heightField);

        panel.add(new JLabel("Weight:"));
        panel.add(weightField);

        panel.add(new JLabel("Status:"));
        panel.add(statusComboBox);

        panel.add(new JLabel("Habitat:"));
        panel.add(habitatComboBox);

        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        panel.add(new JLabel("Species:"));
        panel.add(speciesComboBox);

        panel.add(new JLabel("Image Path:"));

        JPanel imagePathPanel = new JPanel(new BorderLayout(5, 0));
        imagePathPanel.add(imagepathField, BorderLayout.CENTER);
        imagePathPanel.add(browseImageButton, BorderLayout.EAST);
        panel.add(imagePathPanel);


        // =========================
        // Champs spécifiques
        // =========================

        cardLayout = new CardLayout();
        specificFieldsPanel = new JPanel(cardLayout);


        // ---------- Mammal ----------

        gestationField = new JTextField(15);

        JPanel mammalPanel = new JPanel(new GridLayout(1, 2, 5, 5));

        mammalPanel.add(new JLabel("Gestation duration:"));
        mammalPanel.add(gestationField);


        // ---------- Bird ----------

        wingspanField = new JTextField(15);
        canFlyCheckBox = new JCheckBox();

        JPanel birdPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        birdPanel.add(new JLabel("Wingspan:"));
        birdPanel.add(wingspanField);

        birdPanel.add(new JLabel("Can fly:"));
        birdPanel.add(canFlyCheckBox);


        // ---------- Fish ----------

        waterTypeComboBox = new JComboBox<>();
        waterTypeComboBox.addItem("Freshwater");
        waterTypeComboBox.addItem("Saltwater");

        bonesComboBox = new JComboBox<>();
        bonesComboBox.addItem("Bony");
        bonesComboBox.addItem("Cartilaginous");

        depthField = new JTextField(15);

        JPanel fishPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        fishPanel.add(new JLabel("Type of water:"));
        fishPanel.add(waterTypeComboBox);

        fishPanel.add(new JLabel("Bones:"));
        fishPanel.add(bonesComboBox);

        fishPanel.add(new JLabel("Depth:"));
        fishPanel.add(depthField);


        // ---------- Reptile ----------

        reproductionComboBox = new JComboBox<>();

        reproductionComboBox.addItem("Oviparous");
        reproductionComboBox.addItem("Viviparous");
        reproductionComboBox.addItem("Ovoviviparous");

        venomousCheckBox = new JCheckBox();

        JPanel reptilePanel = new JPanel(new GridLayout(2, 2, 5, 5));

        reptilePanel.add(new JLabel("Reproduction:"));
        reptilePanel.add(reproductionComboBox);

        reptilePanel.add(new JLabel("Venomous:"));
        reptilePanel.add(venomousCheckBox);


        // ---------- Amphibian ----------

        respirationComboBox = new JComboBox<>();

        respirationComboBox.addItem("Cutaneous");
        respirationComboBox.addItem("Pulmonary");
        respirationComboBox.addItem("Mixed");

        aquaticCheckBox = new JCheckBox();
        metamorphosisCheckBox = new JCheckBox();

        JPanel amphibianPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        amphibianPanel.add(new JLabel("Respiration:"));
        amphibianPanel.add(respirationComboBox);

        amphibianPanel.add(new JLabel("Aquatic:"));
        amphibianPanel.add(aquaticCheckBox);

        amphibianPanel.add(new JLabel("Metamorphosis:"));
        amphibianPanel.add(metamorphosisCheckBox);


        // Ajout des différents panels dans le CardLayout

        specificFieldsPanel.add(amphibianPanel, "amphibian");
        specificFieldsPanel.add(fishPanel, "fish");
        specificFieldsPanel.add(birdPanel, "bird");
        specificFieldsPanel.add(mammalPanel, "mammal");
        specificFieldsPanel.add(reptilePanel, "reptile");


        // =========================
        // Changement selon l'espèce
        // =========================

        speciesComboBox.addActionListener(e -> {

            String selectedSpecies =
                    speciesComboBox.getSelectedItem().toString();

            cardLayout.show(
                    specificFieldsPanel,
                    selectedSpecies
            );
        });


        // =========================
        // Bouton
        // =========================

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(saveButton);


        // =========================
        // Fenêtre complète
        // =========================

        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.add(panel, BorderLayout.NORTH);

        JPanel specificContainer = new JPanel(new BorderLayout());
        specificContainer.add(specificFieldsPanel, BorderLayout.NORTH);

        centerPanel.add(specificContainer, BorderLayout.CENTER);


        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    public void fillWithAnimalData(Animal animal)
    {
        nameField.setText(animal.getName());
        heightField.setText(String.valueOf(animal.getHeight()));
        weightField.setText(String.valueOf(animal.getWeight()));
        statusComboBox.setSelectedItem(animal.getStatus().toString());
        habitatComboBox.setSelectedItem(animal.getHabitat());
        descriptionField.setText(animal.getDescription());
        imagepathField.setText(animal.getImagePath());


        // remplir les champs specifiques à l'espece
        switch (animal) {
            case Mammal mammal -> {
                speciesComboBox.setSelectedItem("mammal");
                gestationField.setText(String.valueOf(mammal.getDureeGestation()));
            }
            case Bird bird -> {
                speciesComboBox.setSelectedItem("bird");
                wingspanField.setText(String.valueOf(bird.getWingspan()));
                canFlyCheckBox.setSelected(bird.isCanFly());
            }
            case Fish fish -> {
                speciesComboBox.setSelectedItem("fish");
                waterTypeComboBox.setSelectedItem(fish.getTypeOfWater());
                bonesComboBox.setSelectedItem(fish.getBones());
                depthField.setText(String.valueOf(fish.getDepth()));
            }
            case Reptile reptile -> {
                speciesComboBox.setSelectedItem("reptile");
                reproductionComboBox.setSelectedItem(reptile.getReproduction());
                venomousCheckBox.setSelected(reptile.isVenomous());
            }
            case Amphibian amphibian -> {
                speciesComboBox.setSelectedItem("amphibian");
                respirationComboBox.setSelectedItem(amphibian.getTypeRespiration());
                aquaticCheckBox.setSelected(amphibian.isAquatique());
                metamorphosisCheckBox.setSelected(amphibian.isMetamorphose());
            }
            default -> {
            }
        }

        speciesComboBox.setEnabled(false);


    }
}
