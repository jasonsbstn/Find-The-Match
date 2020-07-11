package com.example.finddamatch.Classes;

public class cards {
    String images[];
    int number;

    public cards(String[] images, int number) {
        this.images = images;
        this.number = number;
    }
    public boolean contain(String img)
    {
        if(images.equals(img))
            return true;
        return false;
    }
    public int startGame()
    {
        return number;
    }
}
