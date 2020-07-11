import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.TextAnnotationGutterProvider;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorFontType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

/**
 * A gutter with spaces as text to provide left margin to Editor.
 * There has to be a better way of doing this.
 */
public class GutterTextAnnotation implements TextAnnotationGutterProvider {

    private CustomTextEditor customTextEditor;

    public GutterTextAnnotation(CustomTextEditor customTextEditor) {
        this.customTextEditor = customTextEditor;
    }

    @NotNull
    @Override
    public String getLineText(int line, Editor editor) {
        return "  ";
    }

    @Override
    public @Nullable ColorKey getColor(int line, Editor editor) {
        return null;
    }

    @Override
    public @Nullable String getToolTip(int line, Editor editor) {
        return null;
    }

    @Override
    public EditorFontType getStyle(int line, Editor editor) {
        return null;
    }

    @Override
    public @Nullable Color getBgColor(int line, Editor editor) {
        return customTextEditor.getBackground();
    }

    @Override
    public List<AnAction> getPopupActions(int line, Editor editor) {
        return null;
    }

    @Override
    public void gutterClosed() {

    }

    @Override
    public boolean useMargin() {
        return false;
    }
}
