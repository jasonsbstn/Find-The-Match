package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.finddamatch.UI.GameActivity.score;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        preferences= getSharedPreferences("highScore", 0);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("Sixth",score);
        editor.apply();

        SharedPreferences date=getSharedPreferences("date",0);
        SharedPreferences.Editor dateEdit = date.edit();

        //https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android#:~:text=on%20this%20post.-,Date%20c%20%3D%20Calendar.getInstance().getTime()%3B%20System.,%3D%20df.format(c)%3B
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.d("haz",formattedDate);
        dateEdit.putString("date 6",formattedDate);
        dateEdit.apply();

        TextView scoreView= findViewById(R.id.winText);
        scoreView.setText("Congratulations, you won! Your time is "+(float)score/1000+" ms.");
        updateUI();
    }

    @Override
    public void onBackPressed() {
        updateUI();
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("userName",userNameEntered.getText().toString() );
        super.onBackPressed();
    }

    private void updateUI(){
        userNameEntered = (EditText) findViewById(R.id.userNameEntered);
    }

}