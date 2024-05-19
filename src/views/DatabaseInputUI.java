package views;

import dao.DAOProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class DatabaseInputUI extends JFrame {
    private JTextField dbNameField;
    private JButton loadTablesButton;

    public DatabaseInputUI() {
        setTitle("Database Input");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel dbNameLabel = new JLabel("Enter Database Name:");
        dbNameField = new JTextField(20);
        loadTablesButton = new JButton("Load Tables");
        loadTablesButton.addActionListener(new LoadTablesListener());
        inputPanel.add(dbNameLabel);
        inputPanel.add(dbNameField);
        inputPanel.add(loadTablesButton);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class LoadTablesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String dbName = dbNameField.getText().trim();
            if (!dbName.isEmpty()) {
                DAOProject daoProject = null;
                try {
                    daoProject = new DAOProject();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                List<String> tableNames = daoProject.getTables(dbName);
                try {
                    new CodeGeneratorUI(tableNames);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(DatabaseInputUI.this, "Please enter a database name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new DatabaseInputUI();
    }
}
