package view;

import javax.swing.*;
import java.awt.*;

public class ADDANIMAL extends JDialog
{
    private JTextField nameField;
    private JTextField heightField;
    private JTextField weightField;
    private JComboBox statusComboBox;
    private JTextField habitatField;
    private JTextField descriptionField;
    private JComboBox speciesComboBox;
    private JTextField imagepathField;
    private JButton saveButton;

    public ADDANIMAL()
    {
        setTitle("Add an animal");
        setModal(true);
        setSize(400,450);
        setLocationRelativeTo(null);
        initComponents();
    }

    public void initComponents()
    {
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
        habitatField = new JTextField(15);
        descriptionField = new JTextField(15);
        imagepathField = new JTextField(15);
        saveButton = new JButton("Save");


        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5)); // 8 lignes, 2 colonnes

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Height:"));
        panel.add(heightField);

        panel.add(new JLabel("Weight:"));
        panel.add(weightField);

        panel.add(new JLabel("Status:"));
        panel.add(statusComboBox);

        panel.add(new JLabel("Habitat:"));
        panel.add(habitatField);

        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        panel.add(new JLabel("Species:"));
        panel.add(speciesComboBox);

        panel.add(new JLabel("Image Path:"));
        panel.add(imagepathField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);


    }
}
