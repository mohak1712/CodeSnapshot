import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.panels.VerticalLayout;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SnapView {
    private final JBScrollPane scrollPane;
    private final CustomTextEditor codeView;

    public SnapView(@NotNull Project project) {
        VerticalLayout verticalLayout = new VerticalLayout(20);
        codeView = new CustomTextEditor("", project, null);
        JBPanel panel = new JBPanel(verticalLayout);
        panel.add(codeView);
        panel.add(saveAsLabel());
        panel.withBackground(Color.LIGHT_GRAY);
        scrollPane = new JBScrollPane(panel);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private Component saveAsLabel() {
        try {
            Image img = ImageIO.read(getClass().getResource("camera.png"));
            JLabel saveAsLabel = new JLabel(new ImageIcon(img));
            saveAsLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    saveFile();
                }
            });

            return saveAsLabel;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JLabel();
    }

    private void saveFile() {
        FileSaverDescriptor fileSaverDescriptor = new FileSaverDescriptor("Code Snapshot", "Saves this code as image",
                "jpg", "png");
        FileSaverDialog saveFileDialog = FileChooserFactory.getInstance()
                .createSaveFileDialog(fileSaverDescriptor, (Project) null);
        saveFileDialog.save(null, "code");
    }

    public JComponent getComponent() {
        return scrollPane;
    }
}
