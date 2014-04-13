package interpret;

import javax.swing.*;

public abstract class SubFrame extends JFrame {

    private final Parent listener;

    protected SubFrame(Parent listener) {
        this.listener = listener;
    }

    protected void return_(Object value) {
        setVisible(false);
        if (listener != null) {
            listener.onSubFrameClose(value);
        }
        dispose();
    }
}
