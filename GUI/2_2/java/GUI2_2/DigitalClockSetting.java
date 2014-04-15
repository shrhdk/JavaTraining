package GUI2_2;

import java.awt.*;
import java.util.Arrays;

public class DigitalClockSetting implements Cloneable {

    private final static String[] AVAILABLE_FONT_FAMILIES = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    private String fontFamily;
    private int fontSize;
    private Color fontColor;
    private Color backGroundColor;

    // Constructor
    public DigitalClockSetting(String fontFamily, int fontSize, Color fontColor, Color backGroundColor) {
        setFontFamily(fontFamily);
        setFontSize(fontSize);
        setFontColor(fontColor);
        setBackGroundColor(backGroundColor);
    }

    // Basic methods

    @Override
    public int hashCode() {
        return String.format("%s %d %s %s", fontFamily, fontSize, fontColor, backGroundColor).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DigitalClockSetting)) {
            return false;
        }

        DigitalClockSetting o = (DigitalClockSetting) object;

        return (
                fontFamily.equals(o.fontFamily)
                        && fontSize == o.fontSize
                        && fontColor.equals(o.fontColor)
                        && backGroundColor.equals(o.backGroundColor)
        );
    }

    @Override
    protected DigitalClockSetting clone() {
        try {
            return (DigitalClockSetting) super.clone();
        } catch (CloneNotSupportedException e) {
            // Can't happen because clone is supported.
            throw new InternalError(e.toString());
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    // Utility methods

    public String[] getAvailableFontFamilies() {
        return AVAILABLE_FONT_FAMILIES;
    }

    public Font getFont() {
        return new Font(fontFamily, Font.PLAIN, fontSize);
    }

    // Getter and Setter

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        if (!Arrays.asList(AVAILABLE_FONT_FAMILIES).contains(fontFamily)) {
            throw new IllegalArgumentException(String.format("Font \"%s\" is not available.", fontFamily));
        }

        this.fontFamily = fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        if (fontSize <= 0) {
            throw new IllegalArgumentException("fontSize must be larger than 0");
        }

        this.fontSize = fontSize;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        if (fontColor == null) {
            throw new IllegalArgumentException("fontColor must not be null.");
        }

        this.fontColor = fontColor;
    }

    public Color getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(Color backGroundColor) {
        if (backGroundColor == null) {
            throw new IllegalArgumentException("backGroundColor must not be null.");
        }

        this.backGroundColor = backGroundColor;
    }
}
