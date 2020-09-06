package window;

import javax.swing.*;

public class Window extends JFrame{
	//TODO Generate a Window and be able to draw stuff on it.
	JPanel panel;
	
	public Window(int x,int y){		
		JButton b = new JButton("test");
		b.setBounds(40, 40, 60, 50);
		
		panel = new VisionPanel(x,y);
		this.add(panel);
		
		
		setSize(x, y);
		setLayout(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	
	
}
