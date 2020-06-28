import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.project.Project;

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
        FileSaverDescriptor fileSaverDescriptor = new FileSaverDescriptor("Code Snapshot", "Saves this code as image",
                "jpg", "png");
        FileSaverDialog saveFileDialog = FileChooserFactory.getInstance()
                .createSaveFileDialog(fileSaverDescriptor, (Project) null);
        saveFileDialog.save(null, "code");
    }

    public JPanel getComponent() {
        return panel;
    }
}
