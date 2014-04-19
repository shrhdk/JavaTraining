package GUI2_3;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.*;

public class DigitalClock extends JWindow implements JDigitalClockMenu.DigitalClockMenuListener {

    // Mouse
    private int x, y;

    //
    private final JDigitalClockMenu menu;

    public static void main(String args[]) {
        DigitalClock digitalClock = new DigitalClock();
        digitalClock.setVisible(true);
    }

    private final JDigitalClockPanel clockPanel;

    public DigitalClock() {
        DigitalClockSetting setting = new DigitalClockSetting(Font.SANS_SERIF, 100, Color.BLACK, Color.WHITE);
        clockPanel = new JDigitalClockPanel(setting);

        // Make Window Draggable
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - x, p.y + e.getY() - y);
            }
        });

        // Popup
        menu = new JDigitalClockMenu(setting);
        menu.addListener(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                if (e.isPopupTrigger()) {
                    DigitalClock.this.menu.show(DigitalClock.this, e.getX(), e.getY());
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    DigitalClock.this.menu.show(DigitalClock.this, e.getX(), e.getY());
                }
            }
        });

        getContentPane().setLayout(null);
        getContentPane().add(clockPanel);
        getContentPane().setPreferredSize(clockPanel.getSize());
        pack();
    }

    @Override
    public void onChange(DigitalClockSetting setting) {
        clockPanel.setDigitalClockSetting(setting);
        getContentPane().setPreferredSize(clockPanel.getSize());
        pack();
    }
}
