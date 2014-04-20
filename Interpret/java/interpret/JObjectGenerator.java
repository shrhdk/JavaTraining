package interpret;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class JObjectGenerator extends JButton {

    private final Class<?> type;
    private Object value;

    private Set<DialogListener> listeners = new HashSet<DialogListener>();

    public JObjectGenerator(Class<?> type, Object value) {
        if (type.isEnum()) {
            throw new IllegalArgumentException("JObjectGenerator does not support Enum and Array.");
        }

        this.type = type;
        setValue(value);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setEnabled(false);

                if (JObjectGenerator.this.value == null) {
                    // Launch ClassViewer
                    ClassViewer classViewer = new ClassViewer(JObjectGenerator.this.type, dialogListener);
                    classViewer.setVisible(true);
                } else {
                    if (JObjectGenerator.this.value.getClass().isArray()) {
                        // Launch ArrayViewer
                        ArrayViewer arrayViewer = new ArrayViewer((Object[]) JObjectGenerator.this.value, dialogListener);
                        arrayViewer.setVisible(true);
                    } else {
                        // Launch ObjectViewer
                        ObjectViewer objectViewer = new ObjectViewer(JObjectGenerator.this.value, dialogListener);
                        objectViewer.setVisible(true);
                    }
                }
            }
        });
    }

    public void setValue(Object value) {
        this.value = value;
        setText(String.valueOf(value));
    }

    public Object getValue() {
        return value;
    }

    public boolean addDialogListener(DialogListener listener) {
        return listeners.add(listener);
    }

    public boolean removeDialogListener(DialogListener listener) {
        return listeners.remove(listener);
    }

    private DialogListener dialogListener = new DialogListener() {
        @Override
        public void onSubFrameReturn(Object value) {
            setEnabled(true);
            setValue(value);

            for (DialogListener listener : listeners) {
                listener.onSubFrameReturn(getValue());
            }
        }

        @Override
        public void onSubFrameCancel() {
            setEnabled(true);

            for (DialogListener listener : listeners) {
                listener.onSubFrameCancel();
            }
        }
    };
}
