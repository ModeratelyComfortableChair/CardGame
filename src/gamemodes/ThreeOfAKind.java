package gamemodes;

import java.util.Arrays;
import java.util.Stack;

import org.jnativehook.keyboard.NativeKeyEvent;

import cardgame.Card;
import cardgame.Deck;
import cardgame.Hand;

public class ThreeOfAKind extends GameMode{
	private Stack<Card> pile;
	private enum CardChoiceState {
		CHOICE,
		CARD_PULL_DECK,
		CARD_PULL_PILE,
		DROPPING_CARD,
		DROP_ONE,
		DROP_TWO,
		DROP_THREE,
		DROP_FOUR,
		FINISH_TURN
	}

	private CardChoiceState choice = CardChoiceState.FINISH_TURN;

	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeEvent) {}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
		synchronized(ThreeOfAKind.class) {
			String key = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
			if(choice == CardChoiceState.CHOICE) {
				switch(key) {
				case "1":
					choice = CardChoiceState.CARD_PULL_DECK;
					break;
				case "2":
					choice = CardChoiceState.CARD_PULL_PILE;
					break;
				case "3":
					choice = CardChoiceState.FINISH_TURN;
					break;
				}
			} else if (choice == CardChoiceState.DROPPING_CARD) {
				switch(key) {
				case "1":
					choice = CardChoiceState.DROP_ONE;
					break;
				case "2":
					choice = CardChoiceState.DROP_TWO;
					break;
				case "3":
					choice = CardChoiceState.DROP_THREE;
					break;
				case "4":
					choice = CardChoiceState.DROP_FOUR;
					break;
				}
			}
		}

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeEvent) {}

	@Override
	public void play() {
		deck = Deck.getDeck();
		deck.shuffle();

		while(true) {
			//deal
			dealAllPlayers(3);

			//I'm thinking there's no need to make a custom class
			pile = new Stack<>();
			//Error could occur if deck has no cards
			pile.add(deck.Deal());

			System.out.println("Starting a new game!\n");
			
			while(true) {
				for(int i = 0; i < numPlayers; i++){
					int currentPlayerNum = i + 1;
					Hand currentPlayer = players.get(i);
					System.out.println(talkToPlayer(currentPlayerNum, "Your cards are " +currentPlayer.toString()));
					System.out.println(talkToPlayer(currentPlayerNum, "The card on top of the pile is " +pile.peek().toString()));
					System.out.println(talkToPlayer(currentPlayerNum, "Would you like to:\n(1)\tPull a Card from the deck?\n(2)\tPull the top card of the pile?\n(3)\tSkip?"));

					choice = CardChoiceState.CHOICE;
					while(choice == CardChoiceState.CHOICE) {try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}

					if(choice == CardChoiceState.CARD_PULL_DECK || choice == CardChoiceState.CARD_PULL_PILE) {
						Card dealtCard;
						if(choice == CardChoiceState.CARD_PULL_DECK) {
							dealtCard = dealWithShuffle();
						} else {
							dealtCard = pile.pop();
						}
						currentPlayer.addCard(dealtCard);
						System.out.println(talkToPlayer(currentPlayerNum, "You have been dealt: " +dealtCard.toString()));
						System.out.println(talkToPlayer(currentPlayerNum, "Your cards are " +currentPlayer.toString()));
						System.out.println(talkToPlayer(currentPlayerNum, "Would you like to:\n"
								+"(1)\tDrop the " +currentPlayer.cards.get(0) +"\n"
								+"(2)\tDrop the " +currentPlayer.cards.get(1) +"\n"
								+"(3)\tDrop the " +currentPlayer.cards.get(2) +"\n"
								+"(4)\tDrop the " +currentPlayer.cards.get(3)
								));
						choice = CardChoiceState.DROPPING_CARD;
						while(choice == CardChoiceState.DROPPING_CARD) {try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}}
						//TODO: put card back on to the pile
						switch(choice) {
						case DROP_ONE:
							pile.add(currentPlayer.cards.remove(0));
							break;
						case DROP_TWO:
							pile.add(currentPlayer.cards.remove(1));
							break;
						case DROP_THREE:
							pile.add(currentPlayer.cards.remove(2));
							break;
						case DROP_FOUR:
							pile.add(currentPlayer.cards.remove(3));
							break;
						}
						System.out.println(talkToPlayer(currentPlayerNum, "Your cards are " +currentPlayer.toString() + "\n"));
						if(currentPlayer.cards.get(0).number == currentPlayer.cards.get(1).number
								&& currentPlayer.cards.get(1).number == currentPlayer.cards.get(2).number) {
							System.out.println(talkToPlayer(currentPlayerNum, "You have won!\n"));
							break;
						}
						choice = CardChoiceState.FINISH_TURN;
					}
				}
			}
		}

	}

	/*
	 * I could just deal the card, and then check to see if deck has next card
	 * This should technically work as long as deck always has atleast one card
	 * We shouldn't run in to a situation where deck is empty
	 */
	private Card dealWithShuffle() {
		Card card = deck.Deal();
		if(!deck.hasNextCard()) {
			Card topCard = pile.pop();
			//Not good. Requires deck.cards and pile to be same type
			deck.cards.addAll(Arrays.asList((Card[])pile.toArray()));
			pile = new Stack<>();
			pile.push(topCard);
		}

		return card;
	}


	//TODO: most card games need to give a value to the current hand.
	//Perhaps this should determine if we win (have three of a kind)?
	@Override
	public int getValue(Hand hand) {
		return 0;
	}

}
