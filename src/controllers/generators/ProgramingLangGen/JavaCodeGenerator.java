package controllers.generators.ProgramingLangGen;

import controllers.generators.ColumnTypeMapper;
import controllers.generators.ICodeGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class JavaCodeGenerator implements ICodeGenerator {

    private static final String OUTPUT_FOLDER = "generated_code";

    public JavaCodeGenerator() {
    }

    @Override
    public void generate(String tableName, Connection connection) {
        String className = toCamelCase(tableName);
        StringBuilder classCode = new StringBuilder("public class " + className + " {\n\n");

        try {
            // Use ColumnTypeMapper to get column types, including foreign keys
            ColumnTypeMapper columnTypeMapper = new ColumnTypeMapper();
            Map<String, String> columnTypes = columnTypeMapper.getColumnTypes(tableName, connection);

            // Generate fields and constructors for columns
            for (Map.Entry<String, String> entry : columnTypes.entrySet()) {
                String columnName = entry.getKey();
                String columnType = entry.getValue();
                classCode.append("    private ").append(columnType).append(" ").append(toCamelCase(columnName)).append(";\n");
            }

            // Generate constructor for columns
            classCode.append("\n    public ").append(className).append("(");
            for (Map.Entry<String, String> entry : columnTypes.entrySet()) {
                String columnName = entry.getKey();
                String columnType = entry.getValue();
                classCode.append(columnType).append(" ").append(toCamelCase(columnName)).append(", ");
            }
            classCode.delete(classCode.length() - 2, classCode.length());
            classCode.append(") {\n");
            for (Map.Entry<String, String> entry : columnTypes.entrySet()) {
                String columnName = entry.getKey();
                classCode.append("        this.").append(toCamelCase(columnName)).append(" = ").append(toCamelCase(columnName)).append(";\n");
            }
            classCode.append("    }\n");

            // Generate getter and setter methods for columns
            for (Map.Entry<String, String> entry : columnTypes.entrySet()) {
                String columnName = entry.getKey();
                String columnType = entry.getValue();
                String camelCaseName = toCamelCase(columnName);
                String capitalizedCamelCaseName = camelCaseName.substring(0, 1).toUpperCase() + camelCaseName.substring(1);

                // Getter
                classCode.append("\n    public ").append(columnType).append(" get").append(capitalizedCamelCaseName).append("() {\n");
                classCode.append("        return ").append(camelCaseName).append(";\n");
                classCode.append("    }\n");

                // Setter
                classCode.append("\n    public void set").append(capitalizedCamelCaseName).append("(").append(columnType).append(" ").append(camelCaseName).append(") {\n");
                classCode.append("        this.").append(camelCaseName).append(" = ").append(camelCaseName).append(";\n");
                classCode.append("    }\n");
            }

            classCode.append("}");

            writeFile(OUTPUT_FOLDER + File.separator + className + ".java", classCode.toString());
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
}
