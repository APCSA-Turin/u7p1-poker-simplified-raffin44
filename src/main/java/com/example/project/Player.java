package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private ArrayList<Card> hand; // Player's hand of cards
    private ArrayList<Card> allCards; // Player's hand + the community cards
    String[] suits  = Utility.getSuits(); // Array to hold the suits 
    String[] ranks = Utility.getRanks(); // Array to hold the card ranks 
    
    // Constructor to initialize hand and allCards as empty ArrayLists
    public Player() {
        hand = new ArrayList<Card>();
        allCards = new ArrayList<Card>();
    }

    // Getter for hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Getter for allCards (hand + community cards)
    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    // Adds a card to the player's hand
    public void addCard(Card c) {
        hand.add(c);
    }

    // Determines if the player's hand contains the highest card among all cards
    public boolean highCard() {
        int maxRankValue = -1; 
        // Find the highest card value in allCards
        for (Card card : allCards) {
            int cardValue = Utility.getRankValue(card.getRank());
            if (cardValue > maxRankValue) {
                maxRankValue = cardValue;
            }
        }

        // Check if the highest card is in the player's hand
        for (Card card : hand) {
            if (Utility.getRankValue(card.getRank()) == maxRankValue) {
                return true; // Found highest card in hand
            }
        }
        return false; // No highest card in hand
    }

    // Evaluates the player's hand given the community cards
    public String playHand(ArrayList<Card> communityCards) {
        allCards.clear(); // Clear previous allCards
        allCards.addAll(hand); // Add player's hand to allCards
        allCards.addAll(communityCards); // Add community cards to allCards

        if (allCards.size() == 5) {
            if (royalFlush()) {
                return "Royal Flush"; // Royal Flush Check
            } 
            if (straightFlush()) {
                return "Straight Flush"; // Straight Flush check
            } 
            if (fourOfAKind()) {
                return "Four of a Kind"; // Four of a Kind check
            } 
            if (fullHouse()) {
                return "Full House"; // Full House check
            } 
            if (Flush()) {
                return "Flush"; // Flush check
            } 
            if (Straight()) {
                return "Straight"; // Straight check
            } 
            if (threeOfAKind()) {
                return "Three of a Kind"; // Three of a Kind check
            } 
            if (twoPair()) {
                return "Two Pair"; // Two Pair check
            } 
            if (onePair()) {
                return "A Pair"; // Pair check
            } 
            if (highCard()) {
                return "High Card"; // High Card check
            }
            return "Nothing"; // No valid hand
        }
        return ""; // If there are not exactly 5 cards, return an empty string
    }
    
    // Sorts the player's cards by their rank
    public void sortAllCards() {
        for (int i = 0; i < allCards.size(); i++) {
            int idx = i;
            for (int j = i + 1; j < allCards.size(); j++) {
                // Compare card ranks using Utility.getRankValue()
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(idx).getRank())) {
                    idx = j; // Find the card with the lower rank value
                }
            }
            // Swap the cards
            Card min = allCards.get(idx);
            allCards.set(idx, allCards.get(i));
            allCards.set(i, min);
        }
    } 

    // Finds the frequency of each card rank in allCards
    public ArrayList<Integer> findRankingFrequency() {
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        // Initialize frequency array with 0 for each rank
        for (int i = 0; i < ranks.length; i++) {
            frequency.add(0);
        }
        // Count frequency of each rank in allCards
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 0; j < ranks.length; j++) {
                if (allCards.get(i).getRank().equals(ranks[j])) {
                    frequency.set(j, frequency.get(j) + 1); // Increment frequency of the rank
                    break;
                }
            }
        }
        return frequency; // Return the frequency array
    }

    // Finds the frequency of each suit in allCards
    public ArrayList<Integer> findSuitFrequency() {
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        // Initialize frequency array with 0 for each suit
        for (int i = 0; i < suits.length; i++) {
            frequency.add(0);
        }
        // Count frequency of each suit in allCards
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 0; j < suits.length; j++) {
                if (allCards.get(i).getSuit().equals(suits[j])) {
                    frequency.set(j, frequency.get(j) + 1); // Increment frequency of the suit
                    break;
                }
            }
        }
        return frequency; // Return the frequency array
    }

    // Checks if the player has a pair
    public boolean onePair() {
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 2) {
                return true; // Found a pair
            }
        }
        return false; // No pair
    }

    // Checks if the player has two pairs
    public boolean twoPair() {
        int count = 0;
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 2) {
                count++; // Found a pair
            }
        }
        return count == 2; // Two pairs if count is 2
    }

    // Checks if the player has three of a kind
    public boolean threeOfAKind() {
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 3) {
                return true; // Found three of a kind
            }
        }
        return false; // No three of a kind
    }

    // Checks if the player has a straight
    public boolean Straight() {
        int count = 0;
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 1) {
                count++; // Found a card with unique rank
                if (count == 5) {
                    return true; // Found a straight
                }
            } else {
                count = 0; // Reset count if no consecutive cards
            }
        }
        return false; // No straight
    }

    // Checks if the player has a flush
    public boolean Flush() {
        for (int i = 0; i < findSuitFrequency().size(); i++) {
            if (findSuitFrequency().get(i) == 5) {
                return true; // Found a flush
            }
        }
        return false; // No flush
    }

    // Checks if the player has a full house
    public boolean fullHouse() {
        boolean two = false;
        boolean three = false;
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 2) {
                two = true; // Found a pair
            } else if (findRankingFrequency().get(i) == 3) {
                three = true; // Found three of a kind
            }
        }
        return two && three; // Full house if both a pair and three of a kind are found
    }

    // Checks if the player has four of a kind
    public boolean fourOfAKind() {
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 4) {
                return true; // Found four of a kind
            }
        }
        return false; // No four of a kind
    }

    // Checks if the player has a straight flush
    public boolean straightFlush() {
        return Straight() && Flush(); // Straight flush if both straight and flush are true
    }

    // Checks if the player has a royal flush
    public boolean royalFlush() {
        boolean royal = true;
        // Check if all cards are of ranks 10 or higher
        for (int i = 8; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) != 1) {
                return false; // Not a royal flush
            }
        }
        // If flush is true and all cards are 10 or higher, it's a royal flush
        return royal && Flush();
    }

    // Returns a string representation of the player's hand
    @Override
    public String toString() {
        return hand.toString(); // Convert hand to string for display
    }

    // Main method for testing
    public static void main(String[] args) {
        Player player = new Player();
        player.addCard(new Card("9", "♠"));
        player.addCard(new Card("9", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("9", "♣"));
        communityCards.add(new Card("9", "♥"));
        communityCards.add(new Card("A", "♦"));
        
        // Play the hand and print the result
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        System.out.println(handResult); // Output the result of the hand
    }
}
