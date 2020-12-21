package gamemodes;
import java.util.ArrayList;

import org.jnativehook.keyboard.NativeKeyEvent;

import cardgame.Card;
import cardgame.CardGame;
import cardgame.Deck;
import cardgame.Hand;

public class BlackJack extends GameMode{

	//TODO: change booleans for state all to enums
	private boolean hit = false;
	
	@Override
	public void play() {
		deck = Deck.getDeck();
		Hand dealer = new Hand();
		deck.shuffle();

		//round
		while(true) {
			//Deal
			dealAllPlayers(2);

			//play
			for(int i = 0; i < players.size(); i++) {
				int currentPlayerNum = i + 1;
				Hand currentPlayer = players.get(i);

				dealing:
					do {
						System.out.println(talkToPlayer(currentPlayerNum, "Your cards are " +currentPlayer.toString()));
						System.out.println(talkToPlayer(currentPlayerNum, "You currently have " + getValue(currentPlayer)));

						if(getValue(currentPlayer) > 21) {
							System.out.println(talkToPlayer(currentPlayerNum, "You are over 21! You Lose!"));
							break dealing;
						}

						System.out.print(talkToPlayer(currentPlayerNum, "Would you like another card? (y/n):\t"));
						waitForInput = true;
						while(waitForInput) {try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}}
						
						if(hit) {
							System.out.println("\n");
							Card dealtCard = deck.Deal();
							currentPlayer.addCard(dealtCard);
							System.out.println(talkToPlayer(currentPlayerNum, "You have been dealt " +dealtCard.toString()));
						}
					} while (hit);
				System.out.println("\n");

			}
			
			int winner = -1;
			int maxScore = 0;
			for(int i = 0; i < players.size(); i++) {
				Hand currentPlayer = players.get(i); 
				if(getValue(currentPlayer) > maxScore && getValue(currentPlayer) <= 21) {
					winner = i;
					maxScore = getValue(currentPlayer);
				}
			}

			while(getValue(dealer) < 21 && getValue(dealer) < maxScore) {
				Card dealtCard = deck.Deal();
				dealer.addCard(dealtCard);
				System.out.println("Dealer deals him self a " +dealtCard.toString());
			}

			System.out.println("Dealer has " + dealer.toString() +" with a value of " +getValue(dealer));

			if(winner == -1 || (getValue(dealer) <= 21 && getValue(players.get(winner)) <= getValue(dealer))){
				System.out.println("The dealer wins with: "+dealer.toString());
			} else {
				System.out.println(talkToPlayer(winner + 1, "You have won with "+maxScore));
			}


			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			players = new ArrayList<>();
			for(int i = 0; i < numPlayers; i++) {
				players.add(new Hand());
			}
			
			System.out.println("\nNew round\n");
		}
	}

	@Override
	public int getValue(Hand hand) {
		int value = 0;
		for(Card card : hand.cards) {
			value += card.number.value;
		}

		return value;
	}
	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeEvent) {}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
		synchronized(BlackJack.class) {
			if(waitForInput) {
				String key = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
				if(key.equals("Y") || key.equals("N")) {
					hit = key.equals("Y") ? true: false;
					waitForInput = false;
				}
			}
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeEvent) {}
}
