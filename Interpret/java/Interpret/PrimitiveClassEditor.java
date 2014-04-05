package Interpret;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimitiveClassEditor extends ValueDialog {

    private Class<?> class_;

    private final TextField field = new TextField();
    private final Button okButton = new Button();
    private final Button cancelButton = new Button();

    public PrimitiveClassEditor(Dialog owner, String title, ValueDialogListener listener, Class<?> class_) {
        super(owner, title, listener);

        setSize(200, 100);

        this.class_ = class_;

        okButton.setLabel("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                return_(Utility.toObject(PrimitiveClassEditor.this.class_, field.getText()));
            }
        });

        cancelButton.setLabel("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                return_(null);
            }
        });

        setLayout(new GridLayout(3, 1));
        add(field);
        add(okButton);
        add(cancelButton);

        setVisible(true);
    }
}
