import java.util.Arrays;

// Standard French-style cards
public class Card {

    // Suites
    public enum suites {
        NULL, SPADES, CLUBS, DIAMONDS, HEARTS
    }

    // Ranks
    public enum ranks {
        NULL, two, three, four, five, six, seven, eight, nine, ten, jack, king, queen, ace
    }

    private suites suit;
    private ranks rank;

    Card(){
        suit = suites.NULL;
        rank = ranks.NULL;
    }

    Card(suites s, ranks r){
        suit = s;
        rank = r;
    }

    public void print_card(){
        System.out.print(suit + ": " + rank);
    }

    // method to compare computer card to palyer card
    public int compare(Card card) {
        // compare rank
        int comparedRank = compareRank(this.rank, card.rank);

        // compare suits if ranks are equal
        if (comparedRank == 0) {
            return compareSuit(this.suit, card.suit);
        } else {
            return comparedRank;
        }

    }

    // compares the ranks of the two cards and outputs accordingly
    private int compareRank(ranks rank1, ranks rank2) {
        // order or ranks
        String[] ranking = {"two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "jack", "king", "queen", "ace"};

        int rank1Index = -1;
        int rank2Index = -1;

        for (int i = 0; i < ranking.length; i++) {
            if (ranking[i].equals(rank1.toString())) {
                rank1Index = i;
            }
            if (ranking[i].equals(rank2.toString())) {
                rank2Index = i;
            }
        }

        if (rank1Index > rank2Index) {
            return 1;
        } else if (rank1Index < rank2Index) {
            return -1;
        } else {
            return 0;
        }
    }

    // compares suits of the two cards and outputs accordingly
    private int compareSuit(suites suit1, suites suit2) {
        // if suits are equal return 0
        if (suit1 == suit2) {
            return 0;
        }

        if (suit1 == suites.SPADES) {
            // spades always wins
            return 1;
        }

        // clubs loses to spades but beats all others
        if (suit1 == suites.CLUBS) {
            if (suit2 == suites.SPADES) {
                return -1;
            } else {
                return 1;
            }
        }

        // diamonds loses to spades and clubs but beats hearts
        if (suit1 == suites.DIAMONDS) {
            if (suit2 == suites.SPADES || suit2 == suites.CLUBS) {
                return -1;
            } else {
                return 1;
            }
        }

        if (suit1 == suites.HEARTS) {
            // hearts always loses
            return -1;
        }

        return 0;
    }

}