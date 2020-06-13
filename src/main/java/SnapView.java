import javax.swing.*;

public class SnapView extends JComponent {
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

    public void updateSelectedText(String text) {
        selectedCode.setText(text);
    }

    public SnapView getComponent() {
        return this;
    }
}
