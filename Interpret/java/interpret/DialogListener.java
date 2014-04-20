package interpret;

public interface DialogListener {
    void onSubFrameReturn(Object value);
    void onSubFrameCancel();
}
