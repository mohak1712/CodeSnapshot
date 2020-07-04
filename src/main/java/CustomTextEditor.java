import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;
import org.jetbrains.annotations.NotNull;

public class CustomTextEditor extends EditorTextField {

    public CustomTextEditor(String text, Project project, FileType fileType) {
        super(text, project, fileType);
    }

    @Override
    protected EditorEx createEditor() {
        EditorEx editor = super.createEditor();
        editor.setOneLineMode(false);
        editor.setInsertMode(false);
        editor.setCaretEnabled(false);
        editor.setViewer(true);
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

        return editor;
    }
}
