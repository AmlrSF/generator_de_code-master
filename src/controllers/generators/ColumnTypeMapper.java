package controllers.generators;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ColumnTypeMapper {

    public Map<String, String> getColumnTypes(String tableName, Connection connection) throws SQLException {
        Map<String, String> columnTypes = new HashMap<>();

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            String columnType = columns.getString("TYPE_NAME");
            columnTypes.put(columnName, mapDataType(columnType));
        }

        ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
        while (foreignKeys.next()) {
            String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
            String pkTableName = foreignKeys.getString("PKTABLE_NAME");
            columnTypes.put(fkColumnName, toCamelCase(pkTableName));
        }

        return columnTypes;
    }

    private String mapDataType(String columnType) {
        switch (columnType.toUpperCase()) {
            case "INT":
                return "int";
            case "VARCHAR":
                return "String";
            case "BOOLEAN":
                return "boolean";
            case "DATE":
                return "Date";
            // Add more cases as needed
            default:
                return "Object";
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
