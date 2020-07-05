import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;
import org.jetbrains.annotations.NotNull;

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
        editor.setViewer(true);
        editor.setVerticalScrollbarVisible(true);
    }

    private void setupScroller(EditorEx editor) {
        JScrollPane scrollPane = editor.getScrollPane();
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 40, true));
    }

    private void setupSettings(EditorEx editor) {
        EditorSettings settings = editor.getSettings();
        settings.setUseSoftWraps(true);
        editor.reinitSettings();
    }

    private void setupListeners(EditorEx editor) {
        editor.addEditorMouseMotionListener(new EditorMouseMotionListener() {
            @Override
            public void mouseMoved(@NotNull EditorMouseEvent e) {
                e.consume();
            }

            @Override
            public void mouseDragged(@NotNull EditorMouseEvent e) {
                e.consume();
            }
        });

        editor.addEditorMouseListener(new EditorMouseListener() {
            @Override
            public void mousePressed(@NotNull EditorMouseEvent event) {
                event.consume();
            }

            @Override
            public void mouseClicked(@NotNull EditorMouseEvent event) {
                event.consume();
            }

            @Override
            public void mouseReleased(@NotNull EditorMouseEvent event) {
                event.consume();
            }

            @Override
            public void mouseEntered(@NotNull EditorMouseEvent event) {
                event.consume();
            }

            @Override
            public void mouseExited(@NotNull EditorMouseEvent event) {
                event.consume();
            }
        });
    }
}
