package com.example.finddamatch.Cards;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.finddamatch.MainActivity.Deck;
import static com.example.finddamatch.MainActivity.option;
import static com.example.finddamatch.MainActivity.top;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finddamatch.R;

public class card1 extends AppCompatActivity {
    private ImageView pic1;
    private ImageView pic2;
    private ImageView pic3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card1);
        pic1 = findViewById(R.id.pic1);
        pic2 = findViewById(R.id.pic2);
        pic3 = findViewById(R.id.pic3);
        setImage();
        checkImage();

    }

    private void setImage() {
        if (option == 1) {
            pic1.setImageResource(R.drawable.image1);
            pic2.setImageResource(R.drawable.image2);
            pic3.setImageResource(R.drawable.image3);
        } else {
            pic1.setImageResource(R.drawable.image8);
            pic2.setImageResource(R.drawable.image9);
            pic3.setImageResource(R.drawable.image10);
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
        }); pic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic3")) {
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        }); pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(top.contain("pic2")){
                    Deck.discard();
                    Deck.draw();
                    finish();
                }
            }
        });
    }
}
