package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finddamatch.R;

public class HighScore extends AppCompatActivity {

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, HighScore.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        TextView score = (TextView) findViewById(R.id.scoreView);

        int first,second,third,fourth,fifth,sixth;

        SharedPreferences preferences =getSharedPreferences("highScore", 0);
        first=preferences.getInt("First",0);
        second=preferences.getInt("Second",0);
        third=preferences.getInt("Third",0);
        fourth=preferences.getInt("Fourth",0);
        fifth=preferences.getInt("Fifth",0);
        sixth=preferences.getInt("Sixth",0);

        if(sixth<fifth) {
            SharedPreferences.Editor editor=preferences.edit();
            editor.putInt("Fifth",sixth);
            editor.apply();
        }
        if(sixth<fourth) {
            SharedPreferences.Editor editor=preferences.edit();
            editor.putInt("Fifth",fourth);
            editor.putInt("Fourth",sixth);
            editor.apply();
        }
        if(sixth<third) {
            SharedPreferences.Editor editor=preferences.edit();
            editor.putInt("Fifth",fourth);
            editor.putInt("Fourth",third);
            editor.putInt("Third",sixth);
            editor.apply();
        }
        if(sixth<second) {
            SharedPreferences.Editor editor=preferences.edit();
            editor.putInt("Fifth",fourth);
            editor.putInt("Fourth",third);
            editor.putInt("Third",second);
            editor.putInt("Second",sixth);
            editor.apply();
        }
        if(sixth<fifth) {
            SharedPreferences.Editor editor=preferences.edit();
            editor.putInt("Fifth",fourth);
            editor.putInt("Fourth",third);
            editor.putInt("Third",second);
            editor.putInt("Second",first);
            editor.putInt("First",sixth);
            editor.apply();
        }

        score.setText("First: "+first+"\n"+
                "Second: "+second+"\n"+
                "Third: "+third+"\n"+
                "Fourth: "+fourth+"\n"+
                "Fifth: "+fifth+"\n");
    }
}