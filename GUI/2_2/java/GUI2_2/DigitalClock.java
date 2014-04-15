package GUI2_2;

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

    private final PreferenceManager preferenceManager = new PreferenceManager("be.shiro.java.tokei2-1");
    private final JDigitalClockPanel clockPanel;


    public DigitalClock() {
        DigitalClockSetting setting = preferenceManager.load();
        Point windowPosition = preferenceManager.loadWindowPosition();
        clockPanel = new JDigitalClockPanel(setting);

        setResizable(false);
        setLocation(windowPosition);
        getContentPane().setLayout(null);
        getContentPane().add(clockPanel);
        getContentPane().setPreferredSize(clockPanel.getSize());
        pack();

        setupListener();
        setupMenuBar();
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
                try {
                    preferenceManager.save(clockPanel.getDigitalClockSetting(), getLocation());
                } catch (BackingStoreException exception) {
                    exception.printStackTrace();
                } finally {
                    System.exit(0);
                }
            }
        });
    }

    private void setupMenuBar() {
        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        Menu menuFile = new Menu("File");
        menuBar.add(menuFile);
        MenuItem menuSettings = new MenuItem("Settings", new MenuShortcut('O'));
        menuSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingDialog dialog = new SettingDialog(DigitalClock.this);
                dialog.setVisible(true);
            }
        });
        menuFile.add(menuSettings);
    }
}
