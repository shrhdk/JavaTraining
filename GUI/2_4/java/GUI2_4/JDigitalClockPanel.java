package GUI2_4;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.TimerTask;

public class JDigitalClockPanel extends JPanel {

    private static final int MARGIN = 30;

    private DigitalClockSetting digitalClockSetting;

    // Constructor

    public JDigitalClockPanel(DigitalClockSetting digitalClockSetting) {
        setDigitalClockSetting(digitalClockSetting);

        TimerTask countUpTask = new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        };
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(countUpTask, 0, 1000);
    }

    // Getter & Setter

    public DigitalClockSetting getDigitalClockSetting() {
        return digitalClockSetting;
    }

    public void setDigitalClockSetting(DigitalClockSetting digitalClockSetting) {
        if (digitalClockSetting == null) {
            throw new IllegalArgumentException("setting must not be null.");
        }

        this.digitalClockSetting = digitalClockSetting;

        Dimension stringDimension = getStringDimension(digitalClockSetting.getFont(), "00:00:00");
        Dimension panelSize = new Dimension((int) stringDimension.getWidth() + 2 * MARGIN, (int) stringDimension.getHeight() + 2 * MARGIN);
        setSize(panelSize);
        setPreferredSize(panelSize);
    }

    // Calculate Dimension

    private Dimension getStringDimension(Font font, String string) {
        FontMetrics ft = this.getFontMetrics(font);

        int height = ft.getAscent();
        int width = ft.stringWidth(string);

        return new Dimension(width, height);
    }

    private String getCurrentTime() {
        Calendar now = Calendar.getInstance();
        int h = now.get(Calendar.HOUR_OF_DAY);
        int m = now.get(Calendar.MINUTE);
        int s = now.get(Calendar.SECOND);
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    @Override
    public void paintComponent(Graphics g) {

        // Generate time string
        String time = getCurrentTime();

        // Draw background
        g.setColor(digitalClockSetting.getBackGroundColor());
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw time string
        Dimension stringDimension = getStringDimension(digitalClockSetting.getFont(), time);
        g.setFont(digitalClockSetting.getFont());
        g.setColor(digitalClockSetting.getFontColor());
        g.drawString(time, (getWidth() - (int) stringDimension.getWidth()) / 2, MARGIN + (int) stringDimension.getHeight());
    }
}
