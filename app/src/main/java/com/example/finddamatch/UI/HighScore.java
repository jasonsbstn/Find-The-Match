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

        int first, second, third, fourth, fifth, sixth;

        SharedPreferences preferences = getSharedPreferences("highScore", 0);
        first = preferences.getInt("First", 20000);
        second = preferences.getInt("Second", 20000);
        third = preferences.getInt("Third", 20000);
        fourth = preferences.getInt("Fourth", 20000);
        fifth = preferences.getInt("Fifth", 20000);
        sixth = preferences.getInt("Sixth", 20000);

        SharedPreferences.Editor editor = preferences.edit();
        if (sixth < fifth) {
            fifth = sixth;
            editor.putInt("Fifth", fifth);
            editor.apply();
        }
        if (sixth < fourth) {
            int temp = fourth;
            fourth = sixth;
            fifth = temp;
            editor.putInt("Fifth", fifth);
            editor.putInt("Fourth", fourth);
            editor.apply();
        }
        if (sixth < third) {
            int temp = third;
            third = sixth;
            fourth = temp;
            editor.putInt("Third", third);
            editor.putInt("Fourth", fourth);
            editor.apply();
        }
        if (sixth < second) {
            int temp = second;
            second = sixth;
            third = temp;
            editor.putInt("Second", second);
            editor.putInt("Third", third);
            editor.apply();
        }
        if (sixth < first) {
            int temp = first;
            first = sixth;
            second = temp;
            editor.putInt("First", first);
            editor.putInt("Second", second);
            editor.apply();
        }

        editor.putInt("Sixth", 60000);
        editor.apply();
        score.setText("First: " + (float) first/1000 + "\n" +
                "Second: " + (float) second/1000 + "\n" +
                "Third: " + (float) third/1000 + "\n" +
                "Fourth: " + (float) fourth/1000 + "\n" +
                "Fifth: " + (float) fifth/1000 + "\n");
    }
}