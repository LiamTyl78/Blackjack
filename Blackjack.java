import java.util.Scanner;
import java.util.ArrayList;

public class Blackjack {

    private ArrayList<Card> current_cards = new ArrayList<Card>();
    private ArrayList<Card> dealer_cards = new ArrayList<Card>();
    private Deck deck;
    private static int balance = 1000;

    public Blackjack() {
        this.deck = new Deck();
    }

    public void Play() {
        boolean playing = true;
        System.out.println("Welcome to Blackjack!\nThe goal of this game is to get your hand to a value of 21, but be careful not to go over!\n ");
        while (playing && balance > 0) {
            if(balance > 0){
            PlayaRound();
            char answer = playagainprompt();
            if (answer == 'n') {
                playing = false;
            }
        }
        if(balance == 0){
            System.out.println("Looks like your all outta cash, game over!");
        }
    }
}

    public void PlayaRound() {
        boolean valid_bet = true;
        int bet = 1;
        dealer_cards.clear();
        current_cards.clear();

        Scanner Input = new Scanner(System.in);
        deck.Shuffle();
        dealer_cards.add(deck.DealCard());
        dealer_cards.add(deck.DealCard());

        while (valid_bet) {
            System.out.print("How much would you like to bet? Your current balance is $" + balance + "\n$");
            bet = Input.nextInt();
            if(bet > 0 && bet <= balance){
                System.out.println("Okay you made a bet of $" + bet);
                balance = balance - bet;
                valid_bet = false;
            }
            else{
                System.out.println("Please enter a number between $1 and $" + balance);
            }
        }
        System.out.println("The dealer has a " + (dealer_cards.get(0)));
        int Player_Hand = Deal();

        current_cards.clear();
        for (int i = 0; i < dealer_cards.size(); i++) {
            current_cards.add(dealer_cards.get(i));
        }

        int Dealer_Hand = DealerDeal();
        System.out.println("\nDealer has: " + Dealer_Hand + " Player has: " + Player_Hand + "\n");
        if (Player_Hand > 21) {
            System.out.println("You Busted!");
        }
        else if(Player_Hand == 21){
            System.out.println("You got blackjack! You automatically win!");
            balance += bet * 2;
        } 
        else if (Dealer_Hand > Player_Hand && Dealer_Hand <= 21) {
            System.out.println("You Lost");
            if(Dealer_Hand == 21){
                System.out.print("Dealer had blackjack!");
            }
            else{
                System.out.print("Dealer had ");
                for (int i = 0; i < current_cards.size(); i++) {
                    System.out.print(current_cards.get(i) + ", ");
                }
                System.out.println("for a total hand value of " + CardSum() + ", while you had " + Player_Hand);
            }
        } else if (Dealer_Hand > 21) {
            balance += bet * 2;
            System.out.println("You won cause the dealer busted! Your new balance is: $" + balance);
        } else if (Dealer_Hand < Player_Hand) {
            balance += bet * 2;
            System.out.println("You Won! Your balance is now: $" + balance);
        }
        Input.close();
    }

    public char playagainprompt() {
        char answer = 'n';
        if(balance > 0){
            System.out.println("Would you like to play again? y/n");
            Scanner in = new Scanner(System.in);
            answer = in.next().charAt(0);
            in.close();
        }
        return answer;
    }

    public int CardSum() {
        int sum = 0;
        for (int i = 0; i < current_cards.size(); i++) {
            int added_val = current_cards.get(i).GetValue();
            if (added_val >= 11) {
                added_val = 10;
            }
            sum += added_val;
        }
        return sum;
    }

    public int Deal() {
        boolean Dealing = true;
        int hand_value = 0;
        Scanner Input = new Scanner(System.in);
        current_cards.add(deck.DealCard());
        current_cards.add(deck.DealCard());
        while (Dealing) {
            int total_value = CardSum();
            System.out.print("Current Hand: ");
            for (int i = 0; i < current_cards.size(); i++) {
                System.out.print(current_cards.get(i) + ", ");
            }
            System.out.println("for a total hand value of " + total_value + ".\nWould you like to hit(h) or stand(s)?");
            String answer = Input.nextLine();
            boolean answer_provided = true;
            while (answer_provided) {
                if (answer.equals("h") || answer.equals("hit")) {
                    current_cards.add(deck.DealCard());
                    System.out.println("Dealt a " + current_cards.get(current_cards.size() - 1));
                    answer_provided = false;
                } else if (answer.equals("s") || answer.equals("stand")) {
                    Dealing = false;
                    answer_provided = false;
                } else {
                    System.out.println("Please input either 'h' for 'hit' or 's' for 'stand'.");
                    answer = Input.nextLine();
                }
            }
            if (CardSum() >= 21) {
                Dealing = false;
            }
            hand_value = CardSum();
        }
        Input.close();
        return hand_value;
    }

    public int DealerDeal() {
        int hand_value = 0;
        while (hand_value < 17) {
            current_cards.add(deck.DealCard());
            hand_value = CardSum();
        }

        return hand_value;
    }
}
