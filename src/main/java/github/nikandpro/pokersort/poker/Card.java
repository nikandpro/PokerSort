package github.nikandpro.pokersort.poker;

public class Card implements Comparable<Card> {

    private final int value;
    private final Suit suit;

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }


    public enum Suit {
        S, H, D, C;
    }

    public Card(String value, String suit) {
        this.suit = Suit.valueOf(suit);
        this.value = intValue(value);
    }

    private int intValue(String value) {
        return switch (value) {
            case "A" -> 14;
            case "K" -> 13;
            case "Q" -> 12;
            case "J" -> 11;
            case "T" -> 10;
            default -> Integer.parseInt(value);
        };
    }

    private int valueSuit(Suit f) {
        return switch (f) {
            case Suit.S -> 1;
            case Suit.H -> 2;
            case Suit.D -> 3;
            case Suit.C -> 4;
        };
    }

    private String valueName() {
        return switch (this.value) {
            case 14 -> "A";
            case 13 -> "K";
            case 12 -> "Q";
            case 11 -> "J";
            case 10 -> "T";
            default -> String.valueOf(this.value);
        };
    }

    @Override
    public String toString() {
        return "{" + valueName() + suit +
                '}';
    }

    @Override
    public int compareTo(Card o) {
        int suitValue1 = valueSuit(this.suit);
        int suitValue2 = o.valueSuit(o.suit);
        if (suitValue1 > suitValue2) {
            return 1;
        } else if (suitValue1 < suitValue2) {
            return -1;
        } else {
            if (this.value > o.value) {
                return 1;
            } else if (this.value < o.value) {
                return -1;
            }
        }
        return 0;
    }
}

