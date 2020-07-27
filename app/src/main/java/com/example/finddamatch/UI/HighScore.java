/**
 * Activity for high score screen
 */

package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finddamatch.R;
import static com.example.finddamatch.MainActivity.order;
import static com.example.finddamatch.MainActivity.length;

public class HighScore extends AppCompatActivity {

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, HighScore.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        if(order==2) {
            if(length==5) {
                createHighScore("highScore_2_5", "date_2_5", "name_2_5",17500);
            }
            else if(length==7) {
                createHighScore("highScore_2_7", "date_2_7", "name_2_7",20000);
            }
        }
        else if(order==3) {
            if(length==5) {
                createHighScore("highScore_3_5", "date_3_5", "name_3_5",30000);
            }
            else if(length==10) {
                createHighScore("highScore_3_10", "date_3_10", "name_3_10",35000);
            }
            else if(length==13) {
                createHighScore("highScore_3_13", "date_3_13", "name_3_13",40000);
            }
        }
        else if(order==5) {
            if(length==5) {
                createHighScore("highScore_5_5", "date_5_5", "name_5_5",80000);
            }
            else if(length==10) {
                createHighScore("highScore_5_10", "date_5_10", "name_5_10",85000);
            }
            else if(length==15) {
                createHighScore("highScore_5_15", "date_5_15", "name_5_15",90000);
            }
            else if(length==20) {
                createHighScore("highScore_5_20", "date_5_20", "name_5_20",95000);
            }
            else if(length==31) {
                createHighScore("highScore_5_31", "date_5_31", "name_5_31",100000);
            }
        }
    }

    private void createHighScore(String scores, String dates, String names, int def) {
        TextView score = (TextView) findViewById(R.id.scoreView);

        //get high scores (defValue is default value)
        int first, second, third, fourth, fifth, sixth;

        SharedPreferences preferences = getSharedPreferences(scores, 0);
        first = preferences.getInt("First", def);
        second = preferences.getInt("Second", def);
        third = preferences.getInt("Third", def);
        fourth = preferences.getInt("Fourth", def);
        fifth = preferences.getInt("Fifth", def);
        sixth = preferences.getInt("Sixth", def);
        SharedPreferences.Editor editor = preferences.edit();


        //get high scores corresponding date
        String date1, date2, date3, date4, date5, date6;

        SharedPreferences date = getSharedPreferences(dates, 0);
        date1 = date.getString("date1", "10-Jul-2020");
        date2 = date.getString("date2", "10-Jul-2020");
        date3 = date.getString("date3", "10-Jul-2020");
        date4 = date.getString("date4", "10-Jul-2020");
        date5 = date.getString("date5", "10-Jul-2020");
        date6 = date.getString("date6", "10-Jul-2020");
        SharedPreferences.Editor dateEdit = date.edit();


        //get high scores corresponding username
        String name1, name2, name3, name4, name5, name6;

        SharedPreferences name = getSharedPreferences(names, 0);
        name1 = name.getString("name1", "Anonymous");
        name2 = name.getString("name2", "Anonymous");
        name3 = name.getString("name3", "Anonymous");
        name4 = name.getString("name4", "Anonymous");
        name5 = name.getString("name5", "Anonymous");
        name6 = name.getString("name6", "Anonymous");
        SharedPreferences.Editor nameEdit = name.edit();


        //update list if high score is change
        if (sixth < fifth) {

            //update score
            fifth = sixth;
            editor.putInt("Fifth", fifth);
            editor.apply();

            //update corresponding date
            date5 = date6;
            dateEdit.putString("date5", date5);
            dateEdit.apply();

            //update corresponding name
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


        //reset score
        editor.putInt("Sixth", 60000);
        editor.apply();

        //print out value
        score.setText("1: " + (float) first / 1000 + " - " + date1 + " - " + name1 + " \n" +
                "2: " + (float) second / 1000 + " - " + date2 + " - " + name2 + " \n" +
                "3: " + (float) third / 1000 + " - " + date3 + " - " + name3 + " \n" +
                "4: " + (float) fourth / 1000 + " - " + date4 + " - " + name4 + " \n" +
                "5: " + (float) fifth / 1000 + " - " + date5 + " - " + name5 + " \n");
    }
}