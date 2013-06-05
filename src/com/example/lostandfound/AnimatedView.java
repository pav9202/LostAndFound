package com.example.lostandfound;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.Math;

import com.example.lostandfound.R;
/*
 * This class is being used to implement our splash screen
 */
public class AnimatedView extends ImageView{
        private Context mContext;
        boolean start = true;
        int x = 0;
        int y = 0;
        Bitmap balls;
        double mSign = 1;
        double vSign = 0;
        double Fnet = 0;
        int mScreenWidth;
        int mScreenHeight;
        Bitmap ball;
        int t = 0;
        private Handler h;
        private final int FRAME_RATE = 5;
        boolean doit;
        /*
         * i'm setting up the initial variables
         */
        public AnimatedView(Context context, AttributeSet attrs)  {
                super(context, attrs);
                mContext = context;
                DisplayMetrics displayMetrics = new DisplayMetrics(); 
                displayMetrics = mContext.getResources().getDisplayMetrics();
                mScreenWidth = displayMetrics.widthPixels;
                mScreenHeight = displayMetrics.heightPixels;
                h = new Handler();
                balls = BitmapFactory.decodeResource(this.getResources(),R.drawable.lostafoundedited);
                ball = Bitmap.createScaledBitmap(balls, (int)mScreenWidth/2, mScreenHeight/3, true);
                doit=true;
        }
        /*
         * i'm overriding the run method in Runnable to make it do as i wish
         */
         private Runnable r = new Runnable() {
                 @Override
                 public void run() {
                         invalidate();
                 }
         };
         /*
          * Canvas C is the canvas that android uses to draw itself
          * in this method i implement artificial gravity. I also give it a timer and boundaries
          * so that it will bounce in the middle for a little while. and then it will go to the bottom of the screen
          * and the intent will be created and the activity will be switched.
          */
         protected void onDraw(Canvas c) {
        	if(doit){
        	 if(start)
        	 {
        		 x = (this.getWidth()/2) - (ball.getWidth()/2);
        		 start = false;
        	 }
        	 if(t<1250)
        	 {
        		 Fnet = (mSign*1);
        		 vSign = vSign +((Fnet)/mSign);
        		 if(y>=((this.getHeight()/2))-(ball.getHeight()/2)) 
        		 {
        			 if(Math.abs(vSign) >= 4)
        			 {
        				 if(this.getHeight()<1000) vSign = -vSign + 3;
        				 else{
        					 vSign = -vSign + 8;
        				 }
        			 }
        			 else
        			 {
        				 vSign = 0;
        			 }
        			 y = ((this.getHeight()/2))-(ball.getHeight()/2);
        		 }
        		 y = y + (int)vSign;
        		 c.drawBitmap(ball, x, y, null);
                 h.postDelayed(r, FRAME_RATE);
                 t=t+5;
        	 }
        	 else{
        		 vSign = vSign + 1;
        		 y=y + (int)vSign;
        		 c.drawBitmap(ball, x, y, null);
                 h.postDelayed(r, FRAME_RATE);
                 if(y >= this.getHeight())
                 {
                	 Toast t = Toast.makeText(mContext, "I hit the bottom", Toast.LENGTH_LONG);
                	 //t.show();
                	 doit = false;
                	 Intent in = new Intent(mContext,LoginActivity.class);
         		 	 mContext.startActivity(in);
                 }
        	 }
        	 
             /*
             } else {
                 x += xVelocity;
                 y += yVelocity;
                 if ((x > this.getWidth() - ball.getBitmap().getWidth()) || (x < 0)) {
                         xVelocity = xVelocity*-1;
                 }
                 if ((y > this.getHeight() - ball.getBitmap().getHeight()) || (y < 0)) {
                         yVelocity = yVelocity*-1;
                 }
            }
            */
        	}
      }
}