package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.finddamatch.UI.GameActivity.score;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finddamatch.R;

public class Won extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        SharedPreferences preferences = getSharedPreferences("highScore", 0);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("Sixth",score);
        editor.apply();

        TextView scoreView= findViewById(R.id.winText);
        scoreView.setText("Congratulations, you won! Your time is "+(float)score/1000+" ms.");

    }
}