package controllers;


import controllers.generators.CodeGeneratorModel;
import dao.DAOProject;

import utilitaires.Utilitaire;
import views.CodeGeneratorUI;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CodeGenerateControllers implements ActionListener {

    static CodeGeneratorUI fr1;
    private final Connection connection;

    DAOProject ed = new DAOProject();

    public CodeGenerateControllers(JFrame f) throws SQLException {
        if (f instanceof CodeGeneratorUI)
            fr1 = (CodeGeneratorUI) f;
            connection = Utilitaire.getConnection();
    }

    public void actionPerformed(ActionEvent e) {
        // Get the selected interface type, tables, and language
        String selectedInterface = fr1.getSelectedInterface();
        List<String> selectedTables = fr1.getSelectedTables();
        String selectedLanguage = fr1.getSelectedLanguage();

        CodeGeneratorModel codeGenModel = new CodeGeneratorModel();

        codeGenModel.generateCode(selectedTables ,selectedLanguage ,selectedInterface,connection);
    }
}