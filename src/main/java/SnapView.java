import javax.swing.*;
import java.awt.*;

public class SnapView {
    private JPanel panel;
    private JButton camera;
    private JTextPane selectedCode;
    private JScrollPane scroll;

    public SnapView() {
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scroll.setBorder(null);
        camera.addActionListener(e -> saveFile());
    }

    private void saveFile() {
        System.out.println("Save file on click");
    }

    public JPanel getComponent() {
        return panel;
    }
}
