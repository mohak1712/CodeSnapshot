import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class SelectionHandler extends AnAction implements EditorMouseMotionListener {

    @Override
    public void mouseDragged(@NotNull EditorMouseEvent e) {
        Editor editor = e.getEditor();
        String selectedText = editor.getSelectionModel().getSelectedText();
        if (selectedText == null || selectedText.isEmpty()) {
            return;
        }

        ToolWindow toolWindow = ToolWindowManager.getInstance(editor.getProject()).getToolWindow("CodeSnapshot");
        if (!toolWindow.isVisible()) {
            return;
        }

        JTextArea textArea = getTextArea(editor, toolWindow);
        formatText(textArea, editor, selectedText);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
        ToolWindow toolWindow = ToolWindowManager.getInstance(e.getProject()).getToolWindow("CodeSnapshot");

        if (!toolWindow.isVisible()) {
            toolWindow.show(() -> {
            });
        }

        JTextArea textArea = getTextArea(editor, toolWindow);
        formatText(textArea, editor, selectedText);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Editor edit = e.getData(PlatformDataKeys.EDITOR);
        e.getPresentation().setEnabledAndVisible(shouldEnable(edit));
    }

    private boolean shouldEnable(Editor edit) {
        return edit != null && edit.getSelectionModel().getSelectedText() != null
                && edit.getSelectionModel().getSelectedText().length() > 0;
    }

    private JTextArea getTextArea(Editor editor, ToolWindow toolWindow) {
        Content content = toolWindow.getContentManager().getContent(0);
        JPanel rootPanel = (JPanel) content.getComponent();
        JScrollPane scrollPane = (JScrollPane) rootPanel.getComponent(0);
        JPanel dataPanel = (JPanel) scrollPane.getViewport().getView();
        dataPanel.setBackground(editor.getColorsScheme().getDefaultBackground());
        return (JTextArea) dataPanel.getComponent(0);
    }

    private void formatText(JTextArea textArea, Editor editor, String selectedText) {
        textArea.setBackground(editor.getColorsScheme().getDefaultBackground());
        textArea.setText(selectedText);
        textArea.setFont(new Font(editor.getColorsScheme().getEditorFontName(), Font.PLAIN,
                editor.getColorsScheme().getEditorFontSize()));
    }
}
