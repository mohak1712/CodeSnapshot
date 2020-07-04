import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;

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
        return editor;
    }
}
