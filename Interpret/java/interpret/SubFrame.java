package interpret;

import javax.swing.*;

public abstract class SubFrame extends JFrame {

    private final DialogListener listener;

    protected SubFrame(DialogListener listener) {
        this.listener = listener;
    }

    protected void return_(Object value) {
        setVisible(false);
        if (listener != null) {
            listener.onSubFrameReturn(value);
        }
        dispose();
    }

    protected void return_() {
        setVisible(false);
        if (listener != null) {
            listener.onSubFrameCancel();
        }
        dispose();
    }
}
