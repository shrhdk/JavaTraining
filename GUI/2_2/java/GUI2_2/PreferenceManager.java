package GUI2_2;

import java.awt.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferenceManager {

    private static final String FONT_NAME = "FONT_NAME";
    private static final String FONT_SIZE = "FONT_SIZE";
    private static final String FONT_COLOR = "FONT_COLOR";
    private static final String BACKGROUND_COLOR = "BACKGROUND_COLOR";
    private static final String WINDOW_LEFT = "WINDOW_LEFT";
    private static final String WINDOW_TOP = "WINDOW_TOP";

    private static final String DEFAULT_FONT_FAMILY = Font.SANS_SERIF;
    private static final int DEFAULT_FONT_SIZE = 100;
    private static final String DEFAULT_FONT_COLOR = String.valueOf(Color.BLACK.getRGB());
    private static final String DEFAULT_BACKGROUND_COLOR = String.valueOf(Color.WHITE.getRGB());

    private static final int DEFAULT_WINDOW_POSITION_X = 0;
    private static final int DEFAULT_WINDOW_POSITION_Y = 0;

    private final Preferences prefs;

    public PreferenceManager(String key) {
        if (key == null || key.equals("")) {
            throw new IllegalArgumentException("key must not be null and empty.");
        }

        prefs = Preferences.userRoot().node(key);
    }

    public void save(DigitalClockSetting setting, Point windowPosition) throws BackingStoreException {
        save(setting);
        save(windowPosition);
    }

    public void save(DigitalClockSetting setting) throws BackingStoreException {
        prefs.put(FONT_NAME, setting.getFontFamily());
        prefs.put(FONT_SIZE, String.valueOf(setting.getFontSize()));
        prefs.put(FONT_COLOR, String.valueOf(setting.getFontColor().getRGB()));
        prefs.put(BACKGROUND_COLOR, String.valueOf(setting.getBackGroundColor().getRGB()));
        prefs.flush();
    }

    public void save(Point windowPosition) throws BackingStoreException {
        prefs.put(WINDOW_LEFT, String.valueOf(windowPosition.x));
        prefs.put(WINDOW_TOP, String.valueOf(windowPosition.y));
        prefs.flush();
    }

    public DigitalClockSetting load() {
        String fontFamily = prefs.get(FONT_NAME, DEFAULT_FONT_FAMILY);
        int fontSize = prefs.getInt(FONT_SIZE, DEFAULT_FONT_SIZE);
        Color fontColor = new Color(Integer.parseInt(prefs.get(FONT_COLOR, DEFAULT_FONT_COLOR)));
        Color backGroundColor = new Color(Integer.parseInt(prefs.get(BACKGROUND_COLOR, DEFAULT_BACKGROUND_COLOR)));
        return new DigitalClockSetting(fontFamily, fontSize, fontColor, backGroundColor);
    }

    public Point loadWindowPosition() {
        int x = prefs.getInt(WINDOW_LEFT, DEFAULT_WINDOW_POSITION_X);
        int y = prefs.getInt(WINDOW_TOP, DEFAULT_WINDOW_POSITION_Y);
        return new Point(x, y);
    }
}
