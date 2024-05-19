package controllers.generators;
import controllers.generators.ProgramingLangGen.*;
import controllers.generators.InterfaceGen.*;

import java.sql.Connection;
import java.util.List;

public class CodeGeneratorModel {
    private JavaCodeGenerator javaCodeGenerator = new JavaCodeGenerator();
    private PythonCodeGenerator pythonCodeGenerator = new PythonCodeGenerator();
    private SwingInterfaceGenerator swingInterfaceGenerator = new SwingInterfaceGenerator();
    private HtmlInterfaceGenerator htmlInterfaceGenerator = new HtmlInterfaceGenerator();

    public void generateCode(List<String> selectedTables, String selectedLanguage, String selectedInterface, Connection connection) {
        for (String table : selectedTables) {
            if (selectedLanguage.equals("Java")) {
                javaCodeGenerator.generate(table, connection);
            } else if (selectedLanguage.equals("Python")) {
                pythonCodeGenerator.generate(table, connection);
            }

            if (selectedInterface.equals("Swing")) {
                swingInterfaceGenerator.generate(table, connection);
            } else if (selectedInterface.equals("HTML")) {
                htmlInterfaceGenerator.generate(table, connection);
            }
        }
    }

}
