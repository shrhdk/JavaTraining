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
		// ���ԕ�������Z�o
		Calendar now = Calendar.getInstance();
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);
		String time = String.format("%02d:%02d:%02d", h, m, s);

		// ������̑傫���𑪂�
		if (fontFamily != null)
			g.setFont(new Font(fontFamily, Font.PLAIN, fontSize));
		else
			g.setFont(new Font("Serif", Font.PLAIN, fontSize));

		FontMetrics ft = g.getFontMetrics();
		int stringHeight = ft.getAscent();
		int stringWidth = ft.stringWidth(time);
		int standardWidth = ft.stringWidth("00:00:00");

		// �t���[���̑傫�����Đݒ�
		int height = stringHeight + MARGIN * 2;
		int width = standardWidth + MARGIN * 2;
		setSize(width, height);

		// �o�b�t�@�[�C���[�W���쐬
		if (imageBuffer == null || imageBuffer.getWidth(this) != width
				|| imageBuffer.getHeight(this) != height) {
			imageBuffer = createImage(width, height);
			graphicsBuffer = null;
		}

		// �o�b�t�@�[�O���t�B�b�N�X���쐬
		if (graphicsBuffer == null) {
			graphicsBuffer = imageBuffer.getGraphics();
		}

		// �w�i��h��Ԃ�
		graphicsBuffer.setColor(backGroundColor);
		graphicsBuffer.fillRect(0, 0, width, height);

		// ���ԕ������`��
		if (fontFamily != null)
			graphicsBuffer.setFont(new Font(fontFamily, Font.PLAIN, fontSize));
		else
			graphicsBuffer.setFont(new Font("Serif", Font.PLAIN, fontSize));
		graphicsBuffer.setColor(fontColor);
		graphicsBuffer.drawString(time, MARGIN + (standardWidth - stringWidth)
				/ 2, MARGIN + stringHeight);

		// �o�b�t�@����ʂɃR�s�[����
		g.drawImage(imageBuffer, 0, 0, this);
	}

	public static void main(String args[]) {
		Tokei tokei = new Tokei();
		Thread thread = new Thread(tokei);
		thread.start();
	}
}