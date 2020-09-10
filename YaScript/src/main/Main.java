package main;



import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import game.Game;
import math.Matrix;
import window.RenderEngine;
import window.Window;
/**
 * @author Jan
 *
 */
public class Main {


	
	public static void main(String[] args) {
		actualMain();
		
		
		// DEBUG.  DO NOT UNCOMMENT IF YOU ARE NOT JAN OR KNOW WHAT YOU ARE DOING. YES THE IMPLICATION IS THAT JAN DOES NOT KNOW WHAT HE IS DOING
//		for(int i=0;i<=30;i++) {
//			for(int j=0;j<=30;j++) {
//				System.out.println(rend.debug(i, j).toString());
//				System.out.println(Integer.toString(i)+" "+Integer.toString(j)+" lol \n");
//			}
//		}
		
//		double[] h={1.0, 1.0, 1.0, 3.0, 5.3};
//		Matrix m = new Matrix(1, h);
//		System.out.println(m.toVector().toString());
	}	
	private static void actualMain() {
		
		Window win = new Window(1000,700);
		
		
		final Runnable loop = new Runnable() {
			public void run() {Game.loop();win.rep();}
		};
		
		Game.setup();
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(loop, 17, 17, TimeUnit.MILLISECONDS);
		
		
		

		
	}

}
