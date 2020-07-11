package com.example.finddamatch.Cards;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.finddamatch.MainActivity.Deck;
import static com.example.finddamatch.MainActivity.top;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finddamatch.R;

public class card6 extends AppCompatActivity {
    private ImageView pic4;
    private ImageView pic2;
    private ImageView pic5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card6);
        pic4 = findViewById(R.id.pic4);
        pic2 = findViewById(R.id.pic2);
        pic5 = findViewById(R.id.pic5);
        checkImage();

    }
    public void checkImage(){
        pic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic4")){
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        });
        pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic2")){
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        });
        pic5.setOnClickListener(new View.OnClickListener() {
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
