import com.intellij.openapi.ui.DialogWrapper;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;

public class SaveAsDialog extends DialogWrapper {

    public SaveAsDialog() {
        super(true);
        init();
        setTitle("Code Snapshot");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("File Name");
        label.setPreferredSize(new Dimension(100, 100));
        dialogPanel.add(label, BorderLayout.CENTER);
        return dialogPanel;
    }
}
