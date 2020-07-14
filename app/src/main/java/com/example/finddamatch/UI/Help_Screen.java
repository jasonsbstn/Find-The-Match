/**
 * Activity for help screen
 */

package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.finddamatch.R;

public class Help_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__screen);

    }

    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, Help_Screen.class);
        return intent;
    }
}