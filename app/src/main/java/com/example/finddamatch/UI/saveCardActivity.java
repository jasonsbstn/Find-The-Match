package com.example.finddamatch.UI;
/*
    description : starts the gameView
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class saveCardActivity extends AppCompatActivity {


    exportCards export;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        export = new exportCards(this);
        setContentView(export);
    }


    public static Intent makeLaunchIntent(Context c){
        Intent intent = new Intent(c, saveCardActivity.class);
        return intent;
    }
}