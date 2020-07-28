package com.example.finddamatch.Classes;
/*

Description : stores the image of the cards

 */
public class Cards {
    public String images[];
    public int number;

    public Cards(String[] images, int number) {
        this.images = images;
        this.number = number;
    }
    public String[] getCards()
    {
        return images;
    }
    public boolean contain(String img)
    {
        for(String s:images)
            if(s==img)
                return true;
        return false;//check if there is a same picture within the card
    }
    public int startGame()
    {
        return number;
    }
}
