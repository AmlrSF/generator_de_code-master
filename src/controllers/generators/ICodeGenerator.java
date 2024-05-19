package controllers.generators;

import java.sql.Connection;

public interface ICodeGenerator {
    void generate(String tableName, Connection connection);
}
