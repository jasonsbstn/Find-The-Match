package com.example.finddamatch.Cards;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.finddamatch.MainActivity.top;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finddamatch.Classes.cards;
import com.example.finddamatch.R;

public class card1 extends AppCompatActivity {
    private ImageView pic1;
    private ImageView pic2;
    private ImageView pic3;
    String pics[] = {"pic1","pic2","pic3"};
    public static cards card1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card1);
        pic1 = findViewById(R.id.pic1);
        pic2 = findViewById(R.id.pic2);
        pic3 = findViewById(R.id.pic3);
        card1 =new cards(pics,1);
        pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top.contain("pic1");
            }
        }); pic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top.contain("pic3");
            }
        }); pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top.contain("pic2");
            }
        });
    }
}
