package com.example.finddamatch.Classes;
/*

Description : contains the newest card that is discarded

 */
import static com.example.finddamatch.MainActivity.Deck;

public class Discard {
    Cards top;

    public Discard(Cards top) {
        this.top = top;
    }
    public boolean contain(String selected)
    {
        if(top.contain(selected)){
            Deck.discard();
            Deck.draw();
            return true;//if true it discard the top card of the deck (hand) and put it to the "top" of the discard and finishes the activity
        }
        return false;
    }


    public String[] getCards() {
        return top.getCards();
    }
}