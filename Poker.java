import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Poker 
{
	static Deck deck = new Deck();
    static ArrayList<Card> theDeck = deck.getDeck();
    static Player player = new Player();
    // Oponent oponent = new Oponent();
    // Shuffle only works within Static methods.
    
	public static void main(String[] args)
	{
		Collections.shuffle(theDeck);
		dealCards();
		printHand();
		// give back stage
		initiateGiveBack(player);
		printHand();
		
		announceWinner();

		return;
	}
	
	
	/*
	 * utility to print the hand of the player.
	 */
	public static void printHand()
	{
		for(int i = 0; i < 5; i++)
		{
			System.out.println(player.hand.get(i).getRank() + " " + player.hand.get(i).getSuit());
		}
		return;
	}
	
	/*
	 * dealCards(): for creation sake only make this accomadate two players.
	 */
	public static void dealCards()
	{
		for(int i = 0; i < 5; i++)
		{
			player.setCard(theDeck.get(i));
			//this.oponent.setCard(this.theDeck.get(0));
		}
		for(int i = 0; i < 5; i++)
		{
			theDeck.remove(0);
		}
	}
	
	
	/*
	 * inititateGiveBack: This method would initiate the Giving back phase, 
	 * instructing the player on what to do, in return giving back the cards
	 * to the player and removing them from the deck.
	 */
	public static void initiateGiveBack(Player player)
	{
		System.out.println("How many cards would you like to give back?");
		Scanner keyboard = new Scanner(System.in);
		int numOfCards = keyboard.nextInt();
		if(numOfCards == 4 && ((player.searchHand("Ace", "Clubs") != 5)
				|| (player.searchHand("Ace", "Hearts") != 5)
				|| (player.searchHand("Ace", "Spades") != 5)
				|| (player.searchHand("Ace", "Diamonds") != 5)))
		{
			// Ace is present within the Persons hand
			System.out.println("Please type the Rank and Suit of the card you would like to give back\n"
					+ "			Then press ENTER after each card.");
			for(int i = 0; i < 4; i++)
			{
				String rank = keyboard.next();
				String suit = keyboard.next();
				int cNum = player.searchHand(rank, suit);
				//System.out.println("\n\n");
				player.giveBack(cNum);
			}
			System.out.println("\n\nGiving Back Cards....");
			for(int i = 0; i < numOfCards; i++)
			{
				player.setCard(theDeck.get(i));
			}
			for(int i = 0; i < numOfCards; i++)
			{
				theDeck.remove(0);
			}
		}
		else if(numOfCards < 4 && numOfCards != 0)
		{
			System.out.println("Please type the Rank and Suit of the card you would like to give back\n"
					+ "			Then press ENTER after each card.");
			for(int i = 0; i < numOfCards; i++)
			{
				String rank = keyboard.next();
				String suit = keyboard.next();
				int cNum = player.searchHand(rank, suit);
				//System.out.println("\n\n");
				player.giveBack(cNum);
			}
			System.out.println("\n\nGiving Back Cards....");
			for(int i = 0; i < numOfCards; i++)
			{
				player.setCard(theDeck.get(i));
			}
			for(int i = 0; i < numOfCards; i++)
			{
				theDeck.remove(0);
			}
		}
		else if(numOfCards == 0)
		{
			System.out.println("You must be feeling lucky!");
		}
		else
		{
			System.out.println("I'm Sorry but if you do not possess an Ace then you may not"
					+ "give back that many cards please type the number again");
			//initiateGiveBack(player);
		}
		return;
	}
	
	public static void announceWinner()
	{
		player.calcHand();
		System.out.println("player got : " + player.winPoints + " amount of winPoints!");
	}
}
