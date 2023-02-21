//Liam Tyler
public class Card implements Comparable<Card> {
   public enum Suit {SPADES, HEARTS, DIAMONDS, CLUBS}
   
   public final static int ACE = 1;      // Codes for the non-numeric cards.
   public final static int JACK = 11;    // Cards 2 through 10 have their 
   public final static int QUEEN = 12;   // numerical values for their codes.
   public final static int KING = 13;

   private final Suit suit;
   private final int value;

   
   public Card(int theValue, Suit theSuit) {
      value = theValue;
      suit = theSuit;
   }
   
   @Override
   public boolean equals(Object o) 
   {
      //System.out.printf("Call to Equal\n");
      boolean match = false;
      if ( (value == ((Card)o).value) && (suit == ((Card)o).suit) ) {
         match = true;
      }
      return match;
   }

   /**
    * Returns the suit of this card.
    * 
    * @returns the suit, which is one of the constants Card.SPADES, 
    * Card.HEARTS, Card.DIAMONDS, Card.CLUBS, or Card.JOKER
    */
   public Suit GetSuit() {
      return suit;
   }
   
   /**
    * Returns the value of this card.
    * 
    * @return the value, which is one the numbers 1 through 13, inclusive for
    * a regular card, and which can be any value for a Joker.
    */
   public int GetValue() {
      return value;
   }
   
   /**
    * Returns a String representation of the card's suit.
    * 
    * @return one of the strings "Spades", "Hearts", "Diamonds", or "Clubs"
    */
   public String GetSuitAsString() {
      switch (suit) {
        case SPADES:   
           return "Spades";
        case HEARTS:   
           return "Hearts";
         case DIAMONDS: 
           return "Diamonds";
         default:    
           return "Clubs";
      }
   }
   
   /**
    * Returns a String representation of the card's value.
    * 
    * @return for a regular card, one of the strings "Ace", "2",
    * "3", ..., "10", "Jack", "Queen", or "King".  
    */
   public String GetValueAsString() 
   {
         switch (value) {
          case 1:   return "Ace";
          case 2:   return "2";
          case 3:   return "3";
          case 4:   return "4";
          case 5:   return "5";
          case 6:   return "6";
          case 7:   return "7";
          case 8:   return "8";
          case 9:   return "9";
          case 10:  return "10";
          case 11:  return "Jack";
          case 12:  return "Queen";
          default:  return "King";
      }
   }
   
   /**
    * Returns a string representation of this card, including both
    * its suit and its value  Sample return values
    * are: "Queen of Hearts", "10 of Diamonds", "Ace of Spades"
    */
   @Override
   public String toString() 
   {
      return GetValueAsString() + " of " + GetSuitAsString();
   }
   
   @Override
   public int compareTo(Card card)
   {
       return this.value - card.value;

   }
}