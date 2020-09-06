package window;

import javax.swing.*;

public class Window {
	//TODO Generate a Window and be able to draw stuff on it.
	
	public Window(int x,int y){
		JFrame frame = new JFrame("GameWindow");
		
		JButton b = new JButton("test");
		b.setBounds(40, 40, 60, 50);
		
		frame.add(b);
		
		
		frame.setSize(x, y);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	
	
	
	
}
