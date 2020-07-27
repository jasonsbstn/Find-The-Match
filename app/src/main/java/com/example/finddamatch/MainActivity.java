/**
 * Activity for welcome screen
 */

package com.example.finddamatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.finddamatch.Classes.cards;
import com.example.finddamatch.Classes.deck;
import com.example.finddamatch.Classes.discard;
import com.example.finddamatch.UI.Main_Menu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.CountDownTimer;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static deck Deck;
    public static cards hand;
    public static discard top;
    public static int option;
    public static int order;
    public static int length;
    public static List<Bitmap> bitmaps = new ArrayList<Bitmap>();
    public static int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        option =1;
        SharedPreferences prefs = getSharedPreferences("ThemePrefs",MODE_PRIVATE);
        option = prefs.getInt("ThemeNum",1);

        order = 2;
        SharedPreferences orders = getSharedPreferences("Orders",MODE_PRIVATE);
        order = orders.getInt("Orders",2);

        length =5;
        SharedPreferences lengths = getSharedPreferences("gameLength",MODE_PRIVATE);
        length = lengths.getInt("gameLength",5);

        setContentView(R.layout.activity_main);

        shakeAnimation();
        setSkipButton();
    }

    //set animation shake for title
    private void shakeAnimation() {
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        TextView textView = findViewById(R.id.splash_title);
        textView.setAnimation(animShake);
    }

    //skip after 5 seconds
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