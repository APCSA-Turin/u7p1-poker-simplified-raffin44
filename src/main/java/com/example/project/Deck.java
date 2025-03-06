package com.example.project;
import java.util.ArrayList;
import java.util.Collections;


// Represents a deck of playing cards
public class Deck{
    private ArrayList<Card> cards;


// Constructor initializes and shuffles the deck
    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }


// Returns the list of cards in the deck    
    public ArrayList<Card> getCards(){
        return cards;
    }
    // // Initializes the deck with 52 unique cards
    public void initializeDeck() {
        String[] ranks = Utility.getRanks();
        String[] suits = Utility.getSuits();
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                cards.add(new Card(ranks[j], suits[i]));  // Add a new card to the deck
            }
        }
    }


    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }


    public  Card drawCard(){
        if(cards.size() == 0){
            return null;
        }
        return cards.remove(0);
    }


    public  boolean isEmpty(){
        return cards.isEmpty();
    }

}
