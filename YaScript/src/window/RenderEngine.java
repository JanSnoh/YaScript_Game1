package window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.*;

import Util.Pair;
import game.WorldManager;
import game.entities.WorldEntity;
import math.Vector;
import game.WorldManager;

public class RenderEngine extends JPanel{
	
	private float screenDist;
	int sizex,sizey;
	WorldManager wm;
	Color backgroundC;
	
	//DEBUG CAMERA OBJECT
	private Camera cam = new Camera() {
		@Override
		public Vector getPos() {
			// TODO Auto-generated method stub
			return new Vector(0,0);
		}

		@Override
		public Vector getDir() {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	
	
	public RenderEngine(int x, int y, WorldManager world) {
		setSize(x,y);
		sizex=x;
		sizey=y;
		wm=world;
	}
	
	public Vector debug(int i,int j) {
		Vector pos = new Vector(i-getWidth()/2,j-getHeight()/2);
		pos.add(cam.getPos());
		
		return pos;
	}
	
	public Color getpix(int i,int j) {
		Vector pos = new Vector(i-getWidth()/2,j-getHeight()/2);
		pos.add(cam.getPos());
		Pair<WorldEntity, Double> clos = wm.closestAndDist(pos);
		if(clos.getValue() < 0) return clos.getKey().getColor();
		
		
		return backgroundC; 	
	}
	
	public Image getVision() {
		Color[] pixels;
        BufferedImage image = new BufferedImage(sizex, sizey, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) image.getData();
		for(int i=0;i<sizey;i++) for(int j=0;j<sizey;j++) image.setRGB(i, j, getpix(i,j).getRGB());
		return image;
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getVision(), 0, 0, this); // see javadoc for more info on the parameters            
    }
	
}
