import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;

import javax.swing.*;
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
        setupListeners(editor);
        setupSettings(editor);
        setupHighlightingFilter((EditorImpl) editor);
        return editor;
    }

    private void setupEditor(EditorEx editor) {
        editor.setOneLineMode(false);
        editor.setInsertMode(false);
        editor.setCaretEnabled(false);
        editor.getGutter().registerTextAnnotation(new GutterTextAnnotation(this));
        editor.setViewer(true);
        editor.setVerticalScrollbarVisible(true);
        EditorColorsManager colorsManager = EditorColorsManager.getInstance();
        EditorColorsScheme customGlobalScheme = colorsManager.getSchemeForCurrentUITheme();
        editor.setColorsScheme(customGlobalScheme);
        editor.getColorsScheme().setFontPreferences(customGlobalScheme.getFontPreferences());
    }

    private void setupScroller(EditorEx editor) {
        JScrollPane scrollPane = editor.getScrollPane();
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }

    private void setupSettings(EditorEx editor) {
        EditorSettings settings = editor.getSettings();
        settings.setUseSoftWraps(true);
        settings.setAllowSingleLogicalLineFolding(true);
        settings.setAutoCodeFoldingEnabled(false);
        settings.setFoldingOutlineShown(false);
        editor.reinitSettings();
    }

    private void setupListeners(EditorEx editor) {
        editor.addEditorMouseMotionListener(new MouseEventConsumer());
        editor.addEditorMouseListener(new MouseEventConsumer());
    }

    private void setupHighlightingFilter(EditorImpl editor) {
        editor.setHighlightingFilter(rangeHighlighter -> {
            if (rangeHighlighter.getLayer() == HighlighterLayer.ERROR)
                return false;
            HighlightInfo info = HighlightInfo.fromRangeHighlighter(rangeHighlighter);
            if (info != null && info.getSeverity().compareTo(HighlightSeverity.GENERIC_SERVER_ERROR_OR_WARNING) >= 0)
                return false;
            return true;
        });
    }
}
