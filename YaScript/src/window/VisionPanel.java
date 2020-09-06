package window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class VisionPanel extends JPanel {
	
	public VisionPanel(int x, int y) {
		this.setBounds(0, 0, WIDTH, HEIGHT);
	}
	
	
	@Override
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		
		Graphics2D g = (Graphics2D) g1;
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
	}
}
