package GUI1_1;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Tokei extends Frame implements Runnable {

	private static final long serialVersionUID = 1L;

	public Tokei() {
		super();
		setSize(200, 100);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
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
		Calendar now = Calendar.getInstance();
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);
		g.drawString(String.format("%s:%s:%s", h, m, s), 50, 50);
	}

	public static void main(String args[]) {
		Tokei tokei = new Tokei();
		Thread thread = new Thread(tokei);
		thread.start();
	}
}