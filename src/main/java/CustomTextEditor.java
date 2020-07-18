import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomTextEditor extends EditorTextField {

    public CustomTextEditor(String text, Project project, FileType fileType) {
        super(text, project, fileType);
    }

    @Override
    protected EditorEx createEditor() {
        EditorEx editor = super.createEditor();
        setupEditor(editor);
        setupScroller(editor);
        setupSettings(editor);
        setupListeners(editor);
        return editor;
    }

    private void setupEditor(EditorEx editor) {
        editor.setOneLineMode(false);
        editor.setInsertMode(false);
        editor.setCaretEnabled(false);
        editor.getGutter().registerTextAnnotation(new GutterTextAnnotation(this));
        editor.setViewer(true);
        editor.setVerticalScrollbarVisible(true);
    }

    private void setupScroller(EditorEx editor) {
        JScrollPane scrollPane = editor.getScrollPane();
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 10, false));
    }

    private void setupSettings(EditorEx editor) {
        EditorSettings settings = editor.getSettings();
        settings.setUseSoftWraps(true);
        editor.reinitSettings();
    }

    private void setupListeners(EditorEx editor) {
        editor.addEditorMouseMotionListener(new MouseEventConsumer());
        editor.addEditorMouseListener(new MouseEventConsumer());
    }
}
