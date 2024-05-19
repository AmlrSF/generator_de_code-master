package controllers.generators.InterfaceGen;

import controllers.generators.ICodeGenerator;

import java.io.File;
import java.sql.Connection;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
public class SwingInterfaceGenerator implements ICodeGenerator {

    private static final String OUTPUT_FOLDER = "generated_code";

    @Override
    public void generate(String tableName, Connection connection) {
        String interfaceName = tableName + "UI";
        StringBuilder interfaceCode = new StringBuilder("import javax.swing.*;\n\npublic class " + interfaceName + " {\n\n");

        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableName, null);

            interfaceCode.append("    public ").append(interfaceName).append("() {\n");
            interfaceCode.append("        JFrame frame = new JFrame(\"").append(interfaceName).append("\");\n");
            interfaceCode.append("        JPanel panel = new JPanel();\n");
            interfaceCode.append("        panel.setLayout(new GridLayout(").append(getColumnCount(tableName, connection)).append(", 2));\n");

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                interfaceCode.append("        JLabel ").append(toCamelCase(columnName)).append("Label = new JLabel(\"").append(columnName).append("\");\n");
                interfaceCode.append("        JTextField ").append(toCamelCase(columnName)).append("Field = new JTextField();\n");
                interfaceCode.append("        panel.add(").append(toCamelCase(columnName)).append("Label);\n");
                interfaceCode.append("        panel.add(").append(toCamelCase(columnName)).append("Field);\n");
            }

            interfaceCode.append("        frame.add(panel);\n");
            interfaceCode.append("        frame.setSize(400, 300);\n");
            interfaceCode.append("        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
            interfaceCode.append("        frame.setVisible(true);\n");
            interfaceCode.append("    }\n}\n");

            writeFile(OUTPUT_FOLDER + File.separator + interfaceName + ".java", interfaceCode.toString());

        } catch (SQLException e) {
            System.err.println("Error retrieving table metadata: " + e.getMessage());
        }
    }

    private void writeFile(String fileName, String content) {
        File directory = new File(OUTPUT_FOLDER);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Generated file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private String toCamelCase(String str) {
        String[] parts = str.split("_");
        StringBuilder camelCase = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            camelCase.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1));
        }
        return camelCase.toString();
    }

    private int getColumnCount(String tableName, Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        int count = 0;
        while (columns.next()) {
            count++;
        }
        return count;
    }



}
