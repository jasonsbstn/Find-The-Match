package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.finddamatch.Classes.cards;
import com.example.finddamatch.Classes.deck;
import com.example.finddamatch.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private int NUM_OF_ROWS = 3;
    private int NUM_OF_COL = 1;
    private deck leftCardDeck;
    Button[][] buttons = new Button[NUM_OF_ROWS][NUM_OF_COL];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        createCardGrid();
        setRightCard();
        setLeftCards();

    }

    private void setLeftCards() {
        String pics[] = {"pic1","pic2","pic3"};
        leftCardDeck.add(new cards(pics,1));
        String pics2[] = {"pic1","pic7","pic4"};
        leftCardDeck.add(new cards(pics2,2));
        String pics3[] = {"pic1","pic5","pic6"};
        leftCardDeck.add(new cards(pics3,3));
        String pics4[] = {"pic7","pic5","pic3"};
        leftCardDeck.add(new cards(pics4,4));
        String pics5[] = {"pic3","pic6","pic4"};
        leftCardDeck.add(new cards(pics5,5));
        String pics6[] = {"pic2","pic5","pic4"};
        leftCardDeck.add(new cards(pics6,6));
        String pics7[] = {"pic6","pic2","pic7"};
        leftCardDeck.add(new cards(pics7,7));
    }

    private void setRightCard() {
        Random rand = new Random();
        int randomCardNo;
        randomCardNo = rand.nextInt(7);
        cards rightCard;
        rightCard = leftCardDeck.getCards().get(randomCardNo + 1);
        String [] pics = rightCard.images;
        String pic1 = pics[0];
        String pic2 = pics[1];
        String pic3 = pics[2];
        Button butt1 = (Button) findViewById(R.id.button1);
        Button butt2 = (Button)findViewById(R.id.button2);
        Button butt3 = (Button) findViewById(R.id.button3);

    }

    private void createCardGrid() {
        TableLayout cardsGrid = (TableLayout)findViewById(R.id.cardGrid);
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
    }
}