//Liam Tyler
public class Deck {
    public static final int NCARDS = 52;
    private Card[] deck;
    private int cardsUsed;
    private final int SHUFFLENUM = 1000;
    private RandomInteger r1 = new RandomInteger(0, 51);
    private RandomInteger r2 = new RandomInteger(0, 51);
    //consrtuctor for deck class
    public Deck() {
        Card.Suit iSuit = null;
        deck = new Card[NCARDS];
        for (int suit = 0; suit < 4; suit++) {
            for (int value = 1; value < 14; value++) {
                if (suit == 0) {
                    iSuit = Card.Suit.DIAMONDS;
                } else if (suit == 1) {
                    iSuit = Card.Suit.SPADES;
                } else if (suit == 2) {
                    iSuit = Card.Suit.CLUBS;
                } else if (suit == 3) {
                    iSuit = Card.Suit.HEARTS;
                }
                deck[cardsUsed++] = new Card(value, iSuit);
            }
        }
        cardsUsed = 0;
    }
    //method for dealing a new card from the deck array
    public Card DealCard() {
        if (cardsUsed == NCARDS) {
            System.out.println("Deck is empty");
            return null;
        } else {
            cardsUsed++;
            return deck[cardsUsed - 1];
        }

    }

    // method for shuffling the deck of cards
    public void Shuffle() {
        for (int i = 0; SHUFFLENUM > i; i++) {
            int rand1 = (r1.Generate());
            int rand2 = (r2.Generate());
            Card temp = deck[rand1];
            deck[rand1] = deck[rand2];
            deck[rand2] = temp;
        }
        cardsUsed = 0;
    }
    //keeps track of how many cards are left in the deck
    public int CardsLeft() {
        return deck.length - cardsUsed;
    }

}
