package com.example.finddamatch.Classes;
/*

Description : stores the cards using array list

 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.example.finddamatch.MainActivity.hand;
import static com.example.finddamatch.MainActivity.top;
import static com.example.finddamatch.MainActivity.order;

public class deck implements Iterable<cards> {
    private static List<cards> cardsList= new ArrayList<>();

    public deck() {
        if(order==2) {
            String pics[] = {"pic1", "pic2", "pic3"};
            cardsList.add(new cards(pics, 1));
            String pics2[] = {"pic1", "pic7", "pic4"};
            cardsList.add(new cards(pics2, 2));
            String pics3[] = {"pic1", "pic5", "pic6"};
            cardsList.add(new cards(pics3, 3));
            String pics4[] = {"pic7", "pic5", "pic3"};
            cardsList.add(new cards(pics4, 4));
            String pics5[] = {"pic3", "pic6", "pic4"};
            cardsList.add(new cards(pics5, 5));
            String pics6[] = {"pic2", "pic5", "pic4"};
            cardsList.add(new cards(pics6, 6));
            String pics7[] = {"pic6", "pic2", "pic7"};
            cardsList.add(new cards(pics7, 7));
        }
        else if(order==3) {
            String pics[] = {"pic1", "pic2", "pic3"};
            cardsList.add(new cards(pics, 1));
            String pics2[] = {"pic1", "pic7", "pic4"};
            cardsList.add(new cards(pics2, 2));
            String pics3[] = {"pic1", "pic5", "pic6"};
            cardsList.add(new cards(pics3, 3));
            String pics4[] = {"pic7", "pic5", "pic3"};
            cardsList.add(new cards(pics4, 4));
            String pics5[] = {"pic3", "pic6", "pic4"};
            cardsList.add(new cards(pics5, 5));
            String pics6[] = {"pic2", "pic5", "pic4"};
            cardsList.add(new cards(pics6, 6));
            String pics7[] = {"pic6", "pic2", "pic7"};
            cardsList.add(new cards(pics7, 7));
            String pics8[] = {"pic1", "pic2", "pic3"};
            cardsList.add(new cards(pics, 1));
            String pics9[] = {"pic1", "pic7", "pic4"};
            cardsList.add(new cards(pics2, 2));
            String pics10[] = {"pic1", "pic5", "pic6"};
            cardsList.add(new cards(pics3, 3));
            String pics11[] = {"pic7", "pic5", "pic3"};
            cardsList.add(new cards(pics4, 4));
            String pics12[] = {"pic3", "pic6", "pic4"};
            cardsList.add(new cards(pics5, 5));
            String pics13[] = {"pic2", "pic5", "pic4"};
            cardsList.add(new cards(pics6, 6));
        }
        else if(order==5) {
            String pics[] = {"pic1", "pic2", "pic3"};
            cardsList.add(new cards(pics, 1));
            String pics2[] = {"pic1", "pic7", "pic4"};
            cardsList.add(new cards(pics2, 2));
            String pics3[] = {"pic1", "pic5", "pic6"};
            cardsList.add(new cards(pics3, 3));
            String pics4[] = {"pic7", "pic5", "pic3"};
            cardsList.add(new cards(pics4, 4));
            String pics5[] = {"pic3", "pic6", "pic4"};
            cardsList.add(new cards(pics5, 5));
            String pics6[] = {"pic2", "pic5", "pic4"};
            cardsList.add(new cards(pics6, 6));
            String pics7[] = {"pic6", "pic2", "pic7"};
            cardsList.add(new cards(pics7, 7));
            String pics8[] = {"pic1", "pic2", "pic3"};
            cardsList.add(new cards(pics, 1));
            String pics9[] = {"pic1", "pic7", "pic4"};
            cardsList.add(new cards(pics2, 2));
            String pics10[] = {"pic1", "pic5", "pic6"};
            cardsList.add(new cards(pics3, 3));
            String pics11[] = {"pic7", "pic5", "pic3"};
            cardsList.add(new cards(pics4, 4));
            String pics12[] = {"pic3", "pic6", "pic4"};
            cardsList.add(new cards(pics5, 5));
            String pics13[] = {"pic2", "pic5", "pic4"};
            cardsList.add(new cards(pics6, 6));
            String pics14[] = {"pic1", "pic2", "pic3"};
            cardsList.add(new cards(pics, 1));
            String pics15[] = {"pic1", "pic7", "pic4"};
            cardsList.add(new cards(pics2, 2));
            String pics16[] = {"pic1", "pic5", "pic6"};
            cardsList.add(new cards(pics3, 3));
            String pics17[] = {"pic7", "pic5", "pic3"};
            cardsList.add(new cards(pics4, 4));
            String pics18[] = {"pic3", "pic6", "pic4"};
            cardsList.add(new cards(pics5, 5));
            String pics19[] = {"pic2", "pic5", "pic4"};
            cardsList.add(new cards(pics6, 6));
            String pics20[] = {"pic6", "pic2", "pic7"};
            cardsList.add(new cards(pics7, 7));
            String pics21[] = {"pic1", "pic2", "pic3"};
            cardsList.add(new cards(pics, 1));
            String pics22[] = {"pic1", "pic7", "pic4"};
            cardsList.add(new cards(pics2, 2));
            String pics23[] = {"pic1", "pic5", "pic6"};
            cardsList.add(new cards(pics3, 3));
            String pics24[] = {"pic7", "pic5", "pic3"};
            cardsList.add(new cards(pics4, 4));
            String pics25[] = {"pic3", "pic6", "pic4"};
            cardsList.add(new cards(pics5, 5));
            String pics26[] = {"pic2", "pic5", "pic4"};
            cardsList.add(new cards(pics6, 6));
            String pics27[] = {"pic1", "pic7", "pic4"};
            cardsList.add(new cards(pics2, 2));
            String pics28[] = {"pic1", "pic5", "pic6"};
            cardsList.add(new cards(pics3, 3));
            String pics29[] = {"pic7", "pic5", "pic3"};
            cardsList.add(new cards(pics4, 4));
            String pics30[] = {"pic3", "pic6", "pic4"};
            cardsList.add(new cards(pics5, 5));
            String pics31[] = {"pic2", "pic5", "pic4"};
            cardsList.add(new cards(pics6, 6));
        }

    }

    public List<cards> getCards() {
        return cardsList;
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
        if(isEmpty()==false){
            top=new discard(cardsList.get(0));
            cardsList.remove(0);
        }
    }
    public boolean isEmpty()
    {
        if(cardsList.isEmpty())
            return true;
        else return false;
    }
    public void draw(){
        if(isEmpty()==false)
            hand=cardsList.get(0);
    }
}
