import java.util.ArrayList;

public class testDeck {
	static Deck deck = new Deck();
	static ArrayList<Card> deckList = new ArrayList<>();
	public static void main(String[] args)
	{
		// getDeck()
		System.out.println("getDeck() is a getter for the deck arrayList created inside the deck class.");
		System.out.println("To test this function i will assign the global variable deckList to the getDeck() method.");
		deckList = deck.getDeck();
		System.out.println("To check it worked i will print properties of the list such as size.");
		System.out.println("Printing the size of the deck: " + deckList.size());
		System.out.println("Printing the first card of the deck: " + deckList.get(0).getRank() + " " + deckList.get(0).getSuit());
		System.out.println("When the deck Object is instantiated it run's AssembleDeck() which creates 14 cards 2->Ace, Clubs, Spades, Diamonds, and Hearts");
		System.out.println("to test assemble deck i will print the whole deckList.");
		for(int i = 0; i < 52; i++) System.out.println("Card " + (i+1) + ": " + deckList.get(i).getRank() + " " + deckList.get(i).getSuit());
	}
}
