package window;

import javax.swing.*;

import game.WorldManager;

public class Window extends JFrame{
	//TODO Generate a Window and be able to draw stuff on it.
	JPanel panel;
	
	public Window(int x,int y){		
		JButton b = new JButton("test");
		b.setBounds(40, 40, 60, 50);
		
		
		
		setSize(x, y);
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void putVision(WorldManager wm) {
		panel = new RenderEngine(getWidth(),getHeight(),wm);
		this.add(panel);
		panel.setVisible(true);
	}
	
	public void rep() {
		panel.repaint();
	}
	
	
	
}
