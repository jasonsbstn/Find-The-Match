package com.example.finddamatch.UI;
/*
    description : starts the gameView
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Game_Activity extends AppCompatActivity {


    Game_View gameView;
    public static int score;
    Boolean done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        done = false;
        gameView = new Game_View(this);
        setContentView(gameView);
    }


    public static Intent makeLaunchIntent(Context c){
        Intent intent = new Intent(c, Game_Activity.class);
        return intent;
    }
}