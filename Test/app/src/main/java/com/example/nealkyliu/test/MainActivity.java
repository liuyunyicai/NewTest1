package com.example.nealkyliu.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTextView;
    private TextView mTextView2;
    private boolean isFullScreen = false;

    private LinearLayout mRelatedNews;
    private ImageView mImgView;

    private float mScale = 0.5f;
    int screen_width;
    int screen_height;
    private View view;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen_width = UIUtils.getScreenWidth(this);
        screen_height = UIUtils.getScreenHeight(this);

        mImgView = (ImageView) findViewById(R.id.mImgView);
        changeImageView();
        window = getWindow();
        view = window.getDecorView();

        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LOW_PROFILE);
                } else {
                    view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                }
            }
        });


        Log.i(TAG, "test 2");




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

    private void changeImageView() {
        mScale = 0.5 == mScale ? 0.8f : 0.5f;
        Log.i(TAG, "current scale == " + mScale);
        int cur_height = (int)(screen_height * mScale);
        int cur_width = screen_width;
        mImgView.setMaxWidth(cur_width);
        mImgView.setMaxHeight(cur_height);

        Matrix matrix = new Matrix();
        matrix.postTranslate(0, (int)(cur_height * 0.5));
        mImgView.setImageMatrix(matrix);
        mImgView.setLayoutParams(new RelativeLayout.LayoutParams(cur_width, ViewGroup.LayoutParams.WRAP_CONTENT));
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
