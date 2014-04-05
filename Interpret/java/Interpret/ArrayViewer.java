package Interpret;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrayViewer extends ValueDialog {

    private final Class<?> class_;
    private final int length;

    private final ArrayList arrayList;
    private final Button okButton = new Button();
    private final Button cancelButton = new Button();

    public ArrayViewer(Dialog owner, ValueDialogListener listener, Class<?> class_, int length) {
        super(owner, class_.getCanonicalName() + "[]", listener);

        this.class_ = class_;
        this.length = length;

        arrayList = new ArrayList(this, class_, length);

        // Button
        cancelButton.setLabel("Cancel");
        cancelButton.addActionListener(cancelButtonListener);
        okButton.setLabel("OK");
        okButton.addActionListener(okButtonListener);

        setSize(640, 480);
        setLayout(new GridLayout(3, 1));

        // Add Components
        add(arrayList);
        add(cancelButton);
        add(okButton);

        setVisible(true);
    }

    // Listener

    private ActionListener cancelButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            return_(null);
        }
    };

    private ActionListener okButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            return_(arrayList.getArrayObject());
        }
    };
}
