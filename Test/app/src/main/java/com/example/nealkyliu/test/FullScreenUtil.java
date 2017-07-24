package com.example.nealkyliu.test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nealkyliu on 2017/7/10.
 */

public class FullScreenUtil {
    public static final String TAG = FullScreenUtil.class.getSimpleName();
    static boolean isFullScreenFlag = false;
    public static boolean isFullScreen(final Context context, View view) {

        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN; // 添加FULL_SCREEN属性
        wmParams.gravity = Gravity.TOP | Gravity.LEFT;
        wmParams.width = 1;
        wmParams.height = 1;

        boolean isViewAdded = false;
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        try {
            windowManager.addView(view, wmParams);

            // 获取可视区域大小
            Rect rect = new Rect();
            view.getWindowVisibleDisplayFrame(rect);

            Point point = getScreenDimenson(context, windowManager);
            isFullScreenFlag = (rect.width() >= point.x) && (rect.height() >= point.y);

            Log.i(TAG, "Screen Size =(" + point.x + ", " + point.y + ")");
            Log.i(TAG, "Current Rect= (" + rect.width() + "," + rect.height() + ")");

            isViewAdded = true;
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
        return isFullScreenFlag;
    }

    private static Point getScreenDimenson(Context context, WindowManager windowManager) {
        Point screenPoint = new Point();
        if (null == windowManager) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        windowManager.getDefaultDisplay().getSize(screenPoint);
        return screenPoint;
    }



}
