import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class SnapToolWindow implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, com.intellij.openapi.wm.@NotNull ToolWindow toolWindow) {
        SnapView snapView = new SnapView(project);
        ContentFactory instance = ContentFactory.SERVICE.getInstance();
        Content content = instance.createContent(snapView.getComponent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public void init(com.intellij.openapi.wm.ToolWindow window) {
        EditorTextField codeView = getTextPane(window);
    }

    private EditorTextField getTextPane(ToolWindow toolWindow) {
        Content content = toolWindow.getContentManager().getContent(0);
        JBScrollPane scrollPane = (JBScrollPane) content.getComponent();
        JBPanel dataPanel = (JBPanel) scrollPane.getViewport().getView();
        return (EditorTextField) dataPanel.getComponent(0);
    }

    @Override
    public boolean shouldBeAvailable(@NotNull Project project) {
        return true;
    }

    @Override
    public boolean isDoNotActivateOnStart() {
        return true;
    }
}
