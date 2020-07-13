package com.example.finddamatch.Classes;

import static com.example.finddamatch.MainActivity.Deck;

public class discard {
    cards top;

    public discard(cards top) {
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