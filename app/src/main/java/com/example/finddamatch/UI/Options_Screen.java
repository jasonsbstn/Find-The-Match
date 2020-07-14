package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import static com.example.finddamatch.MainActivity.option;


import com.example.finddamatch.R;

public class Options_Screen extends AppCompatActivity {
    int currentOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options__screen);
        createRadioButton();
       //option = getSettings(this);
        //currentOption=option;
    }

    public static Intent makeLaunchIntent(Context c){
        Intent intent = new Intent(c, Options_Screen.class);
        return intent;
    }
    private void createRadioButton() {
        RadioGroup group = (RadioGroup) findViewById(R.id.collumnrowRadio);
        String[] themeOptions = getResources().getStringArray(R.array.themeselection);
        RadioButton button1 = new RadioButton(this);
        RadioButton button2 = new RadioButton(this);
        button1.setText(themeOptions[0]);
        button2.setText(themeOptions[1]);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option= 1;
                saveTheme(option);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option= 2;
                saveTheme(option);
            }
        });
        group.addView(button1);
        group.addView(button2);
        if(option == 1)
        {
            button1.setChecked(true);
        }
        else if(option ==2)
        {
            button2.setChecked(true);
        }



    }
    private void saveTheme(int optionNum) {
        SharedPreferences prefs = this.getSharedPreferences("ThemePrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("ThemeNum",optionNum);
        editor.apply();
    }
    static int getSettings(Context context){
        SharedPreferences prefs = context.getSharedPreferences("ThemePrefs",MODE_PRIVATE);
        int  x;
        x= prefs.getInt("ThemeNum",1);
        return x;
    }


    public static Intent makeIntent(Context context){
        return new Intent(context, Options_Screen.class);
    }
}
