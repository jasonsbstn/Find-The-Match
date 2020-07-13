package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finddamatch.Classes.deck;
import com.example.finddamatch.R;

import static com.example.finddamatch.MainActivity.Deck;
import static com.example.finddamatch.MainActivity.option;

public class Main_Menu extends AppCompatActivity {
   // gameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);

        Button HelpButton = (Button) findViewById(R.id.helpButton);
        HelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HelpScreen = Help_Screen.makeLaunchIntent(Main_Menu.this);
                startActivity(HelpScreen);
            }
        });

        Button OptionButton = (Button) findViewById(R.id.optionsButton);
        OptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent OptionScreen = Options_Screen.makeLaunchIntent(Main_Menu.this);
                startActivity(OptionScreen);
            }
        });


        Button PlayButton = (Button) findViewById(R.id.playButton);
        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deck = new deck();
                Deck.startGame();
                Intent PlayScreen = GameActivity.makeLaunchIntent(Main_Menu.this);
                startActivity(PlayScreen);
            }

        });

        Button HighButton=(Button) findViewById(R.id.high);
        HighButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HighScreen = HighScore.makeLaunchIntent(Main_Menu.this);
                startActivity(HighScreen);
            }

        });
    }

    public static Intent makeLaunchIntent(Context c){
        Intent intent = new Intent(c, Main_Menu.class);
        return intent;
    }
}