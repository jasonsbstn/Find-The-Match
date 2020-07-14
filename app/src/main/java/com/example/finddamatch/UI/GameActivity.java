package com.example.finddamatch.UI;
/*
    description : starts the gameView
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.finddamatch.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {


    gameView gameView;
    public static int score;
    Boolean done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        done = false;
        gameView = new gameView(this);
        setContentView(gameView);
    }


    public static Intent makeLaunchIntent(Context c){
        Intent intent = new Intent(c, GameActivity.class);
        return intent;
    }
}