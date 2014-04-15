package GUI2_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SettingDialog extends JDialog {

    // Basic Data

    private final DigitalClock digitalClock;
    private final DigitalClockSetting currentSetting;
    private final DigitalClockSetting newSetting;

    // ComboBox Data

    private static final Color[] COLOR_LIST = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE};

    // GUI Component

    private final GridBagLayout gbl = new GridBagLayout();
    private final JPanel mainPanel = new JPanel();

    private final JLabel fontFamilyLabel = new JLabel("Font family");
    private final JComboBox fontFamilySelector;

    private final JLabel fontSizeLabel = new JLabel("Font size");
    private final JSpinner fontSizeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

    private final JLabel fontColorLabel = new JLabel("Font color");
    private final JComboBox fontColorSelector = new JComboBox(COLOR_LIST);

    private final JLabel backGroundColorLabel = new JLabel("Background color");
    private final JComboBox backGroundColorSelector = new JComboBox(COLOR_LIST);

    private final JPanel buttonPanel = new JPanel();
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton okButton = new JButton("OK");

    public SettingDialog(DigitalClock digitalClock) {
        super(digitalClock);
        setModal(true);
        this.digitalClock = digitalClock;
        this.currentSetting = digitalClock.getDigitalClockSetting();
        newSetting = currentSetting.clone();

        // Set JComboBox values and renderer
        fontFamilySelector = new JComboBox(currentSetting.getAvailableFontFamilies());
        fontColorSelector.setRenderer(new ColorListCellRenderer());
        backGroundColorSelector.setRenderer(new ColorListCellRenderer());

        // Set default selection
        fontFamilySelector.setSelectedItem(newSetting.getFontFamily());
        fontSizeSpinner.setValue(newSetting.getFontSize());
        fontColorSelector.setSelectedItem(newSetting.getFontColor());
        backGroundColorSelector.setSelectedItem(newSetting.getBackGroundColor());

        // Setup listener and layout
        setupListener();
        setupComponent();
    }

    private void setupListener() {
        // Close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });

        // Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingDialog.this.digitalClock.onSettingDialogClose(currentSetting.clone());
                dispose();
            }
        });

        // OK button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newSetting.setFontFamily((String) fontFamilySelector.getSelectedItem());
                newSetting.setFontSize(Integer.parseInt(fontSizeSpinner.getValue().toString()));
                newSetting.setFontColor((Color) fontColorSelector.getSelectedItem());
                newSetting.setBackGroundColor((Color) backGroundColorSelector.getSelectedItem());
                SettingDialog.this.digitalClock.onSettingDialogClose(newSetting.clone());
                dispose();
            }
        });
    }

    private void setupComponent() {

        setTitle("DigitalClock settings");
        setResizable(false);

        // Whole layout
        setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.CENTER, mainPanel);
        getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        // Main panel layout
        mainPanel.setLayout(gbl);
        addComponent(mainPanel, gbl, fontFamilyLabel, 0, 0);
        addComponent(mainPanel, gbl, fontFamilySelector, 1, 0);
        addComponent(mainPanel, gbl, fontSizeLabel, 0, 1);
        addComponent(mainPanel, gbl, fontSizeSpinner, 1, 1);
        addComponent(mainPanel, gbl, fontColorLabel, 0, 2);
        addComponent(mainPanel, gbl, fontColorSelector, 1, 2);
        addComponent(mainPanel, gbl, backGroundColorLabel, 0, 3);
        addComponent(mainPanel, gbl, backGroundColorSelector, 1, 3);

        // Button panel layout
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(cancelButton);
        buttonPanel.add(okButton);

        pack();
    }

    private void addComponent(JPanel panel, GridBagLayout gbl, Component component, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();

        if (x == 0) {
            gbc.anchor = GridBagConstraints.EAST;
        } else if (x == 1) {
            gbc.anchor = GridBagConstraints.WEST;
        } else {
            new AssertionError("Invalid component position is set.");
        }

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(component, gbc);
        panel.add(component);
    }
}
