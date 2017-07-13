package com.example.nealkyliu.test;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/*
* 判断当前手机屏幕是够全屏
**/
public class MyTestService extends Service {
    private static final String TAG = MyTestService.class.getSimpleName();
    private ImageView mView;
    private WindowManager.LayoutParams mWmParams;
    private WindowManager mWindowManager;

    private MyMsgHandler mHandler;
    private static final int UPDATE_MSG = 1;

    public MyTestService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new MyMsgHandler(this);

        initView();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (null != mHandler) {
                        mHandler.sendEmptyMessage(UPDATE_MSG);
                    }

                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }


    private void initView() {
        mView = new ImageView(this);
        mWmParams = new WindowManager.LayoutParams();
        mWmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mWmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN; // 添加FULL_SCREEN属性
        mWmParams.gravity = Gravity.TOP | Gravity.LEFT;
        mWmParams.width = 1;
        mWmParams.height = 1;

        try {
            mWindowManager.addView(mView, mWmParams);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }


//        try {
//            Class<?> clazz = mWindowManager.getClass();
//            Field field = clazz.getDeclaredField("mParentWindow");
//            field.setAccessible(true);
//
//            Window window = (Window) field.get(mWindowManager);
//
//            Log.w(TAG, "Window Type==" + window.getClass().getName());
//        } catch (NoSuchFieldException e) {
//            Log.e(TAG, e.toString());
//        } catch (IllegalAccessException e) {
//            Log.e(TAG, e.toString());
//        }


    }

    /*
    * 获取屏幕尺寸
    **/
    private Point getScreenDimenson(Context context) {
        Point screenPoint = new Point();
        if (null == mWindowManager) {
            mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        }
        mWindowManager.getDefaultDisplay().getSize(screenPoint);
        return screenPoint;
    }

    private int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v(TAG, "Navi height:" + height);
        return height;
    }

    private int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v(TAG, "Status height:" + height);
        return height;
    }

    /**
     * 通过反射的方式获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }





    /**
     * 判断是否全屏
     **/
    private boolean isScreenFull(View view) {
        mWindowManager.updateViewLayout(mView, mWmParams);
        // 获取可视区域大小
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);

        Point point = getScreenDimenson(this);
        boolean flag;
        flag = (rect.width() >= point.x) && (rect.height() >= point.y);

        Log.i(TAG, "Screen Size =(" + point.x + ", " + point.y + ")");
        Log.i(TAG, "Current Rect= (" + rect.width() + "," + rect.height() + ")");

        return flag;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 判断当前屏幕是否为横屏
     **/
    public boolean isScreenLandScape(Context context) {
        int orientation = 0;
        if (null != context) {
            orientation = context.getResources().getConfiguration().orientation;
        }
        return orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /*
     * 用以捕获后台线程发送来的更新指令
     **/
    private static class MyMsgHandler extends Handler{
        /* 建立弱引用 */
        private WeakReference<Context> mOuter;

        /* 构造函数 */
        public MyMsgHandler(Context context) {
            mOuter = new WeakReference<>(context);
        }

        /** 处理函数*/
        public void handleMessage(Message msg) {
            // 防止内存泄露
            MyTestService outer = (MyTestService)mOuter.get();
            if (outer != null) {
                outer.handleMessage(msg);
            }
        }
    }

    /*
     * 消息处理函数
     **/
    private void handleMessage(Message msg) {
        switch (msg.what) {
            case UPDATE_MSG:
                if (isScreenFull(mView)) {
                    Log.w(TAG, "Current Screen is Full");
                } else {
                    Log.i(TAG, "Current Screen is Not Full");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if ((null != mWindowManager) && (null != mView)) {
            mWindowManager.removeView(mView);
        }
    }
}