import javax.swing.*;

public class classesUI {

    public classesUI() {
        JFrame frame = new JFrame("classesUI");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        JLabel classIdLabel = new JLabel("class_id");
        JTextField classIdField = new JTextField();
        panel.add(classIdLabel);
        panel.add(classIdField);
        JLabel studentIdLabel = new JLabel("student_id");
        JTextField studentIdField = new JTextField();
        panel.add(studentIdLabel);
        panel.add(studentIdField);
        JLabel teacherIdLabel = new JLabel("teacher_id");
        JTextField teacherIdField = new JTextField();
        panel.add(teacherIdLabel);
        panel.add(teacherIdField);
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
