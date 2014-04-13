package interpret;

import javax.swing.*;
import java.awt.*;

public class LabelGenerator {
    public static JLabel getSelectedJLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        label.setBackground(Color.blue);
        label.setOpaque(true);
        return label;
    }

    public static JLabel getDisabledJLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.gray);
        return label;
    }

    public static JLabel getDisabledAndSelectedJLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.gray);
        label.setBackground(Color.blue);
        return label;
    }
}
