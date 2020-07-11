package com.example.finddamatch.Cards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finddamatch.Classes.cards;
import com.example.finddamatch.R;

import static com.example.finddamatch.MainActivity.Deck;
import static com.example.finddamatch.MainActivity.top;

public class card3 extends AppCompatActivity {
    private ImageView pic1;
    private ImageView pic6;
    private ImageView pic5;
    String pics[] = {"pic1","pic6","pic5"};
    public static cards card3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card3);
        pic1 = findViewById(R.id.pic1);
        pic6 = findViewById(R.id.pic6);
        pic5 = findViewById(R.id.pic5);
        card3 =new cards(pics,3);
        checkImage();

    }
    public void checkImage(){
        pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic1")){
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        }); pic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic6")) {
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        }); pic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic5")) {
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        });
    }
}
