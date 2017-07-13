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
    private ImageView mView;
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
        startService(new Intent(this, MyTestService.class));
////        startService(new Intent(this, FullScreenListenerService.class));
////        startService(new Intent(this, MyTestService2.class));
//
//        startService(new Intent(this, MyTestService.class));
////        initView();
//
//

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

    private void initView() {
        mView = new ImageView(this);
        mView.setBackgroundColor(Color.parseColor("#ff0dd1"));

        mView.setImageResource(R.mipmap.hello);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        wmParams.gravity = Gravity.TOP | Gravity.LEFT;
        wmParams.format = PixelFormat.RGBA_8888;
//        wmParams.x = 0;
//        wmParams.y = 0;
        wmParams.width = 100;
        wmParams.height = 100;

        try {
            windowManager.addView(mView, wmParams);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }


    }

    private void logLocation() {
        int location[] = new int[2];
        mView.getLocationOnScreen(location);
        Log.d(TAG, "LocationOnScreen = (" + location[0] + ", " + location[1] + ")");

        mView.getLocationInWindow(location);
        Log.d(TAG, "getLocationInWindow = (" + location[0] + ", " + location[1] + ")");
    }

    public static boolean isFullscreen(View topLeftView) {
        int location[] = new int[2];
        topLeftView.getLocationOnScreen(location);
        return location[0] == 0 && location[1] == 0;
    }

}
