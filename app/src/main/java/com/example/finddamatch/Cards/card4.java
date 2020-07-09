package com.example.finddamatch.Cards;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.finddamatch.MainActivity.Deck;
import static com.example.finddamatch.MainActivity.top;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finddamatch.Classes.cards;
import com.example.finddamatch.R;

public class card4 extends AppCompatActivity {
    private ImageView pic3;
    private ImageView pic7;
    private ImageView pic5;
    public static cards card4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card4);
        pic3 = findViewById(R.id.pic3);
        pic7 = findViewById(R.id.pic7);
        pic5 = findViewById(R.id.pic5);
        checkImage();

    }
    public void checkImage(){
        pic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic3")){
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        }); pic7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic7")){
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        }); pic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic5")){
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        });
    }
}
