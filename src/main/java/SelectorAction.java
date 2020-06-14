import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class SelectorAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor edit = e.getData(PlatformDataKeys.EDITOR);
        String selectedText = edit.getSelectionModel().getSelectedText();
        ToolWindow toolWindow = ToolWindowManager.getInstance(e.getProject()).getToolWindow("CodeSnapshot");

        Content content = toolWindow.getContentManager().getContent(0);
        JPanel component = (JPanel) content.getComponent();

        if (!toolWindow.isVisible()) {
            toolWindow.show(() -> {
            });
        }

        JScrollPane scrollPane = (JScrollPane) component.getComponent(0);
        JTextArea textArea = (JTextArea) scrollPane.getViewport().getView();
        textArea.setBackground(edit.getColorsScheme().getDefaultBackground());
        textArea.setText(selectedText);
        textArea.setFont(new Font(edit.getColorsScheme().getEditorFontName(), Font.PLAIN,
                edit.getColorsScheme().getEditorFontSize()));
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
}
