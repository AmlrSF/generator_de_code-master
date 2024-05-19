package views;

import controllers.CodeGenerateControllers;
import dao.DAOProject;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CodeGeneratorUI extends JFrame {
    private JList<String> tablesList;
    private JComboBox<String> languageComboBox;
    private JComboBox<String> interfaceComboBox;
    private JButton generateButton;

    public CodeGeneratorUI(List<String> tableNames) throws SQLException {
        setTitle("Code Generator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the main panel with a GridLayout
        JPanel mainPanel = new JPanel(new GridLayout(1, 2)); // One row, two columns
        getContentPane().add(mainPanel);

        // Left Panel for displaying selected tables
        JPanel leftPanel = new JPanel(new BorderLayout());
        mainPanel.add(leftPanel);

        if (tableNames.isEmpty()) {
            // Display a message if no tables are found
            JLabel noTablesLabel = new JLabel("No tables found in the database.", JLabel.CENTER);
            leftPanel.add(noTablesLabel, BorderLayout.CENTER);
        } else {
            // Table Selection
            tablesList = new JList<>(tableNames.toArray(new String[0]));
            tablesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            JScrollPane tablesScrollPane = new JScrollPane(tablesList);
            tablesScrollPane.setPreferredSize(new Dimension(200, 300));
            leftPanel.add(tablesScrollPane, BorderLayout.CENTER);
        }

        // Right Panel for selection options
        JPanel rightPanel = new JPanel(new GridBagLayout());
        mainPanel.add(rightPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Language Selection
        rightPanel.add(new JLabel("Select Language:"), gbc);
        gbc.gridy++;
        languageComboBox = new JComboBox<>(new String[]{"Java", "Python", "C++"});
        rightPanel.add(languageComboBox, gbc);

        // Interface Selection
        gbc.gridy++;
        rightPanel.add(new JLabel("Select Interface:"), gbc);
        gbc.gridy++;
        interfaceComboBox = new JComboBox<>(new String[]{"Swing", "HTML"});
        rightPanel.add(interfaceComboBox, gbc);

        // Generate Button
        gbc.gridy++;
        gbc.gridwidth = 2;
        generateButton = new JButton("Generate Code");
        generateButton.addActionListener(new CodeGenerateControllers(this));
        rightPanel.add(generateButton, gbc);

        setVisible(true);
    }

    public String getSelectedInterface() {
        return (String) this.interfaceComboBox.getSelectedItem();
    }


    public List<String> getSelectedTables() {
        return this.tablesList.getSelectedValuesList();
    }


    public String getSelectedLanguage() {
        return (String) this.languageComboBox.getSelectedItem();
    }


}
