package com.example.finddamatch.Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.example.finddamatch.MainActivity.hand;
import static com.example.finddamatch.MainActivity.top;

public class deck implements Iterable<cards> {
    private static List<cards> cardsList= new ArrayList<>();
    private deck() {
    }
    private static deck instance;
    public static deck getInstance(){//populates 7 cards
        if(instance == null)
        {
            instance = new deck();
            String pics[] = {"pic1","pic2","pic3"};
            add(new cards(pics,1));
            String pics2[] = {"pic1","pic7","pic4"};
            add(new cards(pics2,2));
            String pics3[] = {"pic1","pic5","pic6"};
            add(new cards(pics3,3));
            String pics4[] = {"pic7","pic5","pic3"};
            add(new cards(pics4,4));
            String pics5[] = {"pic3","pic6","pic4"};
            add(new cards(pics5,5));
            String pics6[] = {"pic2","pic5","pic4"};
            add(new cards(pics6,6));
            String pics7[] = {"pic6","pic2","pic7"};
            add(new cards(pics7,7));


        }
        return instance;
    }

    public static void add(cards card) {
        cardsList.add(card);
    }

    @Override
    public Iterator<cards> iterator() {
        return cardsList.iterator();
    }
    public void shuffle(){
        Collections.shuffle(cardsList);//https://www.javatpoint.com/java-collections-shuffle-method#:~:text=The%20shuffle()%20is%20a,Java%20Collections%20shuffle(list)%20Method
        //shuffles the deck
    }
    public int startGame()
    {
        shuffle();
        top= new discard(cardsList.get(0));
        discard();
        draw();
        return cardsList.get(0).startGame();//discard 1 card and returns the top card of the deck card number
    }

    public void discard() {
        top=new discard(cardsList.get(0));
        cardsList.remove(0);
    }
    public void draw(){
        hand=cardsList.get(0);
    }
}
