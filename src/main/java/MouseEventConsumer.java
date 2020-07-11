import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import org.jetbrains.annotations.NotNull;

public class MouseEventConsumer implements EditorMouseListener, EditorMouseMotionListener {

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

    @Override
    public void mouseMoved(@NotNull EditorMouseEvent event) {
        event.consume();
    }

    @Override
    public void mouseDragged(@NotNull EditorMouseEvent event) {
        event.consume();
    }
}
