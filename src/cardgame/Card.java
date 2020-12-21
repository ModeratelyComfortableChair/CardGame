package cardgame;

public enum Card {
	ACE_OF_CLUBS 			(Number.ACE, Suit.CLUBS),
	TWO_OF_CLUBS 			(Number.TWO, Suit.CLUBS),
	THREE_OF_CLUBS			(Number.THREE, Suit.CLUBS),
	FOUR_OF_CLUBS 			(Number.FOUR, Suit.CLUBS),
	FIVE_OF_CLUBS 			(Number.FIVE, Suit.CLUBS),
	SIX_OF_CLUBS			(Number.SIX, Suit.CLUBS),
	SEVEN_OF_CLUBS 			(Number.SEVEN, Suit.CLUBS),
	EIGHT_OF_CLUBS 			(Number.EIGHT, Suit.CLUBS),
	NINE_OF_CLUBS			(Number.NINE, Suit.CLUBS),
	TEN_OF_CLUBS 			(Number.TEN, Suit.CLUBS),
	JACK_OF_CLUBS 			(Number.JACK, Suit.CLUBS),
	QUEEN_OF_CLUBS			(Number.QUEEN, Suit.CLUBS),
	KING_OF_CLUBS			(Number.KING, Suit.CLUBS),
	ACE_OF_HEARTS 			(Number.ACE, Suit.HEARTS),
	TWO_OF_HEARTS 			(Number.TWO, Suit.HEARTS),
	THREE_OF_HEARTS			(Number.THREE, Suit.HEARTS),
	FOUR_OF_HEARTS 			(Number.FOUR, Suit.HEARTS),
	FIVE_OF_HEARTS 			(Number.FIVE, Suit.HEARTS),
	SIX_OF_HEARTS			(Number.SIX, Suit.HEARTS),
	SEVEN_OF_HEARTS 		(Number.SEVEN, Suit.HEARTS),
	EIGHT_OF_HEARTS 		(Number.EIGHT, Suit.HEARTS),
	NINE_OF_HEARTS			(Number.NINE, Suit.HEARTS),
	TEN_OF_HEARTS 			(Number.TEN, Suit.HEARTS),
	JACK_OF_HEARTS 			(Number.JACK, Suit.HEARTS),
	QUEEN_OF_HEARTS			(Number.QUEEN, Suit.HEARTS),
	KING_OF_HEARTS			(Number.KING, Suit.HEARTS),
	ACE_OF_SPADES 			(Number.ACE, Suit.SPADES),
	TWO_OF_SPADES 			(Number.TWO, Suit.SPADES),
	THREE_OF_SPADES			(Number.THREE, Suit.SPADES),
	FOUR_OF_SPADES 			(Number.FOUR, Suit.SPADES),
	FIVE_OF_SPADES 			(Number.FIVE, Suit.SPADES),
	SIX_OF_SPADES			(Number.SIX, Suit.SPADES),
	SEVEN_OF_SPADES 		(Number.SEVEN, Suit.SPADES),
	EIGHT_OF_SPADES 		(Number.EIGHT, Suit.SPADES),
	NINE_OF_SPADES			(Number.NINE, Suit.SPADES),
	TEN_OF_SPADES 			(Number.TEN, Suit.SPADES),
	JACK_OF_SPADES 			(Number.JACK, Suit.SPADES),
	QUEEN_OF_SPADES			(Number.QUEEN, Suit.SPADES),
	KING_OF_SPADES			(Number.KING, Suit.SPADES),
	ACE_OF_DIAMONDS 		(Number.ACE, Suit.DIAMONDS),
	TWO_OF_DIAMONDS 		(Number.TWO, Suit.DIAMONDS),
	THREE_OF_DIAMONDS		(Number.THREE, Suit.DIAMONDS),
	FOUR_OF_DIAMONDS 		(Number.FOUR, Suit.DIAMONDS),
	FIVE_OF_DIAMONDS 		(Number.FIVE, Suit.DIAMONDS),
	SIX_OF_DIAMONDS			(Number.SIX, Suit.DIAMONDS),
	SEVEN_OF_DIAMONDS 		(Number.SEVEN, Suit.DIAMONDS),
	EIGHT_OF_DIAMONDS 		(Number.EIGHT, Suit.DIAMONDS),
	NINE_OF_DIAMONDS		(Number.NINE, Suit.DIAMONDS),
	TEN_OF_DIAMONDS 		(Number.TEN, Suit.DIAMONDS),
	JACK_OF_DIAMONDS 		(Number.JACK, Suit.DIAMONDS),
	QUEEN_OF_DIAMONDS		(Number.QUEEN, Suit.DIAMONDS),
	KING_OF_DIAMONDS		(Number.KING, Suit.DIAMONDS);
	public enum Suit {
		CLUBS, HEARTS, SPADES, DIAMONDS
	}
	
	public enum Number {
		ACE		(1),
		TWO		(2),
		THREE	(3),
		FOUR	(4),
		FIVE	(5),
		SIX		(6),
		SEVEN	(7),
		EIGHT	(8),
		NINE	(9),
		TEN		(10),
		JACK	(11),
		QUEEN	(12),
		KING	(13);
		public final int value;
		Number(int value){
			this.value = value;
		}
	}
	
	public final Number number;
	public final Suit suit;
	
	Card(Number number, Suit suit) {
		this.number = number;
		this.suit = suit;
	}
}
