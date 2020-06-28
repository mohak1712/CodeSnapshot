import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

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
        JPanel rootPanel = (JPanel) content.getComponent();
        JScrollPane scrollPane = (JScrollPane) rootPanel.getComponent(0);
        return (EditorTextField) scrollPane.getViewport().getView();
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
