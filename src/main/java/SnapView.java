import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;

public class SnapView {
    private JPanel panel;
    private JButton camera;

    public SnapView(ToolWindow toolWindow) {
        camera.addActionListener(e -> saveFile());
    }

    private void saveFile() {
        System.out.println("Save file on click");
    }

    public JPanel getComponent() {
        return panel;
    }
}
