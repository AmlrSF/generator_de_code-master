import javax.swing.*;

public class studentsUI {

    public studentsUI() {
        JFrame frame = new JFrame("studentsUI");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        JLabel studentIdLabel = new JLabel("student_id");
        JTextField studentIdField = new JTextField();
        panel.add(studentIdLabel);
        panel.add(studentIdField);
        JLabel firstNameLabel = new JLabel("first_name");
        JTextField firstNameField = new JTextField();
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        JLabel lastNameLabel = new JLabel("last_name");
        JTextField lastNameField = new JTextField();
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        JLabel ageLabel = new JLabel("age");
        JTextField ageField = new JTextField();
        panel.add(ageLabel);
        panel.add(ageField);
        JLabel emailLabel = new JLabel("email");
        JTextField emailField = new JTextField();
        panel.add(emailLabel);
        panel.add(emailField);
        JLabel groupIdLabel = new JLabel("group_id");
        JTextField groupIdField = new JTextField();
        panel.add(groupIdLabel);
        panel.add(groupIdField);
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
