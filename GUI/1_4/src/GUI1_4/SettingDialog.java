package GUI1_4;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JComboBox;

public class SettingDialog extends Dialog {

	private static final long serialVersionUID = 1L;

	// ComboBox Data
	private static final Integer[] fontSize = { 50, 100, 150 };
	private final String[] fontFamilies;
	private static PresetColor[] color = PresetColor.values();

	// UI Elements
	private final GridBagLayout gbl = new GridBagLayout();
	private JComboBox fontSelector;
	private JComboBox fontSizeSelector;
	private JComboBox fontColorSelector;
	private JComboBox backGroundColorSelector;
	private Button cancelButton = new Button("Cancel");
	private Button okButton = new Button("OK");

	private final Tokei tokei;

	public SettingDialog(Tokei tokei) {
		super((Window) null);
		this.tokei = tokei;

		setResizable(false);

		// Close
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
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

		// Set default selection
		fontSelector.setSelectedItem(tokei.fontFamily);
		fontSizeSelector.setSelectedItem(tokei.fontSize);
		fontColorSelector.setSelectedItem(tokei.fontColor);
		backGroundColorSelector.setSelectedItem(tokei.backGroundColor);

		// Cancel button
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});

		// OK button
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SettingDialog.this.tokei.fontFamily = (String) fontSelector
						.getSelectedItem();
				SettingDialog.this.tokei.fontSize = (Integer) fontSizeSelector
						.getSelectedItem();
				SettingDialog.this.tokei.fontColor = (PresetColor) fontColorSelector
						.getSelectedItem();
				SettingDialog.this.tokei.backGroundColor = (PresetColor) backGroundColorSelector
						.getSelectedItem();
				setVisible(false);
				dispose();
			}
		});

		// Layout
		setLayout(gbl);
		setTitle("Tokei settings");
		setSize(600, 400);

		// Add UI elements
		addComponent(new Label("Font"), 0, 0, 1, 1);
		addComponent(fontSelector, 1, 0, 1, 1);

		addComponent(new Label("Font size"), 0, 1, 1, 1);
		addComponent(fontSizeSelector, 1, 1, 1, 1);

		addComponent(new Label("Font color"), 0, 2, 1, 1);
		addComponent(fontColorSelector, 1, 2, 1, 1);

		addComponent(new Label("Background color"), 0, 3, 1, 1);
		addComponent(backGroundColorSelector, 1, 3, 1, 1);

		// Panel contains buttons
		Panel buttons = new Panel();
		buttons.setLayout(new GridLayout(1, 3));
		buttons.add(new Panel());
		buttons.add(cancelButton);
		buttons.add(okButton);
		addComponent(buttons, 0, 4, 2, 1);
		
		// Fit to content
		pack();
	}
	
	private void addComponent(Component component, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		
		if(x == 0) {
			gbc.anchor = GridBagConstraints.EAST;
		} else if (x == 1) {
			gbc.anchor = GridBagConstraints.WEST;
		} else {
			new AssertionError("Invalid component position is set.");
		}
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(component, gbc);
		add(component);
	}
}
