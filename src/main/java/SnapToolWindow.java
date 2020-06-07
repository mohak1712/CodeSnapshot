import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class SnapToolWindow implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, com.intellij.openapi.wm.@NotNull ToolWindow toolWindow) {
        SnapView snapView = new SnapView(toolWindow);
        ContentFactory instance = ContentFactory.SERVICE.getInstance();
        Content content = instance.createContent(snapView.getComponent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public void init(com.intellij.openapi.wm.ToolWindow window) {

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
