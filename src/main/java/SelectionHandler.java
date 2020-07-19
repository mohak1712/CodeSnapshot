import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.NotNull;

public class SelectionHandler extends AnAction implements EditorMouseMotionListener {

    @Override
    public void mouseDragged(@NotNull EditorMouseEvent e) {
        Editor editor = e.getEditor();
        PsiFile psiFile = PsiDocumentManager.getInstance(editor.getProject()).getPsiFile(editor.getDocument());
        String selectedText = editor.getSelectionModel().getSelectedText();

        if (selectedText == null || selectedText.isEmpty()) {
            return;
        }

        ToolWindow toolWindow = ToolWindowManager.getInstance(editor.getProject()).getToolWindow("CodeSnapshot");
        if (!toolWindow.isVisible()) {
            return;
        }

        JBPanel panel = getPanel(toolWindow);
        updateUi(panel);
        updateView(psiFile, selectedText, (EditorTextField) panel.getComponent(1));
    }

    private void updateView(PsiFile psiFile, String selectedText, EditorTextField codeView) {
        if (selectedText.startsWith("\n") && selectedText.endsWith("\n"))
            codeView.setText(selectedText);
        else if (selectedText.startsWith("\n"))
            codeView.setText(selectedText + "\n");
        else if (selectedText.endsWith("\n"))
            codeView.setText("\n" + selectedText);
        else
            codeView.setText("\n" + selectedText + "\n");

        codeView.setFileType(psiFile.getFileType());
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        String selectedText = editor.getSelectionModel().getSelectedText();
        ToolWindow toolWindow = ToolWindowManager.getInstance(e.getProject()).getToolWindow("CodeSnapshot");

        if (!toolWindow.isVisible()) {
            toolWindow.show(() -> {
            });
        }

        JBPanel panel = getPanel(toolWindow);
        updateUi(panel);
        updateView(psiFile, selectedText, (EditorTextField) panel.getComponent(1));
    }

    private void updateUi(JBPanel panel) {
        panel.getComponent(0).setVisible(false);
        panel.getComponent(1).setVisible(true);
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

    private JBPanel getPanel(ToolWindow toolWindow) {
        Content content = toolWindow.getContentManager().getContent(0);
        JBScrollPane scrollPane = (JBScrollPane) content.getComponent();
        JBPanel dataPanel = (JBPanel) scrollPane.getViewport().getView();
        return dataPanel;
    }
}
