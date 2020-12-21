package cardgame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class Deck {

	private static Deck onlyDeck;
	//TODO:Change deck to stack
	public Stack<Card> cards =  new Stack<>();
	public enum Suit {
		CLUBS, HEARTS, SPADES, DIAMONDS
	}
	
	public static Deck getDeck() {
		if (onlyDeck == null) {
			onlyDeck = new Deck();	
			onlyDeck.cards.addAll(Arrays.asList(Card.values()));
		}
		return onlyDeck;
	}
	
	public void resetDeck() {
		cards = new Stack<>();
		cards.addAll(Arrays.asList(Card.values()));
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public boolean hasNextCard() {
		return !cards.isEmpty();
	}
	
	/*
	 * Using Iterator of Collection. This allows me to change cards field to 
	 * LinkedList, ArrayList, etc. without needing to change this function
	 */
	//TODO:Need to find out what Data Structure I should return;
	//What Data Structure is Hand going to use?
	//Deck.cards -> Deck.Deal -> Hand.cards
	public ArrayList<Card> Deal(int numCards) {
		ArrayList<Card> dealtCards = new ArrayList<>(numCards * 2);
		ListIterator<Card> listIter = cards.listIterator();
		for(int i = 0; i < numCards && listIter.hasNext(); i++) {
			dealtCards.add(listIter.next());
			listIter.remove();
		}
		return dealtCards;
	}
	
	public Card Deal() {
		ListIterator<Card> listIter = cards.listIterator();
		Card dealtCard = null;
		if(listIter.hasNext()) {
			dealtCard = listIter.next();
			listIter.remove();
		}
		return dealtCard;
	}
	
	
	public ArrayList<Card> ViewCards(int numCards){
		ArrayList<Card> dealtCards = new ArrayList<>(numCards * 2);
		ListIterator<Card>  listIter = cards.listIterator();
		for(int i = 0; i < numCards && listIter.hasNext(); i++) {
			dealtCards.add(listIter.next());
		}
		return dealtCards;
	}
	public ArrayList<Card> ViewCards(){
		ArrayList<Card> dealtCards = new ArrayList<>();
		ListIterator<Card>  listIter = cards.listIterator();
		while(listIter.hasNext()) {
			dealtCards.add(listIter.next());
		}
		return dealtCards;
	}
	
	
}
