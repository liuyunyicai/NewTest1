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
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTestTxt;
    private boolean isFullScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        setFullScreen();

        Log.i(TAG, "scale == " + getResources().getDisplayMetrics().density);

//
        mTestTxt = (TextView) findViewById(R.id.test_txt);
        mTestTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFullScreen) {
                    setNotFullScreen();

                } else {
                    setFullScreen();
//                    setLandScapeAndFullScreen();
                }
//                logLocation();
            }
        });
//
        startService(new Intent(this, MyTestService2.class));
////        startService(new Intent(this, FullScreenListenerService.class));
////        startService(new Intent(this, MyTestService2.class));
//
//        startService(new Intent(this, MyTestService.class));

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
