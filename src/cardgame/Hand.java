package cardgame;
import java.util.ArrayList;

public class Hand {

	/*TODO
	 * What Data structure should I use for the hands. LinkedList would make it easy
	 * to remove a card without the need to avoid empty spaces. However we would have
	 * O(n) in order to access
	 * 
	 * ArrayList
	 * Access O(1)
	 * Removal O(n) - need to shift every card to the left. ArrayList.remove(index) performs this shifting.
	 * Insertion O(1)
	 * 
	 * LinkedList
	 * Access O(n)
	 * Removal O(1) - Note that we need to access the card first before removing it.
	 * Insertion O(1) - We can always add the new card to the front.
	 * 
	 */
	public ArrayList<Card> cards = null;
	private int limit = 5;
	
	public Hand() {
		cards = new ArrayList<>();
	}
	public Hand(int limit) {
		this.limit = limit;
		cards = new ArrayList<>();
	}
	public void addCard(Card card) {
		if(cards.size() < limit) {
			cards.add(card);
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(Card card : cards) {
			sb.append(card.toString());
			if(cards.indexOf(card) != cards.size()) {
				sb.append(", ");
			}
		}
		return sb.toString();
		
	}
	
	
}
