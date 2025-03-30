import java.util.Scanner;

public class lab6 {

    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();

        // populate linked list with a single deck of cards
        for (Card.suites s : Card.suites.values()) {
            for(Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suites.NULL) {
                    Card newCard = new Card(s, r);
                    //newCard.print_card();
                    deck.add_at_tail(newCard);
                }
            }
        }

        return deck;
    }

    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck) {
        System.out.println("\nStarting Blind mans Bluff \n");
        // play the game FIXME

        // create scanner
        Scanner scnr = new Scanner(System.in);

        // initialize variables
        int playerWins = 0;
        int computerWins = 0;
        int playerLosses = 0;
        int computerLosses = 0;
        int playerLossesInARow = 0;

        // plays 5 rounds
        for (int i = 0; i < 5; i++) {
            System.out.println("Round " + (i + 1) + ": ");

            // check cards in deck
            if (deck.size < 2) {
                System.out.println("Not enough cards to play");
                break;
            }
            // each player draws card
            Card player1Card = player1.remove_from_head();
            Card computerCard = computer.remove_from_head();

            // show computers card
            System.out.print("Computer's card: ");
            computerCard.print_card();

            // ask user higher or lower
            System.out.println();
            System.out.println("\nHigher or lower?");
            String answer = scnr.next();

            // compare cards
            int result = player1Card.compare(computerCard);

            // outcome based on the comparison of cards and what the user answered
            if ((result < 0 && answer.equals("higher")) || (result > 0 && answer.equals("lower"))) {
                System.out.println("\nComputer wins this round\n");
                computerWins = computerWins + 1;
                playerLosses = playerLosses + 1;
                playerLossesInARow = playerLossesInARow + 1;
            } else if ((result > 0 && answer.equals("higher")) || (result < 0 && answer.equals("lower"))) {
                System.out.println("\nPlayer 1 wins this round\n");
                playerWins = playerWins + 1;
                computerLosses = computerLosses + 1;
                playerLossesInARow = 0; // Reset loss count
            } else {
                System.out.println("\nIt's a tie!");
            }

            // add cards back to deck
            deck.add_at_tail(player1Card);
            deck.add_at_tail(computerCard);

            // rage quit if player has 3 losses in a row
            if (playerLossesInARow >= 3) {
                rage_quit(player1, computer, deck);
                break;
            }
        }
        // output game stats
        summaryStats(playerWins, playerLosses, computerWins, computerLosses);
    }

    private static void rage_quit(LinkedList player1, LinkedList computer, LinkedList deck) {
        System.out.println("RAGE QUITTING");

        // add all cards back to deck
        while (!player1.isEmpty()) {
            deck.add_at_tail(player1.remove_from_head());
        }

        while (!computer.isEmpty()) {
            deck.add_at_tail(computer.remove_from_head());
        }

        // reshuffle deck
        deck.shuffle(512);

        // redeal cards
        for (int i = 0; i < 5; i++) {
            if (!deck.isEmpty()) {
                player1.add_at_tail(deck.remove_from_head());
            }
            if (!deck.isEmpty()) {
                computer.add_at_tail(deck.remove_from_head());
            }
        }
        // play game
        play_blind_mans_bluff(player1, computer, deck);

    }

    private static void summaryStats(int playerWins, int playerLosses,
                                     int computerWins, int computerLosses) {
        // print wins for each player
        System.out.println("\nGame Stats: ");
        System.out.println("Player 1: " + playerWins + " wins and " + playerLosses + " losses");
        System.out.println("Computer: " + computerWins + " wins and " + computerLosses + " losses");

    }

    public static void main(String[] args) {

        // create a deck (in order)
        LinkedList deck = initialize_deck();
        deck.print();
        deck.sanity_check(); // because we can all use one

        // shuffle the deck (random order)
        deck.shuffle(512);
        deck.print();
        deck.sanity_check(); // because we can all use one

        // cards for player 1 (hand)
        LinkedList player1 = new LinkedList();
        // cards for player 2 (hand)
        LinkedList computer = new LinkedList();

        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }

        // let the games begin!
        play_blind_mans_bluff(player1, computer, deck);
    }
}