import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SnapView {
    private JPanel panel;
    private JButton camera;
    private JTextPane selectedCode;
    private JScrollPane scroll;

    public SnapView() {
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scroll.setBorder(new LineBorder(Color.LIGHT_GRAY, 40, true));
        camera.addActionListener(e -> saveFile());
    }

    private void saveFile() {
        SaveAsDialog saveAsDialog = new SaveAsDialog();
        if (saveAsDialog.showAndGet()) {
            // ok was pressed
        }
    }

    public JPanel getComponent() {
        return panel;
    }
}
