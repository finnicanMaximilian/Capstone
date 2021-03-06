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
	 * For Opponent this method returns an integer that will represent the Rank of what card has a Pair,
	 * this will aid the think() method in order to help locate the cards in the hand and their posiiton so that the computer
	 * does not return said cards.
	 * 
	 */
	public int onePair()
	{
		int cardNum = 0; // new
		Card card1;
		Card tempCard;
		card1 = this.hand.get(0);
		for(int j = 1; j < 5; j++)
		{
			for(int i = 1; i < 5; i++)
			{
				// next card
				tempCard = this.hand.get(i);
				if(tempCard.getRank().equals(card1.getRank()) && !(tempCard.getSuit().equals(card1.getSuit())) && !(tempCard.equals(card1)))
				{
					cardNum = rankACard(tempCard.getRank());
					this.onePair = true;
				}
			}
			card1 = this.hand.get(j);
		}		
		return cardNum;
	}
	
	
	/*
	 * twoPair() : sets the boolean twoPair to true if there is two pairs in a players hand.
	 */
	public int[] twoPair()
	{
		int[] cardNums = {0,0};

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
					cardNums[0] = rankACard(tempCard.getRank());
					for(int y = 0; y < this.hand.size(); y++)
					{
						Card card2 = this.hand.get(y);
						for(int z = 1; z < this.hand.size(); z++)
						{
							Card nextCard = this.hand.get(z);
							if(nextCard.getRank().equals(card2.getRank()) && !(nextCard.getSuit().equals(card2.getSuit())))
							{
								cardNums[1] = rankACard(tempCard.getRank());
								this.twoPair = true;
							}
						}
					}
					setCard(card1);
					setCard(tempCard);
				}
			}
		}		
		return cardNums;
	}
	
	/*
	 * threeOfKind() : determines is a player has a three of a kind inside their hand.
	 */
	public int threeOfKind()
	{
		int cardNum = 0;
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
						cardNum = rankACard(fCard.getRank());
						this.threeOfKind = true;
					}
				}
			}
		}
		return cardNum;
	}
	
	
	/*
	 * fourOfKind() : determines if the players hand contains four of a kind.
	 */
	public int fourOfKind()
	{
		int cardNum = 0;
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
							cardNum = rankACard(oneCard.getRank());
							this.fourOfKind = true;
						}
					}
				}
			}
		}
		return cardNum;
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
			this.winPoints = 5;
		twoPair(); 
		if(this.twoPair == true)
			this.winPoints = 10;
		threeOfKind(); 
		if(this.threeOfKind == true)
			this.winPoints = 15;
		straight(); 
		if(this.straight == true)
			this.winPoints = 30;
		flush(); 
		if(this.flush == true)
			this.winPoints = 40;
		fullHouse(); 
		if(this.fullHouse == true)
			this.winPoints = 50;
		fourOfKind(); 
		if(this.fourOfKind == true)
			this.winPoints = 60;
		straightFlush(); 
		if(this.straightFlush == true)
			this.winPoints = 70;
		royalFlush(); 
		if(this.royalFlush == true)
			this.winPoints = 100;
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
		if(hand.size() < 5)
		{
			this.hand.add(card);
		}
		else
		{
			System.out.println("Hand is full..");
		}
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
	 * 
	 * 
	 * Easy:
	 * 
	 * 
	 * Medium:
	 * 
	 * 
	 * Hard: 
	 * 
	 */
	public int think(int level)
	{
		calcHand();
		int numOfCards = 0;
		if(level == 0)
		{
			System.out.println("Error AI level of Inteligence was 0.");
		}
		/*
		 * level 1: Easy Difficulty
		 * Saves the highest card but removes 2 random cards.
		 */
		else if(level == 1)
		{
			//TODO Weird bug where onePairs were showing up randomly.
//			int temp = 1;
//			int rankToLookFor = this.highCard;
//			for(int i = 4; i >= 0; i--)
//			{
//				if((rankACard(hand.get(i).getRank()) != rankToLookFor) && temp != 0)
//				{
//					giveBack(i);
//					temp--;
//				}
//			}
//			numOfCards = 1;
//			this.winPoints = 0;
			numOfCards = 0;
			this.winPoints = 0;
		}
		/*
		 * level 2: Medium Difficulty
		 * discards 3 cards randomly.
		 * Saves a Pair if exisitant.
		 */
		else if(level == 2)
		{
			if(winPoints == 0)
			{
				giveBack(0);
				giveBack(0);
				giveBack(0);
				numOfCards = 3;
			}
			else if(winPoints == 5) // onePair is present
			{
				int rankToLookFor = onePair(); // These are the ranks that you have two of. remove all cards w/o that rank.
				for(int i = 4; i >= 0; i--)
				{
					if(rankACard(hand.get(i).getRank()) != rankToLookFor)
					{
						giveBack(i);
					}
				}
				numOfCards = 3;
			}
			this.winPoints = 0;
		}
		/*
		 * level 3: Hard Difficulty
		 * this strategy will try and be as difficult as possible.
		 */
		else if(level == 3)
		{
			int temp = 0;
			int rankToLookFor = 0;
			/*
			 *  case of 0 winpoints Nothing
			 *  find highCard if Ace, discard 4, if not discard 3.
			 */
			if(winPoints == 0)
			{
				//System.out.println("Nothing");
				if(highCard == 14)
				{
					rankToLookFor = 14;
					/*
					 * iterate through whole hand.
					 */
					for(int i = 4; i >= 0; i--)
					{
						if((rankACard(hand.get(i).getRank()) != rankToLookFor))
						{
							giveBack(i);
						}
					}
					numOfCards = 4;
				}
				else
				{
					temp = 3;
					rankToLookFor = highCard;
					for(int i = 4; i >= 0; i--)
					{
						if((rankACard(hand.get(i).getRank()) != rankToLookFor) && temp != 0)
						{
							giveBack(i);
							temp--;
						}
					}
					numOfCards = 3;
				}
			}
			
			/*
			 *  case of 5 winPoints onePair
			 *  Copied level 2 handle of onePair, finds the pair keeps it and returns everything else.
			 */
			if(winPoints == 5) // onePair is present
			{
				//System.out.println("onePair");
				rankToLookFor = onePair(); // These are the ranks that you have two of. remove all cards w/o that rank.
				for(int i = 4; i >= 0; i--)
				{
					if(rankACard(hand.get(i).getRank()) != rankToLookFor)
					{
						giveBack(i);
					}
				}
				numOfCards = 3;
	
			}
			/*
			 *  case of 10 winpoints twoPair
			 *  for twoPair I need to find out if that last card in the opponents hand is its highCard.
			 */
			if(winPoints == 10)
			{
				//System.out.println("twoPair");
				int[] cardNums = twoPair();
				rankToLookFor = cardNums[0];
				int alsoRankToLookFor = cardNums[1];
				/*
				 * This case checks if our highCard is the card remaining, thus leaving the computer with nothing left to do.
				 */
				if(highCard != rankToLookFor && highCard != alsoRankToLookFor)
				{
					numOfCards = 0;
				}
				else
				{
					for(int i = 4; i >= 0; i--)
					{
						if((rankACard(hand.get(i).getRank()) != rankToLookFor) 
								&& (rankACard(hand.get(i).getRank()) != alsoRankToLookFor))
						{
							giveBack(i);
						}
					}
				}
				numOfCards = 1;
		
			}
			/*
			 *  case of 15 winpoints threeOfKind
			 *  In this case i need to check the remaining two cards, see if either of them are the opponents hardCard
			 *  if not, return those two cards or just one. never will i return zero, or it would be a fullHouse.
			 */
			if(winPoints == 15)
			{
				//System.out.println("threePair");
				rankToLookFor = threeOfKind();
				if(highCard == rankToLookFor)
				{
					// if the threeOfKind is highest card return other two cards.
					for(int i = 4; i >= 0; i--)
					{
						if(rankACard(hand.get(i).getRank()) != rankToLookFor)
						{
							giveBack(i);
						}
					}
					numOfCards = 2;
				}
				else
				{
					// check if the card you are removing is A) within the threeOfKind B) the highCard
					for(int i = 4; i >= 0; i--)
					{
						if((rankACard(hand.get(i).getRank()) != rankToLookFor)
							&& (rankACard(hand.get(i).getRank()) != highCard))
						{
							giveBack(i);
						}
					}
					// numOfCards will always equal one because if the highCard is not the pair itself its one of these two.
					numOfCards = 1;
				}

			}
			// case of 60 winpoints fourOfKind
			if(winPoints == 60)
			{
				//System.out.println("fourPair");
				// check if highCard isnt the pair if it isnt dont do anything if it is, remove the other card.
				rankToLookFor = fourOfKind();
				if(highCard == rankToLookFor)
				{
					for(int i = 4; i >= 0; i--)
					{
						if(rankACard(hand.get(i).getRank()) != rankToLookFor)
						{
							giveBack(i);
						}
					}
					numOfCards = 1;
				}
				else
				{
					numOfCards = 0;
				}
			}
			this.winPoints = 0; // reset win points so that it doesn't double calculated from Poker.java "AnnounceWinner"
		}

		return numOfCards;
	}
}
