package dao;

import utilitaires.Utilitaire;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Column;
import models.ForeignKey;
import models.PrimaryKey;
import models.Table;

public class DAOProject {
    private final Connection connection;

    public DAOProject() throws SQLException {
        // Initialize connection here
        Utilitaire.seConnecter("connectionPar.properties");
        connection = Utilitaire.getConnection();
    }

    public List<String> getTables(String databaseName) {
        List<String> tables = new ArrayList<>();
        try {
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet resultSet = metadata.getTables(databaseName, null, null, new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                tables.add(tableName);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving tables: " + e.getMessage());
            e.printStackTrace();
        }
        return tables;
    }

    public List<Column> getColumnsForTable(String databaseName, String tableName) {
        List<Column> columns = new ArrayList<>();
        String query = "SELECT column_name, data_type FROM information_schema.columns " +
                "WHERE table_schema = ? AND table_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, databaseName);
            statement.setString(2, tableName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String columnName = resultSet.getString("column_name");
                    String columnType = resultSet.getString("data_type");
                    columns.add(new Column(columnName, columnType));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    public PrimaryKey getPrimaryKeyForTable(String databaseName, String tableName) {
        PrimaryKey primaryKey = null;
        String query = "SELECT k.column_name " +
                "FROM information_schema.table_constraints t " +
                "JOIN information_schema.key_column_usage k " +
                "USING (constraint_name, table_schema, table_name) " +
                "WHERE t.constraint_type = 'PRIMARY KEY' AND t.table_schema = ? AND t.table_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, databaseName);
            statement.setString(2, tableName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    primaryKey = new PrimaryKey(resultSet.getString("column_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return primaryKey;
    }

    public List<ForeignKey> getForeignKeysForTable(String databaseName, String tableName) {
        List<ForeignKey> foreignKeys = new ArrayList<>();
        String query = "SELECT k.column_name, k.referenced_table_name, k.referenced_column_name " +
                "FROM information_schema.key_column_usage k " +
                "WHERE k.table_schema = ? AND k.table_name = ? AND k.referenced_table_name IS NOT NULL";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, databaseName);
            statement.setString(2, tableName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String columnName = resultSet.getString("column_name");
                    String referencedTableName = resultSet.getString("referenced_table_name");
                    String referencedColumnName = resultSet.getString("referenced_column_name");
                    foreignKeys.add(new ForeignKey(columnName, referencedTableName, referencedColumnName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foreignKeys;
    }

    // Test main method
    public static void main(String[] args) {
        try {
            DAOProject daoProject = new DAOProject();
            String databaseName = "project2"; // Replace with your actual database name
            List<String> tables = daoProject.getTables(databaseName);
            System.out.println("Tables in the database:");
            for (String table : tables) {
                System.out.println(table);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
