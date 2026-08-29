package view;

import app.AppNavigator;

import javax.swing.*;
import controller.controllerMAINVIEW;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MAINVIEW extends JFrame {

    private final AppNavigator navigator;
    private controllerMAINVIEW controller;

    private JPanel mainPanel;

    private  JTable table = new JTable();

    private  JButton buttonAddAnimal =
            new JButton("Add a new animal");

    private  JButton buttonAddBiome =
            new JButton("Add a new biome");

    private  JButton buttonDisplayMap =
            new JButton("See the map");

    private  JButton buttonEndangeredAnimals =
            new JButton("See all endangered animals");

    private  JButton buttonLogout =
            new JButton("Logout");

    public MAINVIEW(AppNavigator navigator) {
        this.navigator = navigator;

        initComponents();
        initActions();
    }

    private void initComponents() {
        setTitle("WILDLIFE");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());

        JPanel buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.add(buttonAddAnimal);
        buttonsPanel.add(buttonAddBiome);
        buttonsPanel.add(buttonDisplayMap);
        buttonsPanel.add(buttonEndangeredAnimals);
        buttonsPanel.add(buttonLogout);

        mainPanel.add(buttonsPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private void initActions() {
        buttonAddAnimal.addActionListener(
                e -> navigator.showAddAnimalForm()
        );

        buttonAddBiome.addActionListener(
                e -> navigator.showAddBiomeForm()
        );

        buttonEndangeredAnimals.addActionListener(
                e -> controller.showEndangeredAnimals()
        );

        buttonLogout.addActionListener(
                e -> logout()
        );

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logout();
            }
        });
    }

    public void setController(controllerMAINVIEW controller) {
        this.controller = controller;
    }

    private void logout() {
        dispose();
        navigator.showLogin();
    }
}
