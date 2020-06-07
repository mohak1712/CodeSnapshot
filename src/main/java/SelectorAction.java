import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

public class SelectorAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor edit = e.getData(PlatformDataKeys.EDITOR);
        String selectedText = edit.getSelectionModel().getSelectedText();
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Editor edit = e.getData(PlatformDataKeys.EDITOR);
        e.getPresentation().setEnabledAndVisible(isEnabled(edit));
    }

    private boolean isEnabled(Editor edit) {
        return edit != null && edit.getSelectionModel().getSelectedText() != null
                && edit.getSelectionModel().getSelectedText().length() > 0;
    }
}
