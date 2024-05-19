import javax.swing.*;

public class enseignantsUI {

    public enseignantsUI() {
        JFrame frame = new JFrame("enseignantsUI");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        JLabel teacherIdLabel = new JLabel("teacher_id");
        JTextField teacherIdField = new JTextField();
        panel.add(teacherIdLabel);
        panel.add(teacherIdField);
        JLabel firstNameLabel = new JLabel("first_name");
        JTextField firstNameField = new JTextField();
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        JLabel lastNameLabel = new JLabel("last_name");
        JTextField lastNameField = new JTextField();
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        JLabel subjectLabel = new JLabel("subject");
        JTextField subjectField = new JTextField();
        panel.add(subjectLabel);
        panel.add(subjectField);
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
