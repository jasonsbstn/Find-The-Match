package com.example.finddamatch.Cards;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.finddamatch.MainActivity.Deck;
import static com.example.finddamatch.MainActivity.option;
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
        setImage();
        checkImage();

    }
    private void setImage() {
        if (option == 1) {
            pic7.setImageResource(R.drawable.image7);
            pic5.setImageResource(R.drawable.image5);
            pic3.setImageResource(R.drawable.image3);
        } else {
            pic7.setImageResource(R.drawable.image14);
            pic5.setImageResource(R.drawable.image12);
            pic3.setImageResource(R.drawable.image10);
        }
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
