package GUI2_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.BackingStoreException;

public class DigitalClock extends JFrame {

    public static void main(String args[]) {
        DigitalClock digitalClock = new DigitalClock();
        digitalClock.setVisible(true);
    }

    private final JDigitalClockPanel clockPanel;

    public DigitalClock() {
        DigitalClockSetting setting = new DigitalClockSetting(Font.SANS_SERIF, 100, Color.BLACK, Color.WHITE);
        clockPanel = new JDigitalClockPanel(setting);

        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(clockPanel);
        getContentPane().setPreferredSize(clockPanel.getSize());
        pack();

        setupListener();
    }

    public DigitalClockSetting getDigitalClockSetting() {
        return clockPanel.getDigitalClockSetting();
    }

    public void onSettingDialogClose(DigitalClockSetting setting) {
        clockPanel.setDigitalClockSetting(setting);
        getContentPane().setPreferredSize(clockPanel.getSize());
        pack();
    }

    private void setupListener() {
        // on close button clicked
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
