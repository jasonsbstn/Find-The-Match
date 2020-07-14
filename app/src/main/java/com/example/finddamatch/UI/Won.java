/**
 * Activity for winning screen
 */

package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.finddamatch.UI.GameActivity.score;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finddamatch.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Won extends AppCompatActivity {
    private EditText userNameEntered;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        //save score
        preferences = getSharedPreferences("highScore", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Sixth", score);
        editor.apply();

        //save date
        SharedPreferences date = getSharedPreferences("date", 0);
        SharedPreferences.Editor dateEdit = date.edit();

        //From:
        //https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android#:~:text=on%20
        //this%20post.-,Date%20c%20%3D%20Calendar.getInstance().getTime()%3B%20System.,%3D%20df.format(c)%3B
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        dateEdit.putString("date6", formattedDate);
        dateEdit.apply();


        //set winning text
        TextView scoreView = findViewById(R.id.winText);
        scoreView.setText("Congratulations, you won! Your time is " + (float) score / 1000 + " s.");
        updateUI();
    }

    @Override
    public void onBackPressed() {
        updateUI();

        //save username
        SharedPreferences name = getSharedPreferences("name", 0);
        SharedPreferences.Editor nameEdit = name.edit();
        nameEdit.putString("name6", userNameEntered.getText().toString());
        nameEdit.apply();
        super.onBackPressed();
    }

    private void updateUI() {
        userNameEntered = (EditText) findViewById(R.id.userNameEntered);
    }

}