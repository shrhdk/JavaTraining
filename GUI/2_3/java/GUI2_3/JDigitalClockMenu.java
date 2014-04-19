package GUI2_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class JDigitalClockMenu extends JPopupMenu {

    private static final int[] FONT_SIZE_LIST = {50, 100, 150, 200};
    private static final Color[] FONT_COLOR_LIST = new Color[]{Color.WHITE, Color.BLACK, Color.RED, Color.GREEN, Color.BLUE};
    private static final Color[] BACKGROUND_COLOR_LIST = new Color[]{Color.WHITE, Color.BLACK, Color.RED, Color.GREEN, Color.BLUE};

    protected final DigitalClockSetting setting;
    protected final Set<DigitalClockMenuListener> listeners = new HashSet<DigitalClockMenuListener>();

    public JDigitalClockMenu(DigitalClockSetting setting) {
        this.setting = setting.clone();

        add(new JFontFamilyMenu());
        add(new JFontSizeMenu());
        add(new JFontColorMenu());
        add(new JBackgroundColorMenu());

        // Exit menu
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        add(exitMenuItem);
    }

    public boolean addListener(DigitalClockMenuListener listener) {
        return listeners.add(listener);
    }

    public boolean removeListener(DigitalClockMenuListener listener) {
        return listeners.remove(listener);
    }

    public interface DigitalClockMenuListener {
        void onChange(DigitalClockSetting setting);
    }

    private void onChange(DigitalClockSetting setting) {
        for(DigitalClockMenuListener listener : listeners) {
            listener.onChange(setting.clone());
        }
    }

    private String colorToString (Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    private class JFontFamilyMenu extends JMenu {
        public JFontFamilyMenu() {
            super("Font");

            for (final String fontFamily : DigitalClockSetting.getAvailableFontFamilies()) {
                JMenuItem menuItem = new JMenuItem(fontFamily);
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        setting.setFontFamily(fontFamily);
                        onChange(setting);
                    }
                });
                add(menuItem);
            }
        }
    }

    private class JFontSizeMenu extends JMenu {

        public JFontSizeMenu() {
            super("Font size");

            for(final int size : FONT_SIZE_LIST) {
                JMenuItem menuItem = new JMenuItem(String.valueOf(size));
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        setting.setFontSize(size);
                        onChange(setting);
                    }
                });
                add(menuItem);
            }
        }
    }

    private class JFontColorMenu extends JMenu {
        public JFontColorMenu() {
            super("Font color");

            for(final Color color : FONT_COLOR_LIST) {
                final JMenuItem menuItem = new JMenuItem(colorToString(color));
                menuItem.setForeground(color);
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        setting.setFontColor(color);
                        onChange(setting);
                    }
                });
                add(menuItem);
            }
        }
    }

    private class JBackgroundColorMenu extends JMenu {
        public JBackgroundColorMenu() {
            super("Background color");

            for (final Color color : BACKGROUND_COLOR_LIST) {
                final JMenuItem menuItem = new JMenuItem(colorToString(color));
                menuItem.setBackground(color);
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        setting.setBackGroundColor(color);
                        onChange(setting);
                    }
                });
                add(menuItem);
            }
        }
    }
}
