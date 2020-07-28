/*
Description: Grid view adapter to display images
based on :https://www.youtube.com/watch?v=2gbPOEH7cQ8
 */
package com.example.finddamatch.UI;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.finddamatch.R;

import static com.example.finddamatch.MainActivity.bitmaps;
import static com.example.finddamatch.UI.Flickr_Img_Class.toBeDeleted;

public class Image_Adapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    public Image_Adapter(Context c)
    {
        context = c;
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.row_image,null);
        }
        final ImageView imageView=convertView.findViewById(R.id.imageFlickrView);
        imageView.setImageBitmap(bitmaps.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable highlight = imageView.getResources().getDrawable(R.drawable.highlight);
                imageView.setBackground(highlight);
                toBeDeleted.add(position);
                imageView.setEnabled(false);//prevents second click
            }
        });
        return convertView;
    }
}
