package com.example.project;
import java.util.ArrayList;

// Represents a single playing card with rank and suit
public class Card{
    private String rank;
    private String suit;

// Constructor to initialize a card with a given rank and suit
    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank(){return rank;}
    public String getSuit(){return suit;}
    
    @Override
    public String toString(){
        return rank + " of " + suit;
    }

}