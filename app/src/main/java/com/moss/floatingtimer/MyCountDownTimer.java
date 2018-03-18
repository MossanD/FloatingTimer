package com.moss.floatingtimer;

import android.os.CountDownTimer;
import android.widget.TextView;

public class MyCountDownTimer extends CountDownTimer {

    private TextView mRemainTimeView;
    private long mMillisRemaining;
    private static final Long INTERVAL = 10l;

    public MyCountDownTimer(long millisInFuture, TextView timerView) {
        super(millisInFuture, INTERVAL);
        mRemainTimeView = timerView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mMillisRemaining = millisUntilFinished;
        String hour = String.format("%02d", getHourFromMillis(millisUntilFinished));
        String second = String.format("%02d", getSecondFromMillis(millisUntilFinished));
        String minute = String.format("%02d", getMinuteFromMillis(millisUntilFinished));
        mRemainTimeView.setText("" + hour
                + ":" + second
                + ":" + minute);
    }

    @Override
    public void onFinish() {
        mRemainTimeView.setText("" + String.format("%02d", 0)
                + ":" + String.format("%02d", 0)
                + ":" + String.format("%02d", 0));
    }

    public long getMillisRemaining() {
        return mMillisRemaining;
    }

    private int getHourFromMillis(long millis) {
        return (int) millis / 3600000;
    }

    private int getSecondFromMillis(long millis) {
        return (int) (millis % 3600000) / 60000;
    }

    private int getMinuteFromMillis(long millis) {
        return (int) ((millis % 60000) / 1000);
    }
}
