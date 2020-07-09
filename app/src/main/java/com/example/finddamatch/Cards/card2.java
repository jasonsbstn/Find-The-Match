package com.example.finddamatch.Cards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.finddamatch.Classes.cards;
import com.example.finddamatch.R;

public class card2 extends AppCompatActivity {


    private ImageView pic1;
    private ImageView pic7;
    private ImageView pic4;
    String pics[] = {"pic1","pic7","pic4"};
    public static cards card2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        card2 =new cards(pics,2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card2);
    }
}
