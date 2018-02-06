import java.util.ArrayList;
import java.util.Arrays;
public class Opponent
{

	int highCard; // completed
	boolean twoPair; // completed
	boolean onePair; // completed
	boolean threeOfKind; // completed
	boolean fullHouse; // completed
	boolean flush; // completed
	boolean straight; // completed
	boolean royalFlush; // completed
	boolean straightFlush; // completed
	boolean fourOfKind;
	ArrayList<Card> hand;
	int winPoints;
	
	
	/*
	 * rankACard(String rank) : This Method aids the "highCard()" in assigned rank values.
	 * rank : A (String) parameter that is the string of the rank of the card you are calculating.
	 * return the rank's integer value.
	 * [Completed]
	 */
	public int rankACard(String rank)
	{
		int rankNum;
		
		if(rank.equals("Ace"))
		{
			rankNum = 14;
		}
		else if(rank.equals("King"))
		{
			rankNum = 13;
		}
		else if(rank.equals("Queen"))
		{
			rankNum = 12;
		}
		else if(rank.equals("Jack"))
		{
			rankNum = 11;
		}
		else
		{
			// if rank is not Ace, Jack, Queen, or King Parse Int
			rankNum = Integer.parseInt(rank);
		}
		
		return rankNum;
	}
	
	/*
	 * HighCard() : This Method sets the "highCard" (int) field
	 * make a tempCard to save the first cards rank Number using rankACard.
	 * loop through the rest of the hand and look at the "nextCard"
	 * if nextCard is greater than tempCard then tempCard = nextCard and continue the loop.
	 * at the end of the method set this.highCard to tempCard.
	 */
	public void highCard()
	{
		int tempCard;
		tempCard = rankACard(this.hand.get(0).getRank());
		for(int i = 1; i < 5; i++)
		{
			int nextCard = rankACard(this.hand.get(i).getRank());
			if(nextCard > tempCard)
				tempCard = nextCard;
		}
		this.highCard = tempCard;
		return;
	}
	
	/*
	 * onePair() : sets the boolean onePair to true if there is a pair inside a players hand.
	 * 
	 */
	public void onePair()
	{
		Card card1;
		Card tempCard;
		card1 = this.hand.get(0);
		for(int j = 1; j < 5; j++)
		{
			
			for(int i = 1; i < 5; i++)
			{
				// next card
				tempCard = this.hand.get(i);
				if(tempCard.getRank().equals(card1.getRank()) && !(tempCard.getSuit().equals(card1.getSuit())))
				{
					this.onePair = true;
				}
			}
			
			card1 = this.hand.get(j);
		}		
		return;
	}
	
	
	/*
	 * twoPair() : sets the boolean twoPair to true if there is two pairs in a players hand.
	 */
	public void twoPair()
	{
		
		Card card1;
		Card tempCard;
		for(int j = 0; j < this.hand.size(); j++)
		{
			card1 = this.hand.get(j);
			for(int i = 1; i < this.hand.size(); i++)
			{
				// next card
				tempCard = this.hand.get(i);
				if(tempCard.getRank().equals(card1.getRank()) && !(tempCard.getSuit().equals(card1.getSuit())))
				{
					// add in the next search of a pair.
					this.hand.remove(searchHand(card1.getRank(), card1.getSuit()));
					this.hand.remove(searchHand(tempCard.getRank(), tempCard.getSuit()));
					for(int y = 0; y < this.hand.size(); y++)
					{
						Card card2 = this.hand.get(y);
						for(int z = 1; z < this.hand.size(); z++)
						{
							Card nextCard = this.hand.get(z);
							if(nextCard.getRank().equals(card2.getRank()) && !(nextCard.getSuit().equals(card2.getSuit())))
							{
								this.twoPair = true;
							}
						}
					}
					setCard(card1);
					setCard(tempCard);
				}
			}
		}		
		return;
	}
	
	/*
	 * threeOfKind() : determines is a player has a three of a kind inside their hand.
	 */
	public void threeOfKind()
	{
		for(int i = 0; i < this.hand.size(); i++)
		{
			Card fCard = this.hand.get(i);
			for(int j = 1; j < this.hand.size(); j++)
			{
				Card sCard = this.hand.get(j);
				for(int k = 2; k < this.hand.size(); k++)
				{
					Card tCard = this.hand.get(k);
					if(fCard.getRank().equals(sCard.getRank()) &&
							sCard.getRank().equals(tCard.getRank()) &&
							tCard.getRank().equals(fCard.getRank()) &&
							!(fCard.getSuit().equals(sCard.getSuit())) &&
							!(sCard.getSuit().equals(tCard.getSuit())) &&
							!(fCard.getSuit().equals(tCard.getSuit())))
					{
						this.threeOfKind = true;
					}
				}
			}
		}
		return;
	}
	
	
	/*
	 * fourOfKind() : determines if the players hand contains four of a kind.
	 */
	public void fourOfKind()
	{
		for(int i = 0; i < this.hand.size(); i++)
		{
			Card oneCard = this.hand.get(i);
			for(int j = 1; j < this.hand.size(); j++)
			{
				Card twoCard = this.hand.get(j);
				for(int k = 2; j < this.hand.size(); j++)
				{
					Card threeCard = this.hand.get(k);
					for(int l = 3; l < this.hand.size(); l++)
					{
						Card fourCard = this.hand.get(l);
						if(oneCard.getRank().equals(twoCard.getRank()) &&
							oneCard.getRank().equals(threeCard.getRank()) &&
							oneCard.getRank().equals(fourCard.getRank()) &&
							twoCard.getRank().equals(threeCard.getRank()) &&
							twoCard.getRank().equals(fourCard.getRank()) &&
							threeCard.getRank().equals(fourCard.getRank()) &&
							!(oneCard.getSuit().equals(twoCard.getSuit())) &&
							!(oneCard.getSuit().equals(threeCard.getSuit())) &&
							!(oneCard.getSuit().equals(fourCard.getSuit())) &&
							!(twoCard.getSuit().equals(threeCard.getSuit())) &&
							!(twoCard.getSuit().equals(fourCard.getSuit())) &&
							!(threeCard.getSuit().equals(fourCard.getSuit()))) 
						{
							this.fourOfKind = true;
						}
					}
				}
			}
		}
	}
	
	/*
	 * flush() : determines if a players hand contains a flush.
	 */
	public void flush()
	{
		String card1, card2, card3, card4, card5;
		card1 = this.hand.get(0).getSuit();
		card2 = this.hand.get(1).getSuit();
		card3 = this.hand.get(2).getSuit();
		card4 = this.hand.get(3).getSuit();
		card5 = this.hand.get(4).getSuit();
		
		if(card1.equals(card2) && card2.equals(card3) && card3.equals(card4) && card4.equals(card5))
		{
			this.flush = true;
		}
		
		return;
	}
	
	/*
	 * fullHouse() : determines if a players hand contains a full House()
	 */
	public void fullHouse()
	{
		for(int i = 0; i < this.hand.size(); i++)
		{
			Card fCard = this.hand.get(i);
			for(int j = 1; j < this.hand.size(); j++)
			{
				Card sCard = this.hand.get(j);
				if(fCard.getRank().equals(sCard.getRank()) &&
						!(fCard.getSuit().equals(sCard.getSuit())))
				{
					this.hand.remove(searchHand(fCard.getRank(), fCard.getSuit()));
					this.hand.remove(searchHand(fCard.getRank(), sCard.getSuit()));
					Card oneC = this.hand.get(0);
					Card twoC = this.hand.get(1);
					Card threeC = this.hand.get(2);
					if(oneC.getRank().equals(twoC.getRank()) &&
							twoC.getRank().equals(threeC.getRank()) &&
							threeC.getRank().equals(oneC.getRank()) &&
							!(oneC.getSuit().equals(twoC.getSuit())) &&
							!(twoC.getSuit().equals(threeC.getSuit())) &&
							!(oneC.getSuit().equals(threeC.getSuit())))
					{
						this.fullHouse = true;
					}
					setCard(fCard);
					setCard(sCard);
				}
			}
		}
	}
	
	/*
	 * straight() : determines if a players hand contains a straight.
	 * a straight is when the ranks are consecuative. 
	 */
	public void straight()
	{
		// gather the highest card
		highCard();
		int temp; 
		temp = highCard;
		int[] array = new int[5];
		for(int i = 0; i < this.hand.size(); i++)
		{
			array[i] = rankACard(this.hand.get(i).getRank());
		}
		Arrays.sort(array);
		if(array[3] == temp -1 &&
				array[2] == temp -2 &&
				array[1] == temp -3 &&
				array[0] == temp -4)
		{
			this.straight = true;
		}
		
	}
	
	

	
	public void straightFlush()
	{
		flush();
		if(this.flush == true)
		{
			straight();
			if(this.straight == true)
			{
				this.straightFlush = true;
			}
		}
	}
	
	public void royalFlush()
	{
		straightFlush();
		if(this.straightFlush == true)
		{
			highCard();
			if(this.highCard == 14)
			{
				this.royalFlush = true;
			}
		}
	}
	
	public void calcHand()
	{
		highCard(); 
		onePair(); 
		if(this.onePair == true)
			this.winPoints += 5;
		twoPair(); 
		if(this.twoPair == true)
			this.winPoints += 10;
		threeOfKind(); 
		if(this.threeOfKind == true)
			this.winPoints += 15;
		straight(); 
		if(this.straight == true)
			this.winPoints += 20;
		flush(); 
		if(this.flush == true)
			this.winPoints += 25;
		fullHouse(); 
		if(this.fullHouse == true)
			this.winPoints += 30;
		fourOfKind(); 
		if(this.fourOfKind == true)
			this.winPoints += 35;
		straightFlush(); 
		if(this.straightFlush == true)
			this.winPoints += 40;
		royalFlush(); 
		if(this.royalFlush == true)
			this.winPoints += 100;
		return;
	}
	
	public Opponent()
	{
		// Constructor. set win points to 0.
		// initialize the ArrayList
		this.winPoints = 0;
		this.hand = new ArrayList<Card>(5);
	}

	public Card getCard(int i)
	{
		return this.hand.get(i);
	}

	public void setCard(Card card)
	{
		//Card newCard = card;
		this.hand.add(card);
		return;
	}

	public int searchHand(String rank, String suit)
	{
		// make the initial index 5 to check for card not found.
		int index = 5;
		for(int i = 0; i < hand.size(); i++)
		{
			if(this.hand.get(i).getRank().equals(rank) && this.hand.get(i).getSuit().equals(suit))
			{
				index = i;
			}
		}
		return index;
	}
	

	public void giveBack(int indexOfCard)
	{
		if(indexOfCard > 4 || indexOfCard < 0)
		{
			System.out.println("index must be 0-4");
			return;
		}
		else{
			// Select Specific cards to give back and to have replaced.
			this.hand.remove(indexOfCard);
		}
		return;
	}
	
	
	/*
	 * Think is a method that helps the opponent decide on what to do with it's Hand.
	 * 
	 * To work, i may need to return the number of cards being returned so that the Poker.java 
	 * can know the amount of cards to give back from the deck.
	 */
	public int think(int level)
	{
		calcHand();
		int numOfCards = 0;
		if(level == 0)
		{
			if(winPoints == 0)
			{
				giveBack(0);
				giveBack(0);
				giveBack(0);
				numOfCards = 3;
			}
//			if(this.winPoints == 0) /* has nothing */
//			{
//				// For simplicity remove 3 random cards.
//				giveBack(0);
//				giveBack(0);
//				giveBack(0);
//				numOfCards = 3;
//			}
//			else if(this.winPoints == 5) /* has one pair */
//			{
//				// find which are the pair cards.
//
//			}

		}
		this.winPoints = 0; // reset win points so that it doesn't double calculated from Poker.java "AnnounceWinner"
		return numOfCards;
	}
}
