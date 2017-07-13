package com.example.nealkyliu.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by nealkyliu on 2017/7/10.
 */

public class FullScreenUtil {
    private static final String TAG = FullScreenUtil.class.getSimpleName();

    public static void isFullScreen(Context context, Handler handler, int what) {
        boolean isFullScreenFlag = false;
        final View view = new TextView(context);
        view.setBackgroundColor(Color.parseColor("#ff0dd1"));

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        wmParams.gravity = Gravity.TOP | Gravity.LEFT;
        wmParams.width = 1;
        wmParams.height = 1;

        boolean isViewAdded = false;
        try {
            windowManager.addView(view, wmParams);

            isViewAdded = true;

            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int location[] = new int[2];
                    view.getLocationOnScreen(location);
                    boolean isFullScreenFlag = (location[0] == 0 && location[1] == 0);
                }
            });



            logLocation(view);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
            isViewAdded = false;
        } finally {
            if (isViewAdded) {
                try {
                    windowManager.removeView(view);
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        }

    }

    private static void sendResult(Handler handler, int what) {
        Message message = handler.obtainMessage(what);

    }

    private static void logLocation(View mView) {
        int location[] = new int[2];
        if (null != mView) {
            mView.getLocationOnScreen(location);
            Log.d(TAG, "LocationOnScreen = (" + location[0] + ", " + location[1] + ")");

            mView.getLocationInWindow(location);
            Log.d(TAG, "getLocationInWindow = (" + location[0] + ", " + location[1] + ")");
        }
    }




}
