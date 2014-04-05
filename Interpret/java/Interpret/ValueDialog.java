package Interpret;

import java.awt.*;

public abstract class ValueDialog extends Dialog {

    private final ValueDialogListener listener;

    public ValueDialog(Dialog owner, String title, ValueDialogListener listener) {
        super(owner, title, true);
        this.listener = listener;
    }

    protected void return_(Object returnValue) {
        setVisible(false);
        if (listener != null) {
            listener.onDialogClose(returnValue);
        }
    }
}
