package controllers.generators.ProgramingLangGen;

import controllers.generators.ICodeGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import java.sql.*;

public class PythonCodeGenerator implements ICodeGenerator {

    private static final String OUTPUT_FOLDER = "generated_code";

    @Override
    public void generate(String tableName, Connection connection) {
        String className = toCamelCase(tableName);
        StringBuilder classCode = new StringBuilder("class " + className + ":\n\n");

        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableName, null);

            // Generate attributes, constructor, getters, and setters
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String camelCaseName = toCamelCase(columnName);
                classCode.append("    def __init__(self, ").append(camelCaseName).append("):\n");
                classCode.append("        self.").append(camelCaseName).append(" = ").append(camelCaseName).append("\n\n");
                classCode.append("    def get_").append(camelCaseName).append("(self):\n");
                classCode.append("        return self.").append(camelCaseName).append("\n\n");
                classCode.append("    def set_").append(camelCaseName).append("(self, ").append(camelCaseName).append("):\n");
                classCode.append("        self.").append(camelCaseName).append(" = ").append(camelCaseName).append("\n\n");
            }
            writeFile(OUTPUT_FOLDER + File.separator +className + ".py", classCode.toString());
        } catch (SQLException e) {
            System.err.println("Error retrieving table metadata: " + e.getMessage());
        }// Generate Python class code
    }


    private String toCamelCase(String str) {
        String[] parts = str.split("_");
        StringBuilder camelCase = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            camelCase.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1));
        }
        return camelCase.toString();
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
}
