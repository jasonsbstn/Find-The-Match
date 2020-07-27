package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import static com.example.finddamatch.MainActivity.option;

/*

Description : user chooses the theme

 */
import com.example.finddamatch.R;
import com.example.finddamatch.flickr.PhotoGalleryActivity;

public class Options_Screen extends AppCompatActivity {
    Button searchBtn;
    Button editFlickrPhoto;
    Button clearFlickrPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options__screen);
        searchBtn= findViewById(R.id.searchBtn);
        editFlickrPhoto = findViewById(R.id.editBtn);
        clearFlickrPhoto=findViewById(R.id.clearBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Options_Screen.this,PhotoGalleryActivity.class);
                startActivity(intent);
            }
        });
        editFlickrPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_Screen.this,flickrImgClass.class);
                startActivity(intent);
            }
        });
        createRadioButton();
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


}
