package gamemodes;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.jnativehook.keyboard.NativeKeyListener;

import cardgame.CardGame;
import cardgame.Deck;
import cardgame.Hand;

public abstract class GameMode implements NativeKeyListener {
	
	protected volatile boolean waitForInput = false;
	
	protected Deck deck;
	protected List<Hand> players;
	protected int numPlayers;
	
	
	public abstract void play();
	public abstract int getValue(Hand hand);

	protected String talkToPlayer(int num, String msg) {
		return "Player "+num+":\t" +msg;
	}
	
	public GameMode() {
		getPlayers();
	}
	
	private void getPlayers() {
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.print("How many players? ("+CardGame.PLAYERS_MIN +"-" +CardGame.PLAYERS_MAX +"):\t");
			try {
				numPlayers = input.nextInt();
				//acceptable range
				if(numPlayers >= CardGame.PLAYERS_MIN && numPlayers <= CardGame.PLAYERS_MAX) {
					break;
				} 
				System.out.println("Please only select between 1 and 3 players");

			} catch (InputMismatchException e) {
				System.out.println("Please input a valid number!");
				input.nextLine();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		input.close();
		
		System.out.println("You've selected " +numPlayers +" Players!");
		players = new ArrayList<>();
		for(int i = 0; i < numPlayers; i++) {
			players.add(new Hand());
		}
		
	}

	protected void dealAllPlayers(int numCards) {
		for(int i = 0; i < numCards; i++) {
			for(Hand player : players) {
				player.addCard(deck.Deal());
			}
		}
	}
}
