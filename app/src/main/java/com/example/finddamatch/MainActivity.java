/**
 * Activity for welcome screen
 */

package com.example.finddamatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.finddamatch.Classes.Cards;
import com.example.finddamatch.Classes.Discard;
import com.example.finddamatch.UI.Main_Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.finddamatch.Flickr_and_Import.Photo_Gallery_Fragment.imageSelected;

public class MainActivity extends AppCompatActivity {

    public static com.example.finddamatch.Classes.Deck Deck;
    public static Cards hand;
    public static Discard top;
    public static int option;
    public static int order;
    public static int length;
    public static int mode;
    public static List<Bitmap> bitmaps = new ArrayList<Bitmap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        option = 1;
        SharedPreferences prefs = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        option = prefs.getInt("ThemeNum", 1);

        order = 2;
        SharedPreferences orders = getSharedPreferences("Orders", MODE_PRIVATE);
        order = orders.getInt("Orders", 2);

        length = 5;
        SharedPreferences lengths = getSharedPreferences("gameLength", MODE_PRIVATE);
        length = lengths.getInt("gameLength", 5);

        mode =1;
        SharedPreferences modes = getSharedPreferences("modes",MODE_PRIVATE);
        mode= modes.getInt("modes",1);

        bitmaps=getBitmaps();

        setContentView(R.layout.activity_main);

        shakeAnimation();
        setSkipButton();
    }

    //get saved list of bitmaps
    private List<Bitmap> getBitmaps() {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        int length;

        //get number of pictures saved
        SharedPreferences prefs = getSharedPreferences("numberPics", MODE_PRIVATE);
        length= prefs.getInt("numberPics", 0);

        //get all images
        for(int i=0;i<length;i++) {
            Bitmap temp;
            //retrive bitmap of picture one by one by decode the base64
            //REFERENCE: https://stackoverflow.com/questions/17268519/how-to-store-bitmap-object-in-sharedpreferences-in-android
            SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
            String previouslyEncodedImage = shre.getString("image_data"+i, "");

            if( !previouslyEncodedImage.equalsIgnoreCase("") ){
                byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
                temp = BitmapFactory.decodeByteArray(b, 0, b.length);
                bitmaps.add(temp);
            }
        }
        return bitmaps;
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