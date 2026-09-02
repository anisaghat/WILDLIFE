package view;

import model.entities.animals.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ENDANGEREDANIMALS extends JDialog {

    public ENDANGEREDANIMALS(List<Animal> animals) {
        setTitle("Endangered animals");
        setModal(true);
        setSize(750, 450);

        initComponents(animals);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents(List<Animal> animals) {
        String[] columns = {
                 "Name", "Habitat",  "Height", "Weight", "Status"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Animal animal : animals) {
            String habitatName = animal.getHabitat() == null
                    ? ""
                    : animal.getHabitat().getName();

            tableModel.addRow(new Object[]{
                    animal.getName(),
                    habitatName,
                    animal.getHeight(),
                    animal.getWeight(),
                    animal.getStatus()
            });
        }

        JTable animalsTable = new JTable(tableModel);
        animalsTable.setFillsViewportHeight(true);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        setLayout(new BorderLayout(10, 10));
        add(new JScrollPane(animalsTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
