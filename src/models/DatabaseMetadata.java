package models;

import java.util.List;

public class DatabaseMetadata {
        private List<Table> tables;

    public DatabaseMetadata(List<Table> tables) {
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
    
}
