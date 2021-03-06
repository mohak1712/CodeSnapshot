import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
        JBPanel panel = getPanel(window);
        panel.getComponent(1).setVisible(false);
        JTextPane instructions = (JTextPane) panel.getComponent(0);
        formatText(instructions);
    }

    private void formatText(JTextPane textPane) {
        textPane.setBackground(new Color(60, 63, 65));
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textPane.setEditable(false);
        StyledDocument doc = textPane.getStyledDocument();

        Style keywordStyle = textPane.addStyle("keyword", null);
        StyleConstants.setForeground(keywordStyle, new Color(204, 120, 50));

        Style paramStyle = textPane.addStyle("param", null);
        StyleConstants.setForeground(paramStyle, new Color(106, 135, 89));
        getInstructions().forEach(s -> {
            try {
                doc.insertString(doc.getLength(), s.substring(0, 6), keywordStyle);
                doc.insertString(doc.getLength(), s.substring(6, s.lastIndexOf(')')), paramStyle);
                doc.insertString(doc.getLength(), ")\n\n", keywordStyle);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });

    }

    private List<String> getInstructions() {
        List<String> instructions = new ArrayList<>();
        instructions.add("print(\"1. Select some text in editor\")");
        instructions.add("print(\"2. Right click and select `Code Snapshot`\")");
        instructions.add("print(\"3. Drag mouse or use keyboard to change selection\")");
        instructions.add("print(\"4. Click on camera to save as image\")");
        return instructions;
    }

    private JBPanel getPanel(ToolWindow toolWindow) {
        Content content = toolWindow.getContentManager().getContent(0);
        JBScrollPane scrollPane = (JBScrollPane) content.getComponent();
        JBPanel dataPanel = (JBPanel) scrollPane.getViewport().getView();
        return dataPanel;
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
