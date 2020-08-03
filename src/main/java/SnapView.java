import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.panels.VerticalLayout;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SnapView {
    private final JBScrollPane scrollPane;
    private final CustomTextEditor codeView;

    public SnapView(@NotNull Project project) {
        VerticalLayout verticalLayout = new VerticalLayout(20);
        codeView = new CustomTextEditor("", project, null);
        JBPanel panel = new JBPanel(verticalLayout);
        JTextPane instructions = new JTextPane();
        instructions.setMargin(JBUI.insets(20));
        panel.add(instructions);
        panel.add(codeView);
        panel.add(saveAsLabel());
        panel.withBackground(Color.LIGHT_GRAY);
        scrollPane = new JBScrollPane(panel);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(JBUI.Borders.customLine(Color.LIGHT_GRAY, 20, 30, 20, 30));
    }

    private Component saveAsLabel() {
        Icon cameraInitialState = IconLoader.getIcon("icons/camera_initial.svg");
        Icon cameraFinalState = IconLoader.getIcon("icons/camera_final.svg");
        JLabel saveAsLabel = new JLabel(cameraInitialState);
        saveAsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                saveAsLabel.setIcon(cameraFinalState);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                saveAsLabel.setIcon(cameraInitialState);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                saveFile();
            }
        });

        return saveAsLabel;
    }

    private void saveFile() {
        if (!codeView.isVisible()) {
            return;
        }

        FileSaverDescriptor fileSaverDescriptor = new FileSaverDescriptor("Code Snapshot", "Saves this code as image");
        FileSaverDialog saveFileDialog = FileChooserFactory.getInstance()
                .createSaveFileDialog(fileSaverDescriptor, (Project) null);
        VirtualFileWrapper fileWrapper = saveFileDialog.save(null, "code.png");
        if (fileWrapper == null) {
            //user pressed cancel
            return;
        }

        BufferedImage image = UIUtil.createImage(codeView, codeView.getWidth(), codeView.getHeight(), BufferedImage.TYPE_INT_ARGB);
        codeView.paint(image.getGraphics());

        try {
            ImageIO.write(image, "png", fileWrapper.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JComponent getComponent() {
        return scrollPane;
    }
}
