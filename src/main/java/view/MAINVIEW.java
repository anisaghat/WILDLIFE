package view;

import app.AppNavigator;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MAINVIEW  extends JFrame {
    private JButton buttonAdd;
    private JButton buttonDisplayMap;
    private JTable table;
    private JPanel mainPanel;
    private JButton buttonLogout;
    private JButton buttonEndangeredAnimals;

    private final AppNavigator navigator;

    public MAINVIEW(AppNavigator navigator)
    {
        this.navigator = navigator;
        setContentPane(mainPanel);
        setTitle("WILFLIFE");
        setLocationRelativeTo(null);
        setSize(700,700);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        buttonAdd.addActionListener( e -> navigator.showAddAnimalForm());
        buttonLogout.addActionListener( e -> logout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logout();
            }
        });
    }
    private void logout() {
        dispose();
        navigator.showLogin();
    }
}
