import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.JBUI;

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
        JLabel label = new JLabel(" Save image as");
        JTextField fileName = new JTextField("code.png");
        label.setPreferredSize(new Dimension(100, 20));
        fileName.setMargin(JBUI.insets(10,0,10,0));
        fileName.setPreferredSize(new Dimension(100, 25));
        dialogPanel.add(label, BorderLayout.NORTH);
        dialogPanel.add(fileName, BorderLayout.SOUTH);
        return dialogPanel;
    }
}
