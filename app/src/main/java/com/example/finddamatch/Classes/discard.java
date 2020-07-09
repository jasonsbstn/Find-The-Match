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
            return true;
        }
        return false;
    }


}