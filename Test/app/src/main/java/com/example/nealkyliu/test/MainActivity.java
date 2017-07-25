package com.example.nealkyliu.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTextView;
    private TextView mTextView2;
    private boolean isFullScreen = false;

    private LinearLayout mRelatedNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.test_txt);
        mTextView2 = (TextView) findViewById(R.id.test_txt2);


        mTextView2.setTextSize(13);


//        View view = new View(this);
//        View two = ((ViewGroup)view).getChildAt(0);
//
//        mRelatedNews = (LinearLayout) findViewById(R.id.mRelatedNews);
//        mRelatedNews.setOrientation(LinearLayout.VERTICAL);
//
//        LayoutInflater inflater = LayoutInflater.from(this);



//        for (int i = 0; i < 10; i++) {
//            View childLayout = mRelatedNews.getChildAt(i / 2);
//            if (null == childLayout) {
//                childLayout = new LinearLayout(this);
//                mRelatedNews.addView(childLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//            }
//
//            if (childLayout instanceof LinearLayout) {
//                ((LinearLayout)childLayout).setOrientation(LinearLayout.HORIZONTAL);
//
//                View mView = inflater.inflate(R.layout.test_text, null, false);
//                mView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
//                ((LinearLayout)childLayout).addView(mView);
//            }
//
//        }

//        Log.i(TAG, "Screen height == " + UIUtils.getScreenHeight(this));
//        Log.i(TAG, "Screen width == " + UIUtils.getScreenWidth(this));
//        Log.w(TAG, "Screen DisplayMetrics == " + this.getResources().getDisplayMetrics());
//        Log.w(TAG, "scale == " + UIUtils.getScreenScale(this));
//
//        setFullScreen();

//        Log.i(TAG, "scale == " + getResources().getDisplayMetrics().density);

////
//        mTestTxt = (TextView) findViewById(R.id.test_txt);
//        mTestTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isFullScreen) {
//                    setNotFullScreen();
//
//                } else {
//                    setFullScreen();
////                    setLandScapeAndFullScreen();
//                }
////                logLocation();
//            }
//        });
//
//        startService(new Intent(this, MyTestService2.class));

    }

    private void setFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        isFullScreen = true;
    }

    private void setNotFullScreen() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        isFullScreen = false;
    }

    private void setLandScapeAndFullScreen() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        isFullScreen = true;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        startService(new Intent(this, FullScreenListenerService.class));
//        logLocation();
    }


}
