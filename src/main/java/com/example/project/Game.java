package com.example.project;
import java.util.ArrayList;

public class Game {
    
    // Determines the winner between two players based on their hands and the community cards
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards) {
        // Get the hand rankings for both players (higher rank means better hand)
        int p1Rank = Utility.getHandRanking(p1Hand);
        int p2Rank = Utility.getHandRanking(p2Hand);
        
        // Compare hand rankings
        if (p1Rank > p2Rank) {
            return "Player 1 wins!"; // Player 1 has a higher-ranking hand
        } else if (p2Rank > p1Rank) {
            return "Player 2 wins!"; // Player 2 has a higher-ranking hand
        } else if (p1Rank == p2Rank) {
            // If both players have the same hand rank, compare the values of the cards in their hands
            for (int i = 0; i < p1.getHand().size(); i++) {
                int p1CardValue = Utility.getRankValue(p1.getHand().get(i).getRank()); // Get card value for Player 1
                int p2CardValue = Utility.getRankValue(p2.getHand().get(i).getRank()); // Get card value for Player 2
                
                // Compare individual card values
                if (p1CardValue > p2CardValue) {
                    return "Player 1 wins!"; // Player 1 has a higher card value
                } else if (p2CardValue > p1CardValue) {
                    return "Player 2 wins!"; // Player 2 has a higher card value
                }
            }
        }
        return "Tie!"; // If all comparisons result in a tie, declare a tie
    }
}
