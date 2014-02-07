package GUI1_3;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Tokei extends Window implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int MARGIN = 30;

	// Buffer
	private Image imageBuffer;
	private Graphics graphicsBuffer;

	// Mouse
	private int x, y;

	// Menu
	PopupMenu popup;

	// Settings
	public String fontFamily = null;
	public int fontSize = 100;
	public Color fontColor = Color.BLACK;
	public Color backGroundColor = Color.WHITE;

	public Tokei() {
		super(null, null);

		// ポップアップメニュー(フォント、フォントサイズ、文字色、背景色、終了)
		popup = new PopupMenu();
		add(popup);
		Menu menu;

		// フォントメニュー
		Menu menuFont = new Menu("Font");
		popup.add(menuFont);
		ActionListener menuFontListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tokei.this.fontFamily = ((MenuItem) e.getSource()).getLabel();
			}
		};
		// Get font list
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fontFamilies = ge.getAvailableFontFamilyNames();
		// Set label
		for (String label : fontFamilies) {
			menu = new Menu(label);
			menu.addActionListener(menuFontListener);
			menuFont.add(menu);
		}

		// フォントサイズメニュー
		Menu menuSize = new Menu("Size");
		popup.add(menuSize);
		ActionListener menuSizeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tokei.this.fontSize = Integer.parseInt(((MenuItem) e
						.getSource()).getLabel());
			}
		};
		for (String label : new String[] { "100", "200", "300" }) {
			menu = new Menu(label);
			menu.addActionListener(menuSizeListener);
			menuSize.add(menu);
		}

		// 文字色メニュー
		Menu menuColor = new Menu("Font Color");
		popup.add(menuColor);
		ActionListener menuColorListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tokei.this.fontColor = colorNameToColor(((MenuItem) e.getSource()).getLabel());
			}
		};
		for (String label : new String[] { "Black", "White", "Red", "Green",
				"Blue" }) {
			menu = new Menu(label);
			menu.addActionListener(menuColorListener);
			menuColor.add(menu);
		}

		// 背景メニュー
		Menu menuBgColor = new Menu("Background Color");
		popup.add(menuBgColor);
		ActionListener menuBgColorListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tokei.this.backGroundColor = colorNameToColor(((MenuItem) e.getSource()).getLabel());
			}
		};
		for (String label : new String[] { "Black", "White", "Red", "Green",
				"Blue" }) {
			menu = new Menu(label);
			menu.addActionListener(menuBgColorListener);
			menuBgColor.add(menu);
		}

		// 終了メニュー
		Menu menuExit = new Menu("Exit");
		popup.add(menuExit);
		ActionListener menuExitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		menuExit.addActionListener(menuExitListener);

		// マウス
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				if (e.isPopupTrigger()) {
					Tokei.this.popup.show(Tokei.this, e.getX(), e.getY());
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.isPopupTrigger()) {
					Tokei.this.popup.show(Tokei.this, e.getX(), e.getY());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					Tokei.this.popup.show(Tokei.this, e.getX(), e.getY());
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - x, p.y + e.getY() - y);
			}
		});

		setVisible(true);
	}

	public void run() {
		for (;;) {
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	public void paint(Graphics g) {
		// 時間文字列を算出
		Calendar now = Calendar.getInstance();
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);
		String time = String.format("%02d:%02d:%02d", h, m, s);

		// 文字列の大きさを測る
		if (fontFamily != null)
			g.setFont(new Font(fontFamily, Font.PLAIN, fontSize));
		else
			g.setFont(new Font("Serif", Font.PLAIN, fontSize));

		FontMetrics ft = g.getFontMetrics();
		int stringHeight = ft.getAscent();
		int stringWidth = ft.stringWidth(time);
		int standardWidth = ft.stringWidth("00:00:00");

		// フレームの大きさを再設定
		int height = stringHeight + MARGIN * 2;
		int width = standardWidth + MARGIN * 2;
		setSize(width, height);

		// バッファーイメージを作成
		if (imageBuffer == null || imageBuffer.getWidth(this) != width
				|| imageBuffer.getHeight(this) != height) {
			imageBuffer = createImage(width, height);
			graphicsBuffer = null;
		}

		// バッファーグラフィックスを作成
		if (graphicsBuffer == null) {
			graphicsBuffer = imageBuffer.getGraphics();
		}

		// 背景を塗りつぶす
		graphicsBuffer.setColor(backGroundColor);
		graphicsBuffer.fillRect(0, 0, width, height);

		// 時間文字列を描画
		if (fontFamily != null)
			graphicsBuffer.setFont(new Font(fontFamily, Font.PLAIN, fontSize));
		else
			graphicsBuffer.setFont(new Font("Serif", Font.PLAIN, fontSize));
		graphicsBuffer.setColor(fontColor);
		graphicsBuffer.drawString(time, MARGIN + (standardWidth - stringWidth)
				/ 2, MARGIN + stringHeight);

		// バッファを画面にコピーする
		g.drawImage(imageBuffer, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	private static Color colorNameToColor(String colorName) {
		String intern = colorName.intern();
		if (intern == "Black")
			return Color.BLACK;
		else if (intern == "White")
			return Color.WHITE;
		else if (intern == "Red")
			return Color.RED;
		else if (intern == "Green")
			return Color.GREEN;
		else if (intern == "Blue")
			return Color.BLUE;
		else
			return null;
	}

	public static void main(String args[]) {
		Tokei tokei = new Tokei();
		Thread thread = new Thread(tokei);
		thread.start();
	}
}