package GUI2_4;

import javax.swing.*;
import java.awt.*;

public class ColorListCellRenderer implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
        final Color color = (Color) value;

        // Color chip
        final JPanel colorChip = new JPanel();
        colorChip.setBackground(color);
        colorChip.setSize(10, 10);  // TODO: Fix hard code
        colorChip.setPreferredSize(colorChip.getSize());

        // Label
        final String colorString = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        final JLabel label = new JLabel(colorString);

        // Panel
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(colorChip);
        panel.add(label);

        if(isSelected || hasFocus) {
            panel.setForeground(Color.WHITE);
            panel.setBackground(Color.BLUE);
            panel.setOpaque(true);
        }

        return panel;

    }
}
