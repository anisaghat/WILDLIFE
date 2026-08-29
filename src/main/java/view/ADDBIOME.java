package view;

import javax.swing.*;
import java.awt.*;

public class ADDBIOME extends JDialog {
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextField temperatureField;
    private JTextField humidityField;
    private JTextField dominantVegetationField;
    private JButton saveButton;
    private JButton cancelButton;

    public ADDBIOME() {
        setTitle("Add a biome");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setResizable(true);

        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        nameField = new JTextField(20);
        descriptionArea = new JTextArea(4, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        temperatureField = new JTextField(20);
        humidityField = new JTextField(20);
        dominantVegetationField = new JTextField(20);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        fieldsPanel.add(new JLabel("Name:"));
        fieldsPanel.add(nameField);
        fieldsPanel.add(new JLabel("Description:"));
        fieldsPanel.add(new JScrollPane(descriptionArea));
        fieldsPanel.add(new JLabel("Temperature:"));
        fieldsPanel.add(temperatureField);
        fieldsPanel.add(new JLabel("Humidity:"));
        fieldsPanel.add(humidityField);
        fieldsPanel.add(new JLabel("Dominant vegetation:"));
        fieldsPanel.add(dominantVegetationField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(fieldsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    public String getBiomeName() {
        return nameField.getText();
    }

    public String getDescription() {
        return descriptionArea.getText();
    }

    public String getTemperature() {
        return temperatureField.getText();
    }

    public String getHumidity() {
        return humidityField.getText();
    }

    public String getDominantVegetation() {
        return dominantVegetationField.getText();
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ADDBIOME dialog = new ADDBIOME();
            dialog.setVisible(true);
        });
    }
}
