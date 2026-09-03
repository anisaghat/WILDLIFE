package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class LOGIN extends JDialog {

    private JPanel MainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox newHereCheckBox;
    private JButton enterButton;

    public LOGIN() {
        initComponents();
        setTitle("Welcome to Wildlife");
        setModal(true);
        setSize(400,300);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(enterButton); // pour se connecter en appuyant sur enter directement

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void addLoginListener(ActionListener listener) {
        enterButton.addActionListener(listener);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword()).trim();
    }

    public boolean getNew() {
        return newHereCheckBox.isSelected();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void initComponents() {
        MainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //MainPanel.setForeground(Color.BLUE); ?? 

        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        newHereCheckBox = new JCheckBox("New here?");
        enterButton = new JButton("Enter application");

        gbc.gridx = 0; gbc.gridy = 0;
        MainPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        MainPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        MainPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        MainPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        MainPanel.add(newHereCheckBox, gbc);

        gbc.gridy = 3;
        MainPanel.add(enterButton, gbc);

        setContentPane(MainPanel);
    }
}
