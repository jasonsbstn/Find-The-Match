package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finddamatch.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

        String date1, date2, date3, date4, date5, date6;

        SharedPreferences date = getSharedPreferences("date", 0);
        date1 = date.getString("date1", "10-Jul-2020");
        date2 = date.getString("date2", "10-Jul-2020");
        date3 = date.getString("date3", "10-Jul-2020");
        date4 = date.getString("date4", "10-Jul-2020");
        date5 = date.getString("date5", "10-Jul-2020");
        date6 = date.getString("date6", "10-Jul-2020");
        SharedPreferences.Editor dateEdit = date.edit();

        if (sixth < fifth) {
            fifth = sixth;
            editor.putInt("Fifth", fifth);
            editor.apply();

            date5 = date6;
            dateEdit.putString("date5", date5);
            dateEdit.apply();
        }
        if (sixth < fourth) {
            int temp = fourth;
            fourth = sixth;
            fifth = temp;
            editor.putInt("Fifth", fifth);
            editor.putInt("Fourth", fourth);
            editor.apply();

            String dateTemp = date4;
            date4 = date6;
            date5 = dateTemp;
            dateEdit.putString("date5", date5);
            dateEdit.putString("date4", date4);
            dateEdit.apply();
        }
        if (sixth < third) {
            int temp = third;
            third = sixth;
            fourth = temp;
            editor.putInt("Third", third);
            editor.putInt("Fourth", fourth);
            editor.apply();

            String dateTemp = date3;
            date3 = date6;
            date4 = dateTemp;
            dateEdit.putString("date4", date4);
            dateEdit.putString("date3", date3);
            dateEdit.apply();
        }
        if (sixth < second) {
            int temp = second;
            second = sixth;
            third = temp;
            editor.putInt("Second", second);
            editor.putInt("Third", third);
            editor.apply();

            String dateTemp = date2;
            date2 = date6;
            date3 = dateTemp;
            dateEdit.putString("date2", date2);
            dateEdit.putString("date3", date3);
            dateEdit.apply();
        }
        if (sixth < first) {
            int temp = first;
            first = sixth;
            second = temp;
            editor.putInt("First", first);
            editor.putInt("Second", second);
            editor.apply();

            String dateTemp = date4;
            date1 = date6;
            date2 = dateTemp;
            dateEdit.putString("date1", date1);
            dateEdit.putString("date2", date2);
            dateEdit.apply();
        }


        editor.putInt("Sixth", 60000);
        editor.apply();
        score.setText("First: " + (float) first / 1000 + " - " + date1 + " " + "\n" +
                "Second: " + (float) second / 1000 + " - " + date2 + " " + "\n" +
                "Third: " + (float) third / 1000 + " - " + date3 + " " + "\n" +
                "Fourth: " + (float) fourth / 1000 + " - " + date4 + " " + "\n" +
                "Fifth: " + (float) fifth / 1000 + " - " + date5 + " " + "\n");
    }
}