import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.panels.VerticalLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class SnapView {
    private JBScrollPane scrollPane;

    public SnapView(@NotNull Project project) {
        VerticalLayout verticalLayout = new VerticalLayout(20);
        CustomTextEditor codeView = new CustomTextEditor("", project, null);
        JBPanel panel = new JBPanel(verticalLayout);
        panel.add(codeView);
        panel.add(saveButton());
        scrollPane = new JBScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }

    private Component saveButton() {
        JButton button = new JButton("");
        button.setFocusable(false);
        button.addActionListener(e -> saveFile());
        button.setMaximumSize(new Dimension(10, 10));
        return button;
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
