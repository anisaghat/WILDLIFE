package view;

import javax.swing.*;
import java.awt.*;

public class LOGIN  extends JDialog {
    private JPanel MainPanel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JCheckBox newHereCheckBox;
    private JButton enterButton;

    public LOGIN() {
        setContentPane(MainPanel);
        setTitle("Welcome to Wildlife");
        setModal(true);
        //setResizable(false);

        //initComponents();
        setSize(400,300);
        //pack();
        setLocationRelativeTo(null); // centre la fenêtre
        setVisible(true);
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        newHereCheckBox = new JCheckBox("New here?");
        enterButton = new JButton("Enter application");

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        // Checkbox
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(newHereCheckBox, gbc);

        // Button
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(enterButton, gbc);

        setContentPane(mainPanel);
    }


}
