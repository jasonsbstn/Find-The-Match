package com.example.finddamatch.UI;

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

  /*  private int NUM_OF_ROWS = 3;
    private int NUM_OF_COL = 1;
    private int RANDOM_NUMBER;
    private deck leftCardDeck = deck.getInstance();
    Button[][] buttons = new Button[NUM_OF_ROWS][NUM_OF_COL];*/
    gameView gameView;
    public static int score;
    Boolean done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        done = false;
        gameView = new gameView(this);
        setContentView(gameView);


        //setContentView(R.layout.activity_game);
      /*  Random randomNum = new Random();
        RANDOM_NUMBER = randomNum.nextInt(7);
        setLeftCards();
        createCardGrid();
        mines(buttons);

        setRightCard();*/



    }

   /* private void setLeftCards() {
        String pics[] = {"1","2","3"};
        leftCardDeck.add(new cards(pics,1));
        String pics2[] = {"1","7","4"};
        leftCardDeck.add(new cards(pics2,2));
        String pics3[] = {"1","5","6"};
        leftCardDeck.add(new cards(pics3,3));
        String pics4[] = {"7","5","3"};
        leftCardDeck.add(new cards(pics4,4));
        String pics5[] = {"3","6","4"};
        leftCardDeck.add(new cards(pics5,5));
        String pics6[] = {"2","5","4"};
        leftCardDeck.add(new cards(pics6,6));
        String pics7[] = {"6","2","7"};
        leftCardDeck.add(new cards(pics7,7));
    }

    private void setRightCard() {
        cards rightCard;
        rightCard = leftCardDeck.getCards().get(RANDOM_NUMBER + 1);
        String [] pics = rightCard.images;
        String pic1 = pics[0];
        String pic2 = pics[1];
        String pic3 = pics[2];
        ImageView ig1 = (ImageView)findViewById(R.id.rc1);
        ImageView ig2 = (ImageView)findViewById(R.id.rc2);
        ImageView ig3 = (ImageView)findViewById(R.id.rc3);
        int first =Integer.parseInt(pic1);
        int second = Integer.parseInt(pic2);
        int third = Integer.parseInt(pic3);
        if(first == 1){ig1.setBackgroundResource(R.drawable.image1);}
        if(first == 2){ig1.setBackgroundResource(R.drawable.image2);}
        if(first == 3){ig1.setBackgroundResource(R.drawable.image3);}
        if(first == 4){ig1.setBackgroundResource(R.drawable.image4);}
        if(first == 5){ig1.setBackgroundResource(R.drawable.image5);}
        if(first == 6){ig1.setBackgroundResource(R.drawable.image6);}
        if(first == 7){ig1.setBackgroundResource(R.drawable.image7);}
        if(second == 1){ig2.setBackgroundResource(R.drawable.image1);}
        if(second == 2){ig2.setBackgroundResource(R.drawable.image2);}
        if(second == 3){ig2.setBackgroundResource(R.drawable.image3);}
        if(second == 4){ig2.setBackgroundResource(R.drawable.image4);}
        if(second == 5){ig2.setBackgroundResource(R.drawable.image5);}
        if(second == 6){ig2.setBackgroundResource(R.drawable.image6);}
        if(second == 7){ig2.setBackgroundResource(R.drawable.image7);}
        if(third == 1){ig3.setBackgroundResource(R.drawable.image1);}
        if(third == 2){ig3.setBackgroundResource(R.drawable.image2);}
        if(third == 3){ig3.setBackgroundResource(R.drawable.image3);}
        if(third == 4){ig3.setBackgroundResource(R.drawable.image4);}
        if(third == 5){ig3.setBackgroundResource(R.drawable.image5);}
        if(third == 6){ig3.setBackgroundResource(R.drawable.image6);}
        if(third == 7){ig3.setBackgroundResource(R.drawable.image7);}
    }

    private void createCardGrid() {
        TableLayout cardsGrid = findViewById(R.id.cardGrid);
        for (int row = 0 ; row < NUM_OF_ROWS ; row++){
            TableRow tablerow = new TableRow(this);
            tablerow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            cardsGrid.addView(tablerow);

            for (int col = 0; col < NUM_OF_COL ; col++){
                final int finalRow = row;
                final int finalCol = col;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                button.setPadding(0,0,0,0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //buttonClicked(finalRow,finalCol);
                    }
                });
                tablerow.addView(button);
                buttons[row][col] = button;

            }
        }
    }
    private void mines(final Button[][] butt){

        final Button b1 = butt[0][0];
        final Button b2 = butt[1][0];
        final Button b3 = butt[2][0];
        lockButtonSizes();
        int newWidth = 45;
        int newHeight = 45;
        Bitmap ogB1 = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        Bitmap ogB2 = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
        Bitmap ogB3 = BitmapFactory.decodeResource(getResources(), R.drawable.image3);
        Bitmap ogB4 = BitmapFactory.decodeResource(getResources(), R.drawable.image4);
        Bitmap ogB5 = BitmapFactory.decodeResource(getResources(), R.drawable.image5);
        Bitmap ogB6 = BitmapFactory.decodeResource(getResources(), R.drawable.image6);
        Bitmap ogB7 = BitmapFactory.decodeResource(getResources(), R.drawable.image7);
        final Bitmap scaledBitmap1 = Bitmap.createScaledBitmap(ogB1, newWidth, newHeight, true);
        final Bitmap scaledBitmap2 = Bitmap.createScaledBitmap(ogB2, newWidth, newHeight, true);
        final Bitmap scaledBitmap3 = Bitmap.createScaledBitmap(ogB3, newWidth, newHeight, true);
        final Bitmap scaledBitmap4 = Bitmap.createScaledBitmap(ogB4, newWidth, newHeight, true);
        final Bitmap scaledBitmap5 = Bitmap.createScaledBitmap(ogB5, newWidth, newHeight, true);
        final Bitmap scaledBitmap6 = Bitmap.createScaledBitmap(ogB6, newWidth, newHeight, true);
        final Bitmap scaledBitmap7 = Bitmap.createScaledBitmap(ogB7, newWidth, newHeight, true);
        final Resources resource = getResources();
        cards selectedCard = leftCardDeck.getCards().get((RANDOM_NUMBER + 2)%7);
        String [] pics = selectedCard.images;
        String pic1 = pics[0];
        String pic2 = pics[1];
        String pic3 = pics[2];
        int first =Integer.parseInt(pic1);
        int second = Integer.parseInt(pic2);
        int third = Integer.parseInt(pic3);
        //ImageButton ig = (ImageButton) findViewById(R.id.imageButton1);
        //ig.setBackgroundResource(R.drawable.image1);
        if(first == 1){b1.setBackgroundResource(R.drawable.image1);}
        if(first == 2){b1.setBackgroundResource(R.drawable.image2);}
        if(first == 3){b1.setBackgroundResource(R.drawable.image3);}
        if(first == 4){b1.setBackgroundResource(R.drawable.image4);}
        if(first == 5){b1.setBackgroundResource(R.drawable.image5);}
        if(first == 6){b1.setBackgroundResource(R.drawable.image6);}
        if(first == 7){b1.setBackgroundResource(R.drawable.image7);}
        if(second == 1){b2.setBackgroundResource(R.drawable.image1);}
        if(second == 2){b2.setBackgroundResource(R.drawable.image2);}
        if(second == 3){b2.setBackgroundResource(R.drawable.image3);}
        if(second == 4){b2.setBackgroundResource(R.drawable.image4);}
        if(second == 5){b2.setBackgroundResource(R.drawable.image5);}
        if(second == 6){b2.setBackgroundResource(R.drawable.image6);}
        if(second == 7){b2.setBackgroundResource(R.drawable.image7);}
        if(third == 1){b3.setBackgroundResource(R.drawable.image1);}
        if(third == 2){b3.setBackgroundResource(R.drawable.image2);}
        if(third == 3){b3.setBackgroundResource(R.drawable.image3);}
        if(third == 4){b3.setBackgroundResource(R.drawable.image4);}
        if(third == 5){b3.setBackgroundResource(R.drawable.image5);}
        if(third == 6){b3.setBackgroundResource(R.drawable.image6);}
        if(third == 7){b3.setBackgroundResource(R.drawable.image7);}
    }

    private void lockButtonSizes(){
        for (int row = 0 ; row < NUM_OF_ROWS;row ++){
            for (int col = 0; col < NUM_OF_COL; col++){
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }*/
    public static Intent makeLaunchIntent(Context c){
        Intent intent = new Intent(c, GameActivity.class);
        return intent;
    }
}