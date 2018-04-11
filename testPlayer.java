
import java.util.Arrays;


/*
 * TestPlayer Tests every method made from the Player.java class.
 */
public class testPlayer {
	static Player testPlayer;
	public static void main(String[] args)
	{
		// Testing setCard
		testPlayer = new Player();
		System.out.println("setCard(Card card) puts a card into a players hand.");
		testPlayer.setCard(new Card("Clubs", "2"));
		System.out.println("to test the function i placed a 2 Of Clubs in the testPlayers hand, card in hand: " 
		+ testPlayer.hand.get(0).getRank() + " " + testPlayer.hand.get(0).getSuit());
		testPlayer.setCard(new Card("Clubs", "3"));
		testPlayer.setCard(new Card("Clubs", "4"));
		testPlayer.setCard(new Card("Clubs", "5"));
		testPlayer.setCard(new Card("Clubs", "6"));
		System.out.println("setCard Should restrict the amount of cards given to the player and limit the hand to 5 Cards.");
		printPHand();
		System.out.println("Attemping to add another card to the players hand.");
		testPlayer.setCard(new Card("Clubs", "7"));
		
		
		// Testing searchHand
		System.out.println("searchHand(String rank, String suit) finds the index of a particular card in a players hand then returns that index.");
		System.out.println("To test this function i will place a 2 Of Clubs in the players hand then run searchHand(2, clubs) the function should return 0. ");
		testPlayer = new Player();
		testPlayer.setCard(new Card("Clubs", "2"));
		System.out.println("Test: " + testPlayer.searchHand("2", "Clubs"));
		
		// Testing giveBack
		System.out.println("giveBack(int index) is basically the arrayList's remove function but tweaked so that the function only accepts indicies 0 - 4");
		System.out.println("To test this function i will place 5 cards inside the hand Clubs, 2-6; print the hand; then giveBack all the cards; then print the hand again");
		testPlayer = new Player();
		System.out.println();
		for(int i = 0; i < 5; i++) testPlayer.setCard(new Card("Clubs", Integer.toString(i+2)));
		printPHand();
		System.out.println("Giving back all cards..");
		for(int i = 0; i < 5; i++) testPlayer.giveBack(0);
		printPHand();
		System.out.println("End of the giveBack testing...");
		
		// Testing Rank A Card
		testPlayer = new Player();
		System.out.println("Testing Rank a Card..");
		
		System.out.println("Testing the String 2: " + testPlayer.rankACard("2"));
		System.out.println("Testing the String 3: " + testPlayer.rankACard("3"));
		System.out.println("Testing the String 4: " + testPlayer.rankACard("4"));
		System.out.println("Testing the String 5: " + testPlayer.rankACard("5"));
		System.out.println("Testing the String 6: " + testPlayer.rankACard("6"));
		System.out.println("Testing the String 7: " + testPlayer.rankACard("7"));
		System.out.println("Testing the String 8: " + testPlayer.rankACard("8"));
		System.out.println("Testing the String 9: " + testPlayer.rankACard("9"));
		System.out.println("Testing the String 10: " + testPlayer.rankACard("10"));
		System.out.println("Testing the String Jack: " + testPlayer.rankACard("Jack"));
		System.out.println("Testing the String Queen: " + testPlayer.rankACard("Queen"));
		System.out.println("Testing the String King: " + testPlayer.rankACard("King"));
		System.out.println("Testing the String Ace: " + testPlayer.rankACard("Ace"));
		
		
		// Testing high Card, this function checks the players have then calls rank a card to store their "highcard"
		// in the player int global variable.
		testPlayer.setCard(new Card("Clubs", "Ace"));
		testPlayer.setCard(new Card("Diamonds", "10"));
		testPlayer.setCard(new Card("Spades", "2"));
		testPlayer.setCard(new Card("Clubs", "2"));
		testPlayer.setCard(new Card("Clubs","4"));
		testPlayer.highCard();
		System.out.println("The high card of this hand should be the Ace!: " + testPlayer.highCard);
		
		
		// Testing Calculate Hand
		testPlayer.calcHand();
		System.out.println("Activating CalcHand() The amount of winpoints generated should be 5 indicating a onepair: " + testPlayer.winPoints);
		
		
		// Testing One Pair
		testPlayer = new Player();
		testPlayer.setCard(new Card("Clubs", "10"));
		testPlayer.setCard(new Card("Hearts", "10"));
		testPlayer.setCard(new Card("Spades", "7"));
		testPlayer.setCard(new Card("Diamonds", "9"));
		testPlayer.setCard(new Card("Clubs", "Ace"));
		testPlayer.onePair();
		System.out.println("testPlayer has a onePair? " + testPlayer.onePair);
		
		
		// Testing Two Pair
		testPlayer = new Player();
		testPlayer.setCard(new Card("Clubs", "10"));
		testPlayer.setCard(new Card("Hearts", "10"));
		testPlayer.setCard(new Card("Spades", "7"));
		testPlayer.setCard(new Card("Diamonds", "7"));
		testPlayer.setCard(new Card("Clubs", "Ace"));
		testPlayer.twoPair();
		System.out.println("testPlayer has a twoPair? " + testPlayer.twoPair);
		
		// Testing Three of a kind
		testPlayer = new Player();
		testPlayer.setCard(new Card("Clubs", "10"));
		testPlayer.setCard(new Card("Hearts", "10"));
		testPlayer.setCard(new Card("Spades", "10"));
		testPlayer.setCard(new Card("Diamonds", "7"));
		testPlayer.setCard(new Card("Clubs", "Ace"));
		testPlayer.threeOfKind();
		System.out.println("testPLauer has a threeOfKind? " + testPlayer.threeOfKind);
		
		// Testing Full House
		testPlayer = new Player();
		testPlayer.setCard(new Card("Clubs", "10"));
		testPlayer.setCard(new Card("Hearts", "10"));
		testPlayer.setCard(new Card("Spades", "10"));
		testPlayer.setCard(new Card("Diamonds", "7"));
		testPlayer.setCard(new Card("Clubs", "7"));
		testPlayer.fullHouse();
		System.out.println("testPlayer has a fullHouse? " + testPlayer.fullHouse);
		
		
		// Testing Straight
		testPlayer = new Player();
		testPlayer.setCard(new Card("Clubs", "2"));
		testPlayer.setCard(new Card("Hearts", "3"));
		testPlayer.setCard(new Card("Spades", "4"));
		testPlayer.setCard(new Card("Diamonds", "5"));
		testPlayer.setCard(new Card("Clubs", "6"));
		testPlayer.straight();
		System.out.println("testPlayer has a straight? " + testPlayer.straight);
		
		
		// Testing Four of a kind
		testPlayer = new Player();
		testPlayer.setCard(new Card("Clubs", "2"));
		testPlayer.setCard(new Card("Hearts", "2"));
		testPlayer.setCard(new Card("Spades", "2"));
		testPlayer.setCard(new Card("Diamonds", "2"));
		testPlayer.setCard(new Card("Clubs", "6"));
		testPlayer.fourOfKind();
		System.out.println("TestPlayer has a fourOfKind? " + testPlayer.fourOfKind);
		
		
		// Testing Straight Flush
		testPlayer = new Player();
		testPlayer.setCard(new Card("Clubs", "2"));
		testPlayer.setCard(new Card("Clubs", "3"));
		testPlayer.setCard(new Card("Clubs", "4"));
		testPlayer.setCard(new Card("Clubs", "5"));
		testPlayer.setCard(new Card("Clubs", "6"));
		testPlayer.straightFlush();
		System.out.println("testPlayer has a straightFlush? " + testPlayer.straightFlush);
		
		// Testing Flush
		testPlayer = new Player();
		testPlayer.setCard(new Card("Clubs", "10"));
		testPlayer.setCard(new Card("Clubs", "4"));
		testPlayer.setCard(new Card("Clubs", "Queen"));
		testPlayer.setCard(new Card("Clubs", "5"));
		testPlayer.setCard(new Card("Clubs", "Ace"));
		testPlayer.flush();
		System.out.println("testPlayer has a flush? " + testPlayer.flush);

		
		// Testing royalFlush
		testPlayer = new Player();
		testPlayer.setCard(new Card("Clubs", "10"));
		testPlayer.setCard(new Card("Clubs", "Jack"));
		testPlayer.setCard(new Card("Clubs", "Queen"));
		testPlayer.setCard(new Card("Clubs", "King"));
		testPlayer.setCard(new Card("Clubs", "Ace"));
		testPlayer.royalFlush();
		System.out.println("testPlayer has a royalFlush? " + testPlayer.royalFlush);
	}
	
	public static void printPHand()
	{
		System.out.println("Printing the hand of the testPlayer... \n");
		for(int i = 0; i < testPlayer.hand.size(); i++)
		{
			System.out.println(testPlayer.hand.get(i).getRank() + " " + testPlayer.hand.get(i).getSuit());
		}
		System.out.println(); // added to help readability
		return;
	}
}
