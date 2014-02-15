package GUI1_4;

import java.awt.Color;

public enum PresetColor {
	BLACK("黒", 0, 0, 0),
	WHITE("白", 255, 255, 255),
	RED("赤", 255, 0, 0),
	GREEN("緑", 0, 255, 0),
	BLUE("青", 0, 0, 255);
	

	private final String name;
	private final Color color;

	private PresetColor(String name, int r, int g, int b) {
		this.name = name;
		this.color = new Color(r, g, b);
	}

	public Color getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
