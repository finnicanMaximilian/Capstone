
public class testCard {
	static Card card = new Card("Clubs", "2");
	
	public static void main(String[] args)
	{
		// Testing getSuit()
		System.out.println("getSuit() returns the suit of the card, for testing purposes i will be using the card 2 of Clubs");
		System.out.println("Suit of card: " + card.getSuit());
		// Testing setSuit(String)
		System.out.println("setSuit(String suit) set the suit of the card. i will set the card suit with 'Spades'.");
		card.setSuit("Spades");
		System.out.println("Changing card's suit to 'Spades'... Suit: " + card.getSuit());
		// Testing getRank()
		System.out.println("getRank() will return the cards rank, which should be 2.");
		System.out.println("Printing the rank: " + card.getRank());
		
		// setRank(String)
		System.out.println("setRank(String) changes the rank of a card with given string for testing i will change the 2 of Spades to a Ace of Spades");
		card.setRank("Ace");
		System.out.println("new Card: " + card.getRank() +" " + card.getSuit());
		return;
	}
}
