package com.moss.floatingtimer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

public class MyNumberPicker  extends NumberPicker {
    public MyNumberPicker(Context context) {
        super(context);
        initialize();
    }
    public MyNumberPicker(Context context, AttributeSet attrs){
        super(context,attrs);
        initialize();
    }
    public MyNumberPicker(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        initialize();
    }
    private void initialize(){
        this.setMinValue(0);
        this.setMaxValue(59);
        this.setValue(0);
        this.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }
    public void reset(){
        this.setValue(0);
    }
}
