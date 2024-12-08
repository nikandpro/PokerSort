package github.nikandpro.pokersort.poker;

import java.util.*;

public class PokerHand implements Comparable<PokerHand> {

    private final Card[] cards;
    private final int rank;

    public PokerHand(String cardsString) {
        String[] cardsArray = cardsString.split(" ");
        cards = new Card[5];

        for (int i = 0; i < cardsArray.length; i++) {
            cards[i] = new Card(cardsArray[i].substring(0, 1), cardsArray[i].substring(1, 2));
        }

        Arrays.sort(cards);
        rank = calculateRank();
    }

    public int calculateRank() {
        List<Integer> dublicates = new ArrayList<>(dublicates());
        dublicates.sort(Collections.reverseOrder());

        if (isRoyalFlash())
            return 9;
        if (isStraightFlush())
            return 8;
        if (isFourOfAKind(dublicates))
            return 7;
        if (isFullHouse(dublicates))
            return 6;
        if (isFlush())
            return 5;
        if (isStraight())
            return 4;
        if (isThreeOfAKind(dublicates))
            return 3;
        if (isTwoPair(dublicates))
            return 2;
        if (isPair(dublicates))
            return 1;

        return 0;
    }


    private List<Integer> dublicates() {
        Map<Integer, Integer> cardValues = new HashMap<Integer, Integer>();
        Arrays.stream(cards).map(Card::getValue).toList().forEach(v -> {
            if (cardValues.containsKey(v)) {
                cardValues.put(v, cardValues.get(v) + 1);
            } else {
                cardValues.put(v, 1);
            }
        });

        return cardValues.values().stream().toList();
    }

    private boolean isPair(List<Integer> dublicates) {
        return dublicates.getFirst() == 2;
    }

    private boolean isTwoPair(List<Integer> dublicates) {
        return dublicates.getFirst() == 2 && dublicates.get(1) == 2;
    }


    private boolean isThreeOfAKind(List<Integer> dublicates) {
        return dublicates.getFirst() == 3;
    }

    private boolean isStraight() {
        List<Integer> values = new ArrayList<>(Arrays.stream(cards).map(Card::getValue).toList());
        Collections.sort(values);
        return values.getLast()-values.getFirst()==4;
    }

    private boolean isFlush() {
        return cards[0].getSuit() == cards[4].getSuit();
    }

    private boolean isFullHouse(List<Integer> dublicates) {
        return dublicates.size() == 2 && dublicates.getFirst() == 3;
    }

    private boolean isFourOfAKind(List<Integer> dublicates) {
        return dublicates.size() == 2 && dublicates.getFirst() == 4;
    }

    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    private boolean isRoyalFlash() {
        return isStraightFlush() && cards[4].getValue() == 14;
    }

    private int maxValue() {
        int max = 0;
        for (Card card : cards) {
            if (card.getValue() > max) {
                max = card.getValue();
            }
        }
        return max;
    }

    @Override
    public int compareTo(PokerHand hand) {
        if (this.rank < hand.rank) {
            return 1;
        } else if (this.rank > hand.rank) {
            return -1;
        } else {
            if (this.maxValue() < hand.maxValue()) {
                return 1;
            } else if (this.maxValue() > hand.maxValue()) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "PokerHand{" +
                Arrays.toString(cards) +
                ", rank=" + rank +
                '}';
    }

}
