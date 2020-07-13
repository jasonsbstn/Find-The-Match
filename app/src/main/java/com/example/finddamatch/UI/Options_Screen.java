package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import static com.example.finddamatch.MainActivity.option;


import com.example.finddamatch.R;

public class Options_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options__screen);
        createRadioButton();
        option = getSettings(this);
    }

    public static Intent makeLaunchIntent(Context c){
        Intent intent = new Intent(c, Options_Screen.class);
        return intent;
    }
    private void createRadioButton() {
        RadioGroup group = (RadioGroup) findViewById(R.id.collumnrowRadio);
        String[] themeOptions = getResources().getStringArray(R.array.themeselection);
        for(int i =0; i<themeOptions.length;i++)
        {
            final int optionNum = i+1;
            final String themeOptionSelection = themeOptions[i];
            RadioButton button = new RadioButton(this);
            button.setText(themeOptionSelection);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    option = optionNum;
                    saveTheme(optionNum);
                }
            });
            group.addView(button);
            if(optionNum == getSettings(this))
            {
                button.setChecked(true);
            }

        }
        //select default button


    }
    private void saveTheme(int optionNum) {
        SharedPreferences prefs = this.getSharedPreferences("ThemePrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("ThemeNum",optionNum);
        editor.apply();
    }
    static int getSettings(Context context){
        SharedPreferences prefs = context.getSharedPreferences("SizePrefs",MODE_PRIVATE);
        int  x;
        x= prefs.getInt("ThemeNum",1);
        return x;
    }


    public static Intent makeIntent(Context context){
        return new Intent(context, Options_Screen.class);
    }
}
