package com.example.project;
import java.util.ArrayList;


public class Game{
    // Determines the winner between two players
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards) {
        int p1Rank = Utility.getHandRanking(p1Hand);
        int p2Rank = Utility.getHandRanking(p2Hand);
    
        if (p1Rank > p2Rank) {
            return "Player 1 wins!";
        } else if (p1Rank < p2Rank) {
            return "Player 2 wins!";
        }
        return "Tie!"; // returns a draw if both hands are equal
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}