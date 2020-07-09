package com.example.finddamatch.Cards;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.finddamatch.MainActivity.Deck;
import static com.example.finddamatch.MainActivity.top;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finddamatch.R;

public class card7 extends AppCompatActivity {
    private ImageView pic6;
    private ImageView pic2;
    private ImageView pic7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card7);
        pic6 = findViewById(R.id.pic6);
        pic2 = findViewById(R.id.pic2);
        pic7 = findViewById(R.id.pic7);
        checkImage();

    }
    public void checkImage(){
        pic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic6")){
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
        pic7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic7")){
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        });
    }
}
