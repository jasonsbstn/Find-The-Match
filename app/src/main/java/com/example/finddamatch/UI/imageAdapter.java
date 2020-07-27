package com.example.finddamatch.UI;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.graphics.drawable.BitmapDrawable;

import com.example.finddamatch.R;

import static com.example.finddamatch.MainActivity.bitmaps;
import static com.example.finddamatch.UI.flickrImgClass.toBeDeleted;

public class imageAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    public imageAdapter(Context c)
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
