package jpl.chapter6.exercise4;

public enum Signal {

	RED {
		@Override
		public Color getColor() {
			return new Color(255, 0, 0);
		}
	},
	GREEN {
		@Override
		public Color getColor() {
			return new Color(0, 255, 0);
		}
	},
	YELLOW {
		@Override
		public Color getColor() {
			return new Color(255, 255, 0);
		}
	};

	public abstract Color getColor();

	public static class Color {
		public int r, g, b;

		public Color(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
	}
}
