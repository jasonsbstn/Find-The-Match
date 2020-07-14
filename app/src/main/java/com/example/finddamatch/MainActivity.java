package com.example.finddamatch;

import android.content.Intent;
import android.os.Bundle;

import com.example.finddamatch.Classes.cards;
import com.example.finddamatch.Classes.deck;
import com.example.finddamatch.Classes.discard;
import com.example.finddamatch.UI.Main_Menu;
import com.example.finddamatch.UI.gameView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.CountDownTimer;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static deck Deck;
    public static cards hand;
    public static discard top;
    public static int option;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        option =1;
        setContentView(R.layout.activity_main);
       /* gameView = new gameView(this);
        setContentView(gameView);*/
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        /**
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         */

        // testing push


        shakeAnimation();
        setSkipButton();
    }

    private void shakeAnimation() {
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        TextView textView = findViewById(R.id.splash_title);
        textView.setAnimation(animShake);
    }

    private void setSkipButton() {
        final CountDownTimer timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent i = Main_Menu.makeLaunchIntent(MainActivity.this);
                startActivity(i);
                finish();
            }
        }.start();

        Button btn = (Button) findViewById(R.id.skip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                Intent i = Main_Menu.makeLaunchIntent(MainActivity.this);
                startActivity(i);
                finish();
            }
        });
    }
}