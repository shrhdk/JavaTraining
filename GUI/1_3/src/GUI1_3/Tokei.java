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

		// �|�b�v�A�b�v���j���[(�t�H���g�A�t�H���g�T�C�Y�A�����F�A�w�i�F�A�I��)
		popup = new PopupMenu();
		add(popup);
		Menu menu;

		// �t�H���g���j���[
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

		// �t�H���g�T�C�Y���j���[
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

		// �����F���j���[
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

		// �w�i���j���[
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

		// �I�����j���[
		Menu menuExit = new Menu("Exit");
		popup.add(menuExit);
		ActionListener menuExitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		menuExit.addActionListener(menuExitListener);

		// �}�E�X
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