import java.util.ArrayList;
public class Deck
{
  public ArrayList<Card> deck = new ArrayList<Card>(52);;
  public Deck()
  {
    assembleDeck();
  }

  public ArrayList<Card> getDeck()
  {
    return this.deck;
  }

  public void assembleDeck()
  {
    int i;
    // Creates Heart Set
    for(i = 1; i < 12; i++)
    {
      if(i == 1)
      {
        this.deck.add(new Card("Hearts", "Ace"));
      }
      else if(i > 10)
      {
        this.deck.add(new Card("Hearts", "Jack"));
        this.deck.add(new Card("Hearts", "Queen"));
        this.deck.add(new Card("Hearts", "King"));
      }
      else
      {
        String numAsString = String.valueOf((i));
        this.deck.add(new Card("Hearts", numAsString));
      }
    }
    // Creates Club Set
    for(i = 1; i < 12; i++)
    {
      if(i == 1)
      {
        this.deck.add(new Card("Clubs", "Ace"));
      }
      else if(i > 10)
      {
        this.deck.add(new Card("Clubs", "Jack"));
        this.deck.add(new Card("Clubs", "Queen"));
        this.deck.add(new Card("Clubs", "King"));
      }
      else
      {
        String numAsString = String.valueOf((i));
        this.deck.add(new Card("Clubs", numAsString));
      }
    }

    // Creates Diamonds Set
    for(i = 1; i < 12; i++)
    {
      if(i == 1)
      {
        this.deck.add(new Card("Diamonds", "Ace"));
      }
      else if(i > 10)
      {
        this.deck.add(new Card("Diamonds", "Jack"));
        this.deck.add(new Card("Diamonds", "Queen"));
        this.deck.add(new Card("Diamonds", "King"));
      }
      else
      {
        String numAsString = String.valueOf((i));
        this.deck.add(new Card("Diamonds", numAsString));
      }
    }

    // Creates Spades Set
    for(i = 1; i < 12; i++)
    {
      if(i == 1)
      {
        this.deck.add(new Card("Spades", "Ace"));
      }
      else if(i > 10)
      {
        this.deck.add(new Card("Spades", "Jack"));
        this.deck.add(new Card("Spades", "Queen"));
        this.deck.add(new Card("Spades", "King"));
      }
      else
      {
        String numAsString = String.valueOf((i));
        this.deck.add(new Card("Spades", numAsString));
      }
    }
  }

}
