package com.example.finddamatch.UI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.example.finddamatch.R;

import static android.content.ContentValues.TAG;
import static com.example.finddamatch.MainActivity.Deck;
import static com.example.finddamatch.MainActivity.hand;
import static com.example.finddamatch.MainActivity.option;
import static com.example.finddamatch.MainActivity.top;

public class gameView extends SurfaceView {
    private Bitmap[]  pic = new Bitmap[6];
    private Bitmap[]  picScaled = new Bitmap[6];
    private Bitmap cardBackground;
    private Bitmap scaledBackground;
    private SurfaceHolder holder;
    private float canvasXSize;
    private float canvasYSize;
    String[] card1;
    String[] card2;
    Canvas c;
    public gameView(Context context) {//https://www.youtube.com/watch?v=3V5aV-iM8YA&t=7s
        super(context);
        holder = getHolder();
        setImages();
        cardBackground = BitmapFactory.decodeResource(getResources(), R.drawable.cardbackground);
        holder.addCallback(new SurfaceHolder.Callback() {
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                c = holder.lockCanvas();
                onDraw(c);
                holder.unlockCanvasAndPost(c);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }

    public void setImages() {
        card1= hand.getCards();
        card2 = top.getCards();
        Log.d(TAG, "setImages: "+ card1[0] + card2[0]);
        if (option == 1) {
            for (int i = 0; i < card1.length; i++) {
                if (card1[i] == "pic1")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
                else if (card1[i] == "pic2")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
                else if (card1[i] == "pic3")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image3);
                else if (card1[i] == "pic4")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image4);
                else if (card1[i] == "pic5")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image5);
                else if (card1[i] == "pic6")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image6);
                else if (card1[i] == "pic7")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image7);
            }
            for (int j = 0; j < card2.length; j++) {
                if (card2[j] == "pic1")
                    pic[j+3] = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
                else if (card2[j] == "pic2")
                    pic[j+3] = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
                else if (card2[j] == "pic3")
                    pic[j+3] = BitmapFactory.decodeResource(getResources(), R.drawable.image3);
                else if (card2[j] == "pic4")
                    pic[j+3] = BitmapFactory.decodeResource(getResources(), R.drawable.image4);
                else if (card2[j] == "pic5")
                    pic[j+3] = BitmapFactory.decodeResource(getResources(), R.drawable.image5);
                else if (card2[j] == "pic6")
                    pic[j+3] = BitmapFactory.decodeResource(getResources(), R.drawable.image6);
                else if (card2[j] == "pic7")
                    pic[j+3] = BitmapFactory.decodeResource(getResources(), R.drawable.image7);
            }
        }
        else if (option == 2) {
            for (int i = 0; i < card1.length; i++) {
                if (card1[i] == "pic1")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image8);
                else if (card1[i] == "pic2")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image9);
                else if (card1[i] == "pic3")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image10);
                else if (card1[i] == "pic4")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image11);
                else if (card1[i] == "pic5")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image12);
                else if (card1[i] == "pic6")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image13);
                else if (card1[i] == "pic7")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.image14);
            }
            for (int j = 0; j < card2.length; j++) {
                if (card2[j] == "pic1")
                    pic[j] = BitmapFactory.decodeResource(getResources(), R.drawable.image8);
                else if (card2[j] == "pic2")
                    pic[j] = BitmapFactory.decodeResource(getResources(), R.drawable.image9);
                else if (card2[j] == "pic3")
                    pic[j] = BitmapFactory.decodeResource(getResources(), R.drawable.image10);
                else if (card2[j] == "pic4")
                    pic[j] = BitmapFactory.decodeResource(getResources(), R.drawable.image11);
                else if (card2[j] == "pic5")
                    pic[j] = BitmapFactory.decodeResource(getResources(), R.drawable.image12);
                else if (card2[j] == "pic6")
                    pic[j] = BitmapFactory.decodeResource(getResources(), R.drawable.image13);
                else if (card2[j] == "pic7")
                    pic[j] = BitmapFactory.decodeResource(getResources(), R.drawable.image14);
            }
        }
        for(int i=0 ; i<card1.length;i++)
        {
            picScaled[i]=Bitmap.createScaledBitmap(pic[i], 300, 300, true);
            picScaled[i+3]=Bitmap.createScaledBitmap(pic[i+3], 300, 300, true);//https://stackoverflow.com/questions/27466099/how-to-resize-bitmap-when-drawing-in-canvas
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*if(top.getCards()==hand.getCards())
            finish();*/ //ends activity
        float x = event.getX();
        float y =event.getY();
        if(x>canvasXSize * 1 / 10 && x< canvasXSize*1/10 +300 &&y>canvasYSize*1/10 && y<canvasYSize*1/10 + 300)
        {
            if(top.contain(card1[0])) {

                setImages();
                Log.d(TAG, "onTouchEvent: " +top.getCards()[0]);
                holder.lockCanvas();
                drawPic(c);
                holder.unlockCanvasAndPost(c);

            }
        }
        else  if(x>canvasXSize * 6.5 / 10 && x< canvasXSize*6.5/10 +300 &&y>canvasYSize*1/10 && y<canvasYSize*1/10 + 300)
        {
            if(top.contain(card1[1])) {

                setImages();
                holder.lockCanvas();
                drawPic(c);
                holder.unlockCanvasAndPost(c);

            }
        }
        else  if(x>canvasXSize * 3.5 / 10 && x< canvasXSize*3.5/10 +300 &&y>canvasYSize*3/10 && y<canvasYSize*3/10 + 300)
        {
            if(top.contain(card1[2])) {

                setImages();
                holder.lockCanvas();
                drawPic(c);
                holder.unlockCanvasAndPost(c);

            }
        }

        return super.onTouchEvent(event);
    }
    private void drawPic(Canvas canvas)
    {
        canvas.drawColor(Color.GRAY);
        canvas.drawBitmap(scaledBackground,0,0,null);
        canvas.drawBitmap(scaledBackground,0,canvas.getHeight()/2,null);
        canvas.drawBitmap(picScaled[0], canvas.getWidth() * 1 / 10, canvas.getHeight() * 1/ 10, null);
        canvas.drawBitmap(picScaled[1], (float) (canvas.getWidth() *  6.5/ 10), canvas.getHeight() * 1 / 10, null);
        canvas.drawBitmap(picScaled[2], (float) (canvas.getWidth() * 3.5 / 10), canvas.getHeight() * 3/ 10, null);

        canvas.drawBitmap(picScaled[3], canvas.getWidth() *  1/ 10, canvas.getHeight() * 6 / 10, null);
        canvas.drawBitmap(picScaled[4], (float) (canvas.getWidth() * 6.5 / 10), canvas.getHeight() * 6 / 10, null);
        canvas.drawBitmap(picScaled[5], (float) (canvas.getWidth() * 3.5 / 10), canvas.getHeight() * 8 / 10, null);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        scaledBackground=Bitmap.createScaledBitmap(cardBackground, canvas.getWidth(), canvas.getHeight()/2, true);
        canvasXSize=canvas.getWidth();
        canvasYSize=canvas.getHeight();


        drawPic(canvas);

    }
}
