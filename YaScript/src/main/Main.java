package main;



import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import game.Game;
import window.Window;
/**
 * @author Jan
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO I dont actually know what goes in here
		
		Window win = new Window(1000,700);
		
		
		final Runnable loop = new Runnable() {
			public void run() {Game.loop();}
		};
		
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(loop, 17, 17, TimeUnit.MILLISECONDS);
		
		
		
		Game.setup();
		

	}

}
