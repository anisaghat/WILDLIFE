package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.controllerMAINVIEW;
import model.entities.animals.Animal;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MAINVIEW extends JFrame {

    private controllerMAINVIEW controller;
    private JPanel mainPanel;

    private  JTable table;
    private DefaultTableModel tableModel;

    //liste des animaux affichés dans la table
    private List<Animal> displayedAnimals = new ArrayList<>();

    //=============
    // détails sur l'animal sélectionné
    //=============

    private JPanel detailsContainer;
    private CardLayout detailsLayout;
    private JLabel animalImageLabel;

    private JLabel animalNameLabel;
    private JLabel animalSpeciesLabel;
    private JLabel animalWeightLabel;
    private JLabel animalHeightLabel;
    private JLabel animalHabitatLabel;
    private JLabel animalStatusLabel;
    private JLabel animalDescriptionLabel;

    private JButton editButton;
    private JButton deleteButton;

    //=============
    // boutons principaux
    //=============

    private  JButton buttonAddAnimal =
            new JButton("Add a new animal");

    private  JButton buttonAddBiome =
            new JButton("Add a new biome");

    private  JButton buttonAddHabitat =
            new JButton("Add a new habitat");

    private  JButton buttonEndangeredAnimals =
            new JButton("See all endangered animals");

    private  JButton buttonLogout =
            new JButton("Logout");

    private final JMenuItem exportItem =
            new JMenuItem("Exporter la base (binaire)");

    private final JMenuItem importItem =
            new JMenuItem("Importer la base (binaire)");

    public MAINVIEW() {
        initComponents();
        initActions();
    }

    private void initComponents() {
        setTitle("WILDLIFE");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder (BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //==============
        // boutons du haut
        //==============

        JPanel buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.add(buttonAddAnimal);
        buttonsPanel.add(buttonAddBiome);
        buttonsPanel.add(buttonAddHabitat);
        buttonsPanel.add(buttonEndangeredAnimals);
        buttonsPanel.add(buttonLogout);

        JMenu binaryDatabaseMenu = new JMenu("Base binaire");
        binaryDatabaseMenu.add(exportItem);
        binaryDatabaseMenu.add(importItem);

        JMenuBar binaryDatabaseMenuBar = new JMenuBar();
        binaryDatabaseMenuBar.add(binaryDatabaseMenu);
        buttonsPanel.add(binaryDatabaseMenuBar);

        //==============
        // table des animaux
        //==============

        String[] columns = {"Name","Species","Height","Weight","Status"};
        tableModel = new DefaultTableModel(columns,0){
            @Override
            public boolean isCellEditable(int row,int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        initDetailsPanel();

        mainPanel.add(buttonsPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(detailsContainer, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    //=============
    // détails sur l'animal sélectionné
    //=============

    private void initDetailsPanel() {

        detailsLayout = new CardLayout();
        detailsContainer = new JPanel(detailsLayout);

        detailsContainer.setBorder(
                BorderFactory.createTitledBorder(
                        "Selected animal"
                )
        );

        detailsContainer.setPreferredSize(
                new Dimension(0, 220)
        );

        JPanel noSelectionPanel =
                new JPanel(new GridBagLayout());

        noSelectionPanel.add(
                new JLabel(
                        "Select an animal to display data"
                )
        );

        JPanel selectedAnimalPanel =
                new JPanel(new BorderLayout(15, 10));

        // ----- Image -----

        animalImageLabel =
                new JLabel("[No image]", SwingConstants.CENTER);

        animalImageLabel.setPreferredSize(
                new Dimension(150, 150)
        );

        animalImageLabel.setBorder(
                BorderFactory.createEtchedBorder()
        );

        selectedAnimalPanel.add(
                animalImageLabel,
                BorderLayout.WEST
        );

        // ----- Informations -----

        JPanel informationPanel =
                new JPanel(new GridLayout(7, 1, 5, 5));

        animalNameLabel = new JLabel();
        animalSpeciesLabel = new JLabel();
        animalWeightLabel = new JLabel();
        animalHeightLabel = new JLabel();
        animalHabitatLabel = new JLabel();
        animalStatusLabel = new JLabel();
        animalDescriptionLabel = new JLabel();

        informationPanel.add(animalNameLabel);
        informationPanel.add(animalSpeciesLabel);
        informationPanel.add(animalWeightLabel);
        informationPanel.add(animalHeightLabel);
        informationPanel.add(animalHabitatLabel);
        informationPanel.add(animalStatusLabel);
        informationPanel.add(animalDescriptionLabel);

        selectedAnimalPanel.add(
                informationPanel,
                BorderLayout.CENTER
        );


        // ----- Boutons Edit / Delete -----

        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        JPanel actionPanel = new JPanel();

        actionPanel.add(editButton);
        editButton.addActionListener(e -> editSelectedAnimal());
        actionPanel.add(deleteButton);
        deleteButton.addActionListener(e -> deleteSelectedAnimal());

        selectedAnimalPanel.add(
                actionPanel,
                BorderLayout.SOUTH
        );


        // =========================
        // CardLayout
        // =========================

        detailsContainer.add(
                noSelectionPanel,
                "NO_SELECTION"
        );

        detailsContainer.add(
                selectedAnimalPanel,
                "SELECTED"
        );

        detailsLayout.show(
                detailsContainer,
                "NO_SELECTION"
        );


    }

    private void initActions() {

        buttonAddAnimal.addActionListener(
                e -> controller.showAddAnimalForm()
        );


        buttonAddBiome.addActionListener(
                e -> controller.showAddBiomeForm()
        );

        buttonAddHabitat.addActionListener(
                e -> controller.showAddHabitatForm()
        );


        buttonEndangeredAnimals.addActionListener(
                e -> controller.showEndangeredAnimals()
        );


        buttonLogout.addActionListener(
                e -> controller.logout()
        );

        exportItem.addActionListener(
                e -> controller.exportAnimalsToBinary()
        );

        importItem.addActionListener(
                e -> controller.importAnimalsFromBinary()
        );


        // Quand l'utilisateur sélectionne une ligne
        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                displaySelectedAnimal();
            }
        });


        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                controller.logout();
            }
        });
    }

    // =========================
    // Animal sélectionné
    // =========================

    private void displaySelectedAnimal() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {

            detailsLayout.show(
                    detailsContainer,
                    "NO_SELECTION"
            );

            return;
        }


        Animal animal =
                displayedAnimals.get(selectedRow);


        animalNameLabel.setText(
                "Name: " + animal.getName()
        );

        animalSpeciesLabel.setText(
                "Species: "
                        + animal.getClass().getSimpleName()
        );

        animalWeightLabel.setText(
                "Weight: "
                        + animal.getWeight()
        );

        animalHeightLabel.setText(
                "Height: "
                        + animal.getHeight()
        );


        if (animal.getHabitat() != null) {

            animalHabitatLabel.setText(
                    "Habitat: "
                            + animal.getHabitat().getName()
            );

        } else {

            animalHabitatLabel.setText(
                    "Habitat: None"
            );
        }


        animalStatusLabel.setText(
                "Status: "
                        + animal.getStatus()
        );

        animalDescriptionLabel.setText(
                "Description: "
                        + animal.getDescription()
        );


        displayAnimalImage(
                animal.getImagePath()
        );


        detailsLayout.show(
                detailsContainer,
                "SELECTED"
        );
    }

    // =========================
    // Image
    // =========================

    private void displayAnimalImage(String imagePath) {

        animalImageLabel.setIcon(null);

        if (imagePath == null ||
                imagePath.isBlank()) {

            animalImageLabel.setText(
                    "[No image]"
            );

            return;
        }


        File imageFile =
                new File(imagePath);

        if (!imageFile.exists()) {

            animalImageLabel.setText(
                    "[Image not found]"
            );

            return;
        }


        ImageIcon originalIcon =
                new ImageIcon(imagePath);

        Image scaledImage =
                originalIcon
                        .getImage()
                        .getScaledInstance(
                                140,
                                140,
                                Image.SCALE_SMOOTH
                        );

        animalImageLabel.setText("");

        animalImageLabel.setIcon(
                new ImageIcon(scaledImage)
        );
    }

    private void deleteSelectedAnimal() {
        Animal animalToDelete = getSelectedAnimal();
        if (animalToDelete == null || controller == null) {
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete " + animalToDelete.getName() + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            controller.deleteAnimal(animalToDelete);
        }
    }

    private void editSelectedAnimal() {
        Animal animalToEdit = getSelectedAnimal();
        if (animalToEdit == null || controller == null) {
            return;
        }

        controller.editAnimal(animalToEdit);
    }

    public void displayAnimals(List<Animal> animals) {

        displayedAnimals = new ArrayList<>(animals);

        tableModel.setRowCount(0);

        for (Animal animal : animals) {

            String habitatName = "";

            if (animal.getHabitat() != null) {
                habitatName = animal.getHabitat().getName();
            }

            Object[] row = {
                    animal.getName(),
                    animal.getClass().getSimpleName(),
                    animal.getHeight(),
                    animal.getWeight(),
                    animal.getStatus()

            };

            tableModel.addRow(row);
        }
    }

    public void setController(controllerMAINVIEW controller) {
        this.controller = controller;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }


    public Animal getSelectedAnimal() {

        int selectedRow =
                table.getSelectedRow();

        if (selectedRow == -1) {
            return null;
        }

        return displayedAnimals.get(
                selectedRow
        );
    }

}
