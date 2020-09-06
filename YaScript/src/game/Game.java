package game;

public class Game {

	
	
	public static void setup() {
		
	}
	
	static int counter = 0;
	public static void loop() {
		counter += 1;
		if (counter%60==0) System.out.println("second!");
		
	}
}
