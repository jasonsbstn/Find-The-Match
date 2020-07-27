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
            String pics[] = {"pic1", "pic2", "pic3", "pic10"};
            cardsList.add(new cards(pics, 1));
            String pics2[] = {"pic10", "pic4", "pic5", "pic6"};
            cardsList.add(new cards(pics2, 2));
            String pics3[] = {"pic9", "pic10", "pic7", "pic8"};
            cardsList.add(new cards(pics3, 3));
            String pics4[] = {"pic1", "pic11", "pic4", "pic7"};
            cardsList.add(new cards(pics4, 4));
            String pics5[] = {"pic2", "pic11", "pic5", "pic8"};
            cardsList.add(new cards(pics5, 5));
            String pics6[] = {"pic9", "pic3", "pic11", "pic6"};
            cardsList.add(new cards(pics6, 6));
            String pics7[] = {"pic1", "pic9", "pic12", "pic4"};
            cardsList.add(new cards(pics7, 7));
            String pics8[] = {"pic2", "pic12", "pic6", "pic7"};
            cardsList.add(new cards(pics8, 8));
            String pics9[] = {"pic12", "pic3", "pic4", "pic8"};
            cardsList.add(new cards(pics9, 9));
            String pics10[] = {"pic1", "pic13", "pic6", "pic8"};
            cardsList.add(new cards(pics10, 10));
            String pics11[] = {"pic9", "pic2", "pic4", "pic13"};
            cardsList.add(new cards(pics11, 11));
            String pics12[] = {"pic13", "pic3", "pic5", "pic7"};
            cardsList.add(new cards(pics12, 12));
            String pics13[] = {"pic10", "pic11", "pic12", "pic13"};
            cardsList.add(new cards(pics13, 13));
        }
        else if(order==5) {
            String pics[] = {"pic1", "pic2", "pic3", "pic4", "pic5", "pic26"};
            cardsList.add(new cards(pics, 1));
            String pics2[] = {"pic6", "pic7", "pic8", "pic9", "pic10", "pic26"};
            cardsList.add(new cards(pics2, 2));
            String pics3[] = {"pic11", "pic12", "pic13", "pic14", "pic15", "pic26"};
            cardsList.add(new cards(pics3, 3));
            String pics4[] = {"pic16", "pic17", "pic18", "pic19", "pic20", "pic26"};
            cardsList.add(new cards(pics4, 4));
            String pics5[] = {"pic21", "pic22", "pic23", "pic24", "pic25", "pic26"};
            cardsList.add(new cards(pics5, 5));
            String pics6[] = {"pic1", "pic6", "pic11", "pic16", "pic21", "pic27"};
            cardsList.add(new cards(pics6, 6));
            String pics7[] = {"pic2", "pic7", "pic12", "pic17", "pic22", "pic27"};
            cardsList.add(new cards(pics7, 7));
            String pics8[] = {"pic3", "pic8", "pic13", "pic18", "pic23", "pic27"};
            cardsList.add(new cards(pics8, 8));
            String pics9[] = {"pic4", "pic9", "pic14", "pic19", "pic24", "pic27"};
            cardsList.add(new cards(pics9, 9));
            String pics10[] = {"pic5", "pic10", "pic15", "pic20", "pic25", "pic27"};
            cardsList.add(new cards(pics10, 10));
            String pics11[] = {"pic1", "pic7", "pic13", "pic19", "pic25", "pic28"};
            cardsList.add(new cards(pics11, 11));
            String pics12[] = {"pic2", "pic8", "pic14", "pic20", "pic21", "pic28"};
            cardsList.add(new cards(pics12, 12));
            String pics13[] = {"pic3", "pic9", "pic15", "pic16", "pic22", "pic28"};
            cardsList.add(new cards(pics13, 13));
            String pics14[] = {"pic4", "pic10", "pic11", "pic17", "pic23", "pic28"};
            cardsList.add(new cards(pics14, 14));
            String pics15[] = {"pic5", "pic6", "pic12", "pic18", "pic24", "pic28"};
            cardsList.add(new cards(pics15, 15));
            String pics16[] = {"pic1", "pic8", "pic15", "pic17", "pic24", "pic29"};
            cardsList.add(new cards(pics16, 16));
            String pics17[] = {"pic2", "pic9", "pic11", "pic18", "pic25", "pic29"};
            cardsList.add(new cards(pics17, 17));
            String pics18[] = {"pic3", "pic10", "pic12", "pic19", "pic21", "pic29"};
            cardsList.add(new cards(pics18, 18));
            String pics19[] = {"pic4", "pic6", "pic13", "pic20", "pic22", "pic29"};
            cardsList.add(new cards(pics19, 19));
            String pics20[] = {"pic5", "pic7", "pic14", "pic16", "pic23", "pic29"};
            cardsList.add(new cards(pics20, 20));
            String pics21[] = {"pic1", "pic9", "pic12", "pic20", "pic23", "pic30"};
            cardsList.add(new cards(pics21, 21));
            String pics22[] = {"pic2", "pic10", "pic13", "pic16", "pic24", "pic30"};
            cardsList.add(new cards(pics22, 22));
            String pics23[] = {"pic3", "pic6", "pic14", "pic17", "pic25", "pic30"};
            cardsList.add(new cards(pics23, 23));
            String pics24[] = {"pic4", "pic7", "pic15", "pic18", "pic21", "pic30"};
            cardsList.add(new cards(pics24, 24));
            String pics25[] = {"pic5", "pic8", "pic10", "pic19", "pic22", "pic30"};
            cardsList.add(new cards(pics25, 25));
            String pics26[] = {"pic1", "pic10", "pic14", "pic18", "pic22", "pic31"};
            cardsList.add(new cards(pics26, 26));
            String pics27[] = {"pic2", "pic6", "pic15", "pic19", "pic23", "pic31"};
            cardsList.add(new cards(pics27, 27));
            String pics28[] = {"pic3", "pic7", "pic11", "pic20", "pic24", "pic31"};
            cardsList.add(new cards(pics28, 28));
            String pics29[] = {"pic4", "pic8", "pic12", "pic16", "pic25", "pic31"};
            cardsList.add(new cards(pics29, 29));
            String pics30[] = {"pic5", "pic9", "pic13", "pic17", "pic21", "pic31"};
            cardsList.add(new cards(pics30, 30));
            String pics31[] = {"pic26", "pic27", "pic28", "pic29", "pic30", "pic31"};
            cardsList.add(new cards(pics31, 31));
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
