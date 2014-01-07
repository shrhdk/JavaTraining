package GUI1_2;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Tokei extends Frame implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int MARGIN = 30;

	// Buffer
	Image imageBuffer;
	Graphics graphicsBuffer;

	// Settings
	public String fontFamily = null;
	public int fontSize = 50;
	public Color fontColor = Color.BLACK;
	public Color backGroundColor = Color.WHITE;

	public Tokei() {
		super();

		setResizable(false);
		setVisible(true);

		// Close
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Menu
		MenuBar menuBar = new MenuBar();
		setMenuBar(menuBar);
		Menu menuFile = new Menu("File");
		menuBar.add(menuFile);
		MenuItem menuSettings = new MenuItem("Settings", new MenuShortcut('O'));
		menuSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dialog dialog = new SettingDialog(Tokei.this);
				dialog.setVisible(true);
			}
		});
		menuFile.add(menuSettings);
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

	public static void main(String args[]) {
		Tokei tokei = new Tokei();
		Thread thread = new Thread(tokei);
		thread.start();
	}
}