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

public class card5 extends AppCompatActivity {
    private ImageView pic6;
    private ImageView pic3;
    private ImageView pic4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card5);
        pic6 = findViewById(R.id.pic6);
        pic3 = findViewById(R.id.pic3);
        pic4 = findViewById(R.id.pic4);
        setImage();
        checkImage();

    }
    private void setImage() {
        if (option == 1) {
            pic6.setImageResource(R.drawable.image6);
            pic4.setImageResource(R.drawable.image4);
            pic3.setImageResource(R.drawable.image3);
        } else {
            pic6.setImageResource(R.drawable.image13);
            pic4.setImageResource(R.drawable.image11);
            pic3.setImageResource(R.drawable.image10);
        }
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
        }); pic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic3")){
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        }); pic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic4")){
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        });
    }
}
