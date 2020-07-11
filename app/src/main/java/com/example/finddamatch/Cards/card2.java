package com.example.finddamatch.Cards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finddamatch.Classes.cards;
import com.example.finddamatch.MainActivity;
import com.example.finddamatch.R;

import static com.example.finddamatch.MainActivity.Deck;
import static com.example.finddamatch.MainActivity.option;
import static com.example.finddamatch.MainActivity.top;

public class card2 extends AppCompatActivity {


    private ImageView pic1;
    private ImageView pic7;
    private ImageView pic4;
    String pics[] = {"pic1","pic7","pic4"};
    public static cards card2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card2);
        pic1 = findViewById(R.id.pic1);
        pic7 = findViewById(R.id.pic7);
        pic4 = findViewById(R.id.pic4);
        card2 =new cards(pics,2);
        setImage();
        checkImage();


    }
    private void setImage() {
        if (option == 1) {
            pic1.setImageResource(R.drawable.image1);
            pic7.setImageResource(R.drawable.image7);
            pic4.setImageResource(R.drawable.image4);
        } else {
            pic1.setImageResource(R.drawable.image8);
            pic7.setImageResource(R.drawable.image14);
            pic4.setImageResource(R.drawable.image11);
        }
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
    }
}
