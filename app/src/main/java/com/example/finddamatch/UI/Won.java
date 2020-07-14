package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.finddamatch.UI.GameActivity.score;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finddamatch.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Won extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        SharedPreferences preferences = getSharedPreferences("highScore", 0);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("Sixth",score);
        editor.apply();

        SharedPreferences date=getSharedPreferences("date",0);
        SharedPreferences.Editor dateEdit = date.edit();

        //https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android#:~:text=on%20this%20post.-,Date%20c%20%3D%20Calendar.getInstance().getTime()%3B%20System.,%3D%20df.format(c)%3B
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        Log.d("haz",formattedDate);
        dateEdit.putString("date 6",formattedDate);
        dateEdit.apply();

        TextView scoreView= findViewById(R.id.winText);
        scoreView.setText("Congratulations, you won! Your time is "+(float)score/1000+" ms.");

    }
}