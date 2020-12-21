package cardgame;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import gamemodes.BlackJack;
import gamemodes.GameMode;
import gamemodes.ThreeOfAKind;

public class CardGame{

	public static final int PLAYERS_MIN = 1;
	public static final int PLAYERS_MAX = 3;

	public static final String BLACKJACK = "Black Jack";

	private static boolean gameChosen = false;
	

	public static void main(String[] args) {

		//TODO: determine if CardGame should be the NativeKeyListener, or should GameMode
		//TODO: make another gamemode, and 
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		

		Logger logger = Logger.getLogger(GlobalScreen.class.getPackageName());
		logger.setLevel(Level.OFF);
		logger.setUseParentHandlers(false);



		System.out.println("This is my card game!");
		Scanner input = new Scanner(System.in);
		GameMode game = null;
		//TODO:make all the names enum, so I don't have to hardcode them
		while(!gameChosen) {
			System.out.print("What GameMode would you like to choose?: ");
			String choice = input.nextLine();
			switch(choice) {
			case "BlackJack":
				System.out.println("We are gonna play: " +BLACKJACK);
				gameChosen = true;
				game = new BlackJack();
				break;
			case "ThreeOfAKind":
				System.out.println("We are gonna play: ThreeOfAKind");
				gameChosen = true;
				game = new ThreeOfAKind();
				break;
			default:
				System.out.println("Please select an Appropraiate choice");
				break;
			}
		}
		
		input.close();
		GlobalScreen.addNativeKeyListener(game);
		
		game.play();

	}
	

	
}
