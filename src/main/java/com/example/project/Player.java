package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    // Evaluates and returns the best poker hand the player has
    public String playHand(ArrayList<Card> communityCards) {
        allCards.clear();
        allCards.addAll(hand); // Add player's hand
        allCards.addAll(communityCards); // Add community cards
        return "Nothing"; // Default return 
    }

    public void sortAllCards() {
        // Selection Sort algorithm to sort cards by rank value
        for (int i = 0; i < allCards.size() - 1; i++) {
            int minIndex = i; // Assume the current index has the smallest rank
    
            // Find the index of the smallest rank in the remaining list
            for (int j = i + 1; j < allCards.size(); j++) {
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(minIndex).getRank())) {
                    minIndex = j; // Update minIndex if a smaller rank is found
                }
            }
    
            // Swap the smallest found card with the current card at index i
            if (minIndex != i) {
                Card temp = allCards.get(i);
                allCards.set(i, allCards.get(minIndex));
                allCards.set(minIndex, temp);
            }
        }
    }
    

    public ArrayList<Integer> findRankingFrequency() {
        ArrayList<Integer> rankFrequency = new ArrayList<>();
    
        // Initialize frequency list with zeros (one for each rank)
        for (int i = 0; i < 13; i++) {
            rankFrequency.add(0);
        }
    
        // Count occurrences of each rank
        for (int i = 0; i < ranks.length; i++) { // Loop through each rank
            int count = 0;
            for (Card card : allCards) { // Loop through all player's cards
                if (card.getRank().equals(ranks[i])) { // Check if the card matches the current rank
                    count++; // Increase the count for this rank
                }
            }
            rankFrequency.set(i, count); // Store the count in the list
        }
        return rankFrequency;
    }
    
    public ArrayList<Integer> findSuitFrequency() {
        ArrayList<Integer> suitFrequency = new ArrayList<>();
    
        // Initialize frequency list with zeros (one for each suit)
        for (int i = 0; i < 4; i++) {
            suitFrequency.add(0);
        }
    
        // Count occurrences of each suit
        for (int i = 0; i < suits.length; i++) { // Loop through each suit
            int count = 0;
            for (Card card : allCards) { // Loop through all player's cards
                if (card.getSuit().equals(suits[i])) { // Check if the card matches the current suit
                    count++; // Increase the count for this suit
                }
            }
            suitFrequency.set(i, count); // Store the count in the list
        }
    
        return suitFrequency;
    }
    
    
   
    @Override
    public String toString(){
        return hand.toString();
    }




}
