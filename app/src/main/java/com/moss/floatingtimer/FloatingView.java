package com.moss.floatingtimer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class FloatingView extends FrameLayout {

    private WindowManager mWindowManager;
    private final WindowManager.LayoutParams mLayoutParams;
    private final int mStatusBarHeight;
    private float mLocalTouchX = 0;
    private float mLocalTouchY = 0;

    public FloatingView(final Context context) {
        super(context);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mLayoutParams.format = PixelFormat.TRANSLUCENT;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;

        final Resources resources = context.getResources();
        mStatusBarHeight = resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float screenTouchX = event.getRawX();
        float screenTouchY = event.getRawY() - mStatusBarHeight;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLocalTouchX = event.getX();
                mLocalTouchY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTo((int) (screenTouchX - mLocalTouchX), (int) (screenTouchY - mLocalTouchY));
                break;
        }
        return false;
    }

    private void moveTo(int x, int y) {
        mLayoutParams.x = x;
        mLayoutParams.y = y;
        if (ViewCompat.isAttachedToWindow(this)) {
            mWindowManager.updateViewLayout(this, mLayoutParams);
        }
    }

    public WindowManager.LayoutParams getWindowLayoutParams() {
        return mLayoutParams;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
