package com.example.finddamatch.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.finddamatch.R;

import static android.content.ContentValues.TAG;
import static com.example.finddamatch.MainActivity.Deck;
import static com.example.finddamatch.MainActivity.hand;
import static com.example.finddamatch.MainActivity.option;
import static com.example.finddamatch.MainActivity.top;
import static com.example.finddamatch.UI.GameActivity.score;
import static com.example.finddamatch.flickr.PhotoGalleryFragment.flickImgSelected;
/*
    Description: using canvas to start the game layout and game logic
 */
public class gameView extends SurfaceView {
    private Bitmap[] pic = new Bitmap[6];
    private Bitmap[] picScaled = new Bitmap[6];
    private Bitmap cardBackground;
    private Bitmap scaledBackground;
    private SurfaceHolder holder;
    private float canvasXSize;
    private float canvasYSize;
    private int timer;
    String[] card1;
    String[] card2;
    Canvas c;

    public gameView(Context context) {//https://www.youtube.com/watch?v=3V5aV-iM8YA&t=7s
        super(context);
        holder = getHolder();

        timer=(int) SystemClock.elapsedRealtime();
        score=0;
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
        //if user won
        if (top.getCards().equals(hand.getCards())) {

            //calculate time
            score= (int) (SystemClock.elapsedRealtime()-timer);


            Intent intent = new Intent().setClass(getContext(), Won.class);
            //pass score value to winning screen activity
            intent.putExtra("score",score);
            ((Activity) getContext()).startActivity(intent);
            ((Activity) getContext()).finish();

        }
        card1 = hand.getCards();
        card2 = top.getCards();
        Log.d(TAG, "setImages: " + card1[0] + card2[0]);
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
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
                else if (card2[j] == "pic2")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
                else if (card2[j] == "pic3")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image3);
                else if (card2[j] == "pic4")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image4);
                else if (card2[j] == "pic5")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image5);
                else if (card2[j] == "pic6")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image6);
                else if (card2[j] == "pic7")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image7);
            }
        } else if (option == 2) {
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
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image8);
                else if (card2[j] == "pic2")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image9);
                else if (card2[j] == "pic3")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image10);
                else if (card2[j] == "pic4")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image11);
                else if (card2[j] == "pic5")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image12);
                else if (card2[j] == "pic6")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image13);
                else if (card2[j] == "pic7")
                    pic[j + 3] = BitmapFactory.decodeResource(getResources(), R.drawable.image14);
            }
        } else if (option == 3) {
            for (int i = 0; i < card1.length; i++) {
                if (card1[i] == "pic1")
                    pic[i] = flickImgSelected[0];
                else if (card1[i] == "pic2")
                    pic[i] = flickImgSelected[1];
                else if (card1[i] == "pic3")
                    pic[i] = flickImgSelected[2];
                else if (card1[i] == "pic4")
                    pic[i] = flickImgSelected[3];
                else if (card1[i] == "pic5")
                    pic[i] = flickImgSelected[4];
                else if (card1[i] == "pic6")
                    pic[i] = flickImgSelected[5];
                else if (card1[i] == "pic7")
                    pic[i] = flickImgSelected[6];
            }
            for (int j = 0; j < card2.length; j++) {
                if (card2[j] == "pic1")
                    pic[j + 3] = flickImgSelected[0];
                else if (card2[j] == "pic2")
                    pic[j + 3] = flickImgSelected[1];
                else if (card2[j] == "pic3")
                    pic[j + 3] = flickImgSelected[2];
                else if (card2[j] == "pic4")
                    pic[j + 3] = flickImgSelected[3];
                else if (card2[j] == "pic5")
                    pic[j + 3] = flickImgSelected[4];
                else if (card2[j] == "pic6")
                    pic[j + 3] = flickImgSelected[5];
                else if (card2[j] == "pic7")
                    pic[j + 3] = flickImgSelected[6];
            }
        }
        for (int i = 0; i < card1.length; i++) {
            picScaled[i] = Bitmap.createScaledBitmap(pic[i], 300, 300, true);
            picScaled[i + 3] = Bitmap.createScaledBitmap(pic[i + 3], 300, 300, true);//https://stackoverflow.com/questions/27466099/how-to-resize-bitmap-when-drawing-in-canvas
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        //when touched compare the coordinates to see whether it is within the picture's coordinate and check if the picture is contained in the top and hand
        if (x > canvasXSize * 1 / 10 && x < canvasXSize * 1 / 10 + 300 && y > canvasYSize * 1 / 10 && y < canvasYSize * 1 / 10 + 300) {
            if (top.contain(card1[0])) {

                setImages();
                Log.d(TAG, "onTouchEvent: " + top.getCards()[0]);
                holder.lockCanvas();
                drawPic(c);
                holder.unlockCanvasAndPost(c);

            }
        } else if (x > canvasXSize * 6.5 / 10 && x < canvasXSize * 6.5 / 10 + 300 && y > canvasYSize * 1 / 10 && y < canvasYSize * 1 / 10 + 300) {
            if (top.contain(card1[1])) {

                setImages();
                holder.lockCanvas();
                drawPic(c);
                holder.unlockCanvasAndPost(c);

            }
        } else if (x > canvasXSize * 3.5 / 10 && x < canvasXSize * 3.5 / 10 + 300 && y > canvasYSize * 3 / 10 && y < canvasYSize * 3 / 10 + 300) {
            if (top.contain(card1[2])) {

                setImages();
                holder.lockCanvas();
                drawPic(c);
                holder.unlockCanvasAndPost(c);

            }
        }

        return super.onTouchEvent(event);
    }

    private void drawPic(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        canvas.drawBitmap(scaledBackground, 0, 0, null);
        canvas.drawBitmap(scaledBackground, 0, canvas.getHeight() / 2, null);
        canvas.drawBitmap(picScaled[0], canvas.getWidth() * 1 / 10, canvas.getHeight() * 1 / 10, null);
        canvas.drawBitmap(picScaled[1], (float) (canvas.getWidth() * 6.5 / 10), canvas.getHeight() * 1 / 10, null);
        canvas.drawBitmap(picScaled[2], (float) (canvas.getWidth() * 3.5 / 10), canvas.getHeight() * 3 / 10, null);

        canvas.drawBitmap(picScaled[3], canvas.getWidth() * 1 / 10, canvas.getHeight() * 6 / 10, null);
        canvas.drawBitmap(picScaled[4], (float) (canvas.getWidth() * 6.5 / 10), canvas.getHeight() * 6 / 10, null);
        canvas.drawBitmap(picScaled[5], (float) (canvas.getWidth() * 3.5 / 10), canvas.getHeight() * 8 / 10, null);

    }
    private Bitmap getCircleBitmap(Bitmap bitmap){//https://stackoverflow.com/questions/12944275/crop-image-as-circle-in-android
        //crop bitmap into circle
        int widthLight = bitmap.getWidth();
        int heightLight = bitmap.getHeight();

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        Paint paintColor = new Paint();
        paintColor.setFlags(Paint.ANTI_ALIAS_FLAG);

        RectF rectF = new RectF(new Rect(0, 0, widthLight, heightLight));

        canvas.drawRoundRect(rectF, widthLight / 2 ,heightLight / 2,paintColor);

        Paint paintImage = new Paint();
        paintImage.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, 0, 0, paintImage);

        return output;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        scaledBackground = Bitmap.createScaledBitmap(cardBackground, canvas.getWidth(), canvas.getHeight() / 2, true);
        canvasXSize = canvas.getWidth();
        canvasYSize = canvas.getHeight();


        drawPic(canvas);

    }
}
