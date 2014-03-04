package GUI1_2;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JComboBox;

public class SettingDialog extends Dialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	// ComboBox Data
	private static final Integer[] fontSize = { 50, 100, 150 };
	private final String[] fontFamilies;
	private static Color[] color = { Color.RED, Color.BLUE, Color.BLACK,
			Color.WHITE, Color.GREEN, Color.GRAY, Color.YELLOW };

	// UI Elements
	private JComboBox fontSelector;
	private JComboBox fontSizeSelector;
	private JComboBox fontColorSelector;
	private JComboBox backGroundColorSelector;

	private Tokei tokei;

	public SettingDialog(Tokei tokei) {
		super(tokei);
		this.tokei = tokei;

		// Close
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		// Get font list
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		fontFamilies = ge.getAvailableFontFamilyNames();

		// Set up UI elements
		fontSelector = new JComboBox(fontFamilies);
		fontSizeSelector = new JComboBox(fontSize);
		fontColorSelector = new JComboBox(color);
		backGroundColorSelector = new JComboBox(color);

		// OK button
		Button okButton = new Button("OK");
		okButton.addActionListener(this);

		// Layout
		setLayout(new FlowLayout());
		setTitle("Tokei settings");
		setSize(300, 200);

		// Add UI elements
		add(fontSelector);
		add(fontSizeSelector);
		add(fontColorSelector);
		add(backGroundColorSelector);
		add(okButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tokei.fontFamily = (String) fontSelector.getSelectedItem();
		tokei.fontSize = (Integer) fontSizeSelector.getSelectedItem();
		tokei.fontColor = (Color) fontColorSelector.getSelectedItem();
		tokei.backGroundColor = (Color) backGroundColorSelector.getSelectedItem();
		setVisible(false);
	}
}
