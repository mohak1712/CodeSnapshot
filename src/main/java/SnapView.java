import javax.swing.*;

public class SnapView {
    private JPanel panel;
    private JButton camera;
    private JTextArea selectedCode;
    private JScrollPane scroll;

    public SnapView() {
        camera.addActionListener(e -> saveFile());
    }

    private void saveFile() {
        System.out.println("Save file on click");
    }

    public JPanel getComponent() {
        return panel;
    }
}
