import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SelectionHandler implements EditorMouseMotionListener {

    @Override
    public void mouseDragged(@NotNull EditorMouseEvent e) {
        String selectedText = e.getEditor().getSelectionModel().getSelectedText();
        if (selectedText != null && selectedText.isEmpty()) {
            return;
        }

        ToolWindow toolWindow = ToolWindowManager.getInstance(e.getEditor().getProject())
                .getToolWindow("CodeSnapshot");
        if (!toolWindow.isVisible()) {
            return;
        }

        Content content = toolWindow.getContentManager().getContent(0);
        JPanel component = (JPanel) content.getComponent();
        JScrollPane scrollPane = (JScrollPane) component.getComponent(0);
        JTextArea textArea = (JTextArea) scrollPane.getViewport().getView();
        textArea.setBackground(e.getEditor().getColorsScheme().getDefaultBackground());
        textArea.setText(selectedText);
    }
}
