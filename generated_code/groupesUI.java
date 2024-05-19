import javax.swing.*;

public class groupesUI {

    public groupesUI() {
        JFrame frame = new JFrame("groupesUI");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        JLabel groupIdLabel = new JLabel("group_id");
        JTextField groupIdField = new JTextField();
        panel.add(groupIdLabel);
        panel.add(groupIdField);
        JLabel groupNameLabel = new JLabel("group_name");
        JTextField groupNameField = new JTextField();
        panel.add(groupNameLabel);
        panel.add(groupNameField);
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
