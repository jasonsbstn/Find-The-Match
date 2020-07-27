package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.finddamatch.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.finddamatch.MainActivity.bitmaps;

public class flickrImgClass extends AppCompatActivity {
    GridView gridView;
    public static List<Integer> toBeDeleted;
    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_img_class);
        toBeDeleted=new ArrayList<>();
        gridView=findViewById(R.id.grid_view);
        saveBtn=findViewById(R.id.deleteBtn);
        imageAdapter adapter = new imageAdapter(flickrImgClass.this);
        gridView.setAdapter(adapter);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toBeDeleted.isEmpty()==false)
                {
                   for(int i =0; i<toBeDeleted.size();i++) {
                       int index = toBeDeleted.get(i);
                       bitmaps.remove(index);
                   }
                }
                finish();
            }
        });

    }
}