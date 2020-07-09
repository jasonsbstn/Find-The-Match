package com.example.finddamatch.Cards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finddamatch.Classes.cards;
import com.example.finddamatch.R;

public class card3 extends AppCompatActivity {
    private ImageView pic1;
    private ImageView pic6;
    private ImageView pic5;
    String pics[] = {"pic1","pic6","pic5"};
    public static cards card1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        card1 =new cards(pics,2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card3);
    }
}
