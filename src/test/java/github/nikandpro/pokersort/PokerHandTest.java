package github.nikandpro.pokersort;

import github.nikandpro.pokersort.poker.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokerHandTest {

    @Test
    public void testConstructorWithValidInput() {
        PokerHand hand = new PokerHand("2H 3D 5S 9C KD");
        assertNotNull(hand);
    }

    @Test
    public void testCalculateRankHighCard() {
        PokerHand hand = new PokerHand("2H 3D 5S 9C KD");
        assertEquals(0, hand.calculateRank(), "Expected rank for High Card should be 0");
    }

    @Test
    public void testCalculateRankPair() {
        PokerHand hand = new PokerHand("2H 2D 5S 9C KD");
        assertEquals(1, hand.calculateRank(), "Expected rank for One Pair should be 1");
    }

    @Test
    public void testCalculateRankTwoPair() {
        PokerHand hand = new PokerHand("2H 2D 5S 5C KD");
        assertEquals(2, hand.calculateRank(), "Expected rank for Two Pair should be 2");
    }

    @Test
    public void testCalculateRankThreeOfAKind() {
        PokerHand hand = new PokerHand("2H 2D 2S 9C KD");
        assertEquals(3, hand.calculateRank(), "Expected rank for Three of a Kind should be 3");
    }

    @Test
    public void testCalculateRankStraight() {
        PokerHand hand = new PokerHand("4H 5D 6S 7C 8D");
        assertEquals(4, hand.calculateRank(), "Expected rank for Straight should be 4");
    }

    @Test
    public void testCalculateRankFlush() {
        PokerHand hand = new PokerHand("2H 4H 6H 8H KH");
        assertEquals(5, hand.calculateRank(), "Expected rank for Flush should be 5");
    }

    @Test
    public void testCalculateRankFullHouse() {
        PokerHand hand = new PokerHand("2H 2D 3S 3C 3D");
        assertEquals(6, hand.calculateRank(), "Expected rank for Full House should be 6");
    }

    @Test
    public void testCalculateRankFourOfAKind() {
        PokerHand hand = new PokerHand("2H 2D 2S 2C KD");
        assertEquals(7, hand.calculateRank(), "Expected rank for Four of a Kind should be 7");
    }

    @Test
    public void testCalculateRankStraightFlush() {
        PokerHand hand = new PokerHand("4H 5H 6H 7H 8H");
        assertEquals(8, hand.calculateRank(), "Expected rank for Straight Flush should be 8");
    }

    @Test
    public void testCalculateRankRoyalFlush() {
        PokerHand hand = new PokerHand("TH JH QH KH AH");
        assertEquals(9, hand.calculateRank(), "Expected rank for Royal Flush should be 9");
    }

    @Test
    public void testCompareToHigherRank() {
        PokerHand hand1 = new PokerHand("TH JH QH KH AH"); // Royal Flush
        PokerHand hand2 = new PokerHand("4H 5H 6H 7H 8H"); // Straight Flush

        assertTrue(hand1.compareTo(hand2) < 0, "Royal Flush should be greater than Straight Flush");
    }

    @Test
    public void testCompareToEqualRankDifferentMaxValue() {
        PokerHand hand1 = new PokerHand("TH JH QH KH AH"); // Royal Flush
        PokerHand hand2 = new PokerHand("TH JH QH KH KH"); // Full House

        assertTrue(hand1.compareTo(hand2) < 0, "Royal Flush should be greater than Full House with same ranks but different max values.");
    }

    @Test
    public void testPokerHandSortingByRank() {
        List<PokerHand> hands = new ArrayList<>();

        // Создаем несколько рук покера с различными рангами
        hands.add(new PokerHand("2H 3D 5S 9C KD")); // High Card (rank 0)
        hands.add(new PokerHand("2H 2D 5S 9C KD")); // One Pair (rank 1)
        hands.add(new PokerHand("2H 2D 5S 5C KD")); // Two Pair (rank 2)
        hands.add(new PokerHand("2H 2D 2S 9C KD")); // Three of a Kind (rank 3)
        hands.add(new PokerHand("4H 5D 6S 7C 8D")); // Straight (rank 4)
        hands.add(new PokerHand("2H 4H 6H 8H KH")); // Flush (rank 5)
        hands.add(new PokerHand("2H 2D 3S 3C 3D")); // Full House (rank 6)
        hands.add(new PokerHand("2H 2D 2S 2C KD")); // Four of a Kind (rank 7)
        hands.add(new PokerHand("4H 5H 6H 7H 8H")); // Straight Flush (rank 8)
        hands.add(new PokerHand("TH JH QH KH AH")); // Royal Flush (rank 9)

        // Сортируем руки по возрастанию ранга
        Collections.sort(hands);

        // Проверяем, что руки отсортированы правильно по рангу
        for (int i = 0; i < hands.size() - 1; i++) {
            assertEquals(-1, hands.get(i).compareTo(hands.get(i + 1)),
                    "Hands should be sorted by rank in ascending order.");
        }
    }
}
