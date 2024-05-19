package controllers.generators.InterfaceGen;

import controllers.generators.ICodeGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import java.sql.*;

public class HtmlInterfaceGenerator implements ICodeGenerator {

    private static final String OUTPUT_FOLDER = "generated_code";

    @Override
    public void generate(String tableName, Connection connection) {
        String interfaceName = tableName + "UI";
        StringBuilder interfaceCode = new StringBuilder("<!DOCTYPE html>\n<html>\n<head>\n<title>" + interfaceName + "</title>\n</head>\n<body>\n");

        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableName, null);

            interfaceCode.append("    <form action=\"#\" method=\"post\">\n");

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                interfaceCode.append("        <label for=\"").append(toCamelCase(columnName)).append("\">").append(columnName).append(":</label>\n");
                interfaceCode.append("        <input type=\"text\" id=\"").append(toCamelCase(columnName)).append("\" name=\"").append(toCamelCase(columnName)).append("\"><br>\n");
            }

            interfaceCode.append("        <input type=\"submit\" value=\"Submit\">\n");
            interfaceCode.append("    </form>\n");
            interfaceCode.append("</body>\n</html>");

            writeFile(OUTPUT_FOLDER + File.separator +interfaceName + ".html", interfaceCode.toString());
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
