package GUI1_4;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Tokei extends Window implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int MARGIN = 30;
	
	// Default settings
	private static final String DEFAULT_FONT_FAMILY = Font.SANS_SERIF;
	private static final int DEFAULT_FONT_SIZE = 100;
	private static final PresetColor DEFAULT_FONT_COLOR = PresetColor.BLACK;
	private static final PresetColor DEFAULT_BACKGROUND_COLOR = PresetColor.WHITE;

	// Buffer
	private Image imageBuffer;
	private Graphics graphicsBuffer;

	// Mouse
	private int x, y;

	// Menu
	PopupMenu popup;

	// Settings
	public String fontFamily = DEFAULT_FONT_FAMILY;
	public int fontSize = DEFAULT_FONT_SIZE;
	public PresetColor fontColor = DEFAULT_FONT_COLOR;
	public PresetColor backGroundColor = DEFAULT_BACKGROUND_COLOR;

	public Tokei() {
		super(null, null);
		
		// 終了時の設定を読み込む
		load();

		// ポップアップメニュー(設定)
		MenuItem menuSetting = new MenuItem("Settings...");
		menuSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dialog dialog = new SettingDialog(Tokei.this);
				dialog.setVisible(true);
			}
		});

		// ポップアップメニュー(終了)
		MenuItem menuExit = new MenuItem("Exit");
		menuExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
				System.exit(0);
			}
		});

		// ポップアップメニュー
		popup = new PopupMenu();
		popup.add(menuSetting);
		popup.add(menuExit);
		add(popup);

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
		graphicsBuffer.setColor(backGroundColor.getColor());
		graphicsBuffer.fillRect(0, 0, width, height);

		// 時間文字列を描画
		if (fontFamily != null)
			graphicsBuffer.setFont(new Font(fontFamily, Font.PLAIN, fontSize));
		else
			graphicsBuffer.setFont(new Font("Serif", Font.PLAIN, fontSize));
		graphicsBuffer.setColor(fontColor.getColor());
		graphicsBuffer.drawString(time, MARGIN + (standardWidth - stringWidth)
				/ 2, MARGIN + stringHeight);

		// バッファを画面にコピーする
		g.drawImage(imageBuffer, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	// 設定保存
	
	private static final String FONT_NAME = "FONT_NAME";
	private static final String FONT_SIZE = "FONT_SIZE";
	private static final String FONT_COLOR = "FONT_COLOR";
	private static final String BACKGROUND_COLOR = "BACKGROUND_COLOR";
	private static final String WINDOW_LEFT = "WINDOW_LEFT";
	private static final String WINDOW_TOP = "WINDOW_TOP";

	private final Preferences prefs = Preferences.userRoot().node("be.shiro.java.tokei");
	
	private void load() {
		fontFamily = prefs.get(FONT_NAME, Font.SANS_SERIF);
		fontSize = prefs.getInt(FONT_SIZE, 100);
		fontColor = PresetColor.valueOf(prefs.get(FONT_COLOR, DEFAULT_FONT_COLOR.name()));
		backGroundColor = PresetColor.valueOf(prefs.get(BACKGROUND_COLOR, DEFAULT_BACKGROUND_COLOR.name()));
		int x = prefs.getInt(WINDOW_LEFT, 0);
		int y = prefs.getInt(WINDOW_TOP, 0);
		setLocation(x, y);
	}

	private void save() {
        try {
        	prefs.put(FONT_NAME, fontFamily);
        	prefs.put(FONT_SIZE, String.valueOf(fontSize));
        	prefs.put(FONT_COLOR, fontColor.name());
        	prefs.put(BACKGROUND_COLOR, backGroundColor.name());
        	prefs.put(WINDOW_LEFT, String.valueOf(getLocation().x));
            prefs.put(WINDOW_TOP, String.valueOf(getLocation().y));
            prefs.flush();
        } catch (BackingStoreException ex) {
            ex.printStackTrace();
        }
	}

	public static void main(String args[]) {
		Tokei tokei = new Tokei();
		Thread thread = new Thread(tokei);
		thread.start();
	}
}