package com.moss.floatingtimer;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class FloatingTimerService extends Service {

    private WindowManager mWindowManager;
    private FloatingTimerView mFloatingView;
    private static final int NOTIFICATION_ID = 101;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        if(mWindowManager!=null){
            return START_REDELIVER_INTENT;
        }

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        mFloatingView = new FloatingTimerView(this);
        mWindowManager.addView(mFloatingView, mFloatingView.getWindowLayoutParams());

        startForeground(NOTIFICATION_ID, createNotification(this));
        return START_REDELIVER_INTENT;
    }

    private static Notification createNotification(Context context) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("FloatTimer");
        builder.setContentText("FloatingTimer is Running");
        builder.setOngoing(true);
        builder.setPriority(NotificationCompat.PRIORITY_MIN);
        builder.setCategory(NotificationCompat.CATEGORY_SERVICE);
        return builder.build();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onDestroy() {
        mWindowManager.removeView(mFloatingView);
    }
}
