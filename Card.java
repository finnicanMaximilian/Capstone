// This class handes the card functions.
public class Card
{
  String suit;
  String rank;

  public Card(String suit, String rank)
  {
    this.suit = suit;
    this.rank = rank;
  }

  public String getSuit()
  {
    return this.suit;
  }

  public void setSuit(String suit)
  {
    this.suit = suit;
  }

  public String getRank()
  {
    return this.rank;
  }

  /* Sets the rank of a card */
  public void setRank(String rank)
  {
    this.rank = rank;
  }

}
