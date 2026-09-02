package view;

import model.entities.Biome;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ADDNEWHABITAT extends JDialog {
    private JTextField nameField;
    private JComboBox<Biome> BiomeComboBox;
    private JButton saveButton;
    private JButton cancelButton;

    public ADDNEWHABITAT(List<Biome> biomes) {
        setTitle("Add a new habitat");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);

        initComponents(biomes);
        setLocationRelativeTo(null);
    }

    private void initComponents(List<Biome> biomes) {
        nameField = new JTextField(20);
        BiomeComboBox = new JComboBox<>(biomes.toArray(new Biome[0]));


        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JPanel fieldsPanel = new JPanel( new GridLayout(2,5, 5 ,10));
        fieldsPanel.add(new JLabel("Name:"));
        fieldsPanel.add(nameField);
        fieldsPanel.add(new JLabel("Biome:"));
        fieldsPanel.add(BiomeComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );
        mainPanel.add(fieldsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    public String getName() {
        return nameField.getText();
    }

    public Biome getSelectedBiome() {
        return (Biome) BiomeComboBox.getSelectedItem();
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
