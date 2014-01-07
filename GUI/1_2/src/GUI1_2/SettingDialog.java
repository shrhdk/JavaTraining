package GUI1_2;

import java.awt.*;
import java.awt.event.*;

public class SettingDialog extends Dialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private Tokei tokei;
	
	public SettingDialog(Tokei tokei) {
		super(tokei);
		this.tokei = tokei;
		
		// Layout
        setLayout(new FlowLayout());
        setTitle("Tokei settings");
        setSize(80, 80);
        
        // OK button
        Button okButton = new Button("OK");
        okButton.addActionListener(this);
        add(okButton);
        
        // Font selector
        
        // Font size selector
        
        // Font color selector
        
        // Background color selector
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
