import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Poker 
{
	static Deck deck = new Deck();
    static ArrayList<Card> theDeck = deck.getDeck();
    static Player player = new Player();
    static Opponent opponent = new Opponent();
    // Shuffle only works within Static methods.
    
	public static void main(String[] args)
	{
		Collections.shuffle(theDeck);
		dealCards();
		printPHand();
		printOHand();
		// give back stage
		initiateGiveBack(0);
		initiateGiveBack(1);
		printPHand();
		printOHand();
		
		announceWinner();

		return;
	}
	
	
	/*
	 * utility to print the hand of the player.
	 */
	public static void printPHand()
	{
		System.out.println("Printing the hand of the Player... \n");
		for(int i = 0; i < 5; i++)
		{
			System.out.println(player.hand.get(i).getRank() + " " + player.hand.get(i).getSuit());
		}
		System.out.println(); // added to help readability
		return;
	}
	
	/*
	 * utility to print the hand of the Opponent.
	 */
	public static void printOHand()
	{
		System.out.println("Printing the hand of the Opponent... \n");
		for(int i = 0; i < 5; i++)
		{
			System.out.println(opponent.hand.get(i).getRank() + " " + opponent.hand.get(i).getSuit());
		}
		System.out.println(); // added to help readability
		return;
	}
	
	
	/*
	 * dealCards(): for creation sake only make this accommodate two players.
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
		for(int i = 0; i < 5; i++)
		{
			//player.setCard(theDeck.get(i));
			opponent.setCard(theDeck.get(i));
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
	 * 0 == Player
	 * 1 == Computer
	 */
	public static void initiateGiveBack(int person)
	{
		if(person == 0)
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
			}
		}
		else if(person == 1)
		{
			// opponent goes here.
			int numOfCards = opponent.think(0);
			for(int i = 0; i < numOfCards; i++)
			{
				opponent.setCard(theDeck.get(i));
			}
			for(int i = 0; i < numOfCards; i++)
			{
				theDeck.remove(0);
			}

		}
		else
		{
			System.out.println("Wrong number entered, please enter 0 for player and 1 for opponent.");
		}
		return;
	}
	
	public static void announceWinner()
	{
		player.calcHand();
		opponent.calcHand();
		System.out.println("player got : " + player.winPoints + " amount of winPoints!");
		System.out.println("computer got : " + opponent.winPoints + " amount of winPoints!");
		if(player.winPoints > opponent.winPoints)
		{
			System.out.println("Player wins!");
		}
		else if(opponent.winPoints > player.winPoints)
		{
			System.out.println("Computer wins!");
		}
		else if(opponent.winPoints == player.winPoints)
		{
			if(opponent.highCard > player.highCard)
			{
				System.out.println("Computer wins!");
			}
			else if(player.highCard > opponent.highCard)
			{
				System.out.println("Player wins!");
			}
			else
			{
				System.out.println("dafaqqq?!?!!?");
			}
		}
	}
}
