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


        String name1, name2, name3, name4, name5, name6;

        SharedPreferences name = getSharedPreferences("date", 0);
        name1 = name.getString("name1", "Anonymous");
        name2 = name.getString("name2", "Anonymous");
        name3 = name.getString("name3", "Anonymous");
        name4 = name.getString("name4", "Anonymous");
        name5 = name.getString("name5", "Anonymous");
        name6 = name.getString("name6", "Anonymous");
        SharedPreferences.Editor nameEdit = name.edit();



        if (sixth < fifth) {
            fifth = sixth;
            editor.putInt("Fifth", fifth);
            editor.apply();

            date5 = date6;
            dateEdit.putString("date5", date5);
            dateEdit.apply();

            name5 = name6;
            nameEdit.putString("name5", name5);
            nameEdit.apply();
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

            String nameTemp = name4;
            name4 = name6;
            name5 = nameTemp;
            nameEdit.putString("name5", name5);
            nameEdit.putString("name4", name4);
            nameEdit.apply();
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

            String nameTemp = name3;
            name3 = name6;
            name4 = nameTemp;
            nameEdit.putString("name4", name4);
            nameEdit.putString("name3", name3);
            nameEdit.apply();
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

            String nameTemp = name2;
            name2 = name6;
            name3 = nameTemp;
            nameEdit.putString("name2", name2);
            nameEdit.putString("name3", name3);
            nameEdit.apply();
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

            String nameTemp = name1;
            name1 = name6;
            name2 = nameTemp;
            nameEdit.putString("name1", name1);
            nameEdit.putString("name2", name2);
            nameEdit.apply();
        }


        editor.putInt("Sixth", 60000);
        editor.apply();
        score.setText("1: " + (float) first / 1000 + " - " + date1 + " - " + name1 +" \n" +
                      "2: " + (float) second / 1000 + " - " + date2 + " - " + name2 +" \n" +
                      "3: " + (float) third / 1000 + " - " + date3 + " - " + name3 +" \n" +
                      "4: " + (float) fourth / 1000 + " - " + date4 + " - " + name4 +" \n" +
                      "5: " + (float) fifth / 1000 + " - " + date5 + " - " + name5 +" \n");
    }
}