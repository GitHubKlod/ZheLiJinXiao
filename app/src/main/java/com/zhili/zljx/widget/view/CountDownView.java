package com.zhili.zljx.widget.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.zhili.zljx.R;


/**
 * 获取验证码按钮
 */
public class CountDownView extends AppCompatButton {

    private int mCountTime = 60;

    public CountDownView(Context context) {
        super(context);
        init();
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setText("获取验证码");
//        setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            setText(mCountTime + "秒后重发");
//            setTextColor(getResources().getColor(R.color.FF999999));
            setEnabled(false);
            if (mCountTime > 0) {
                postDelayed(this, 1000);
                mCountTime--;
            } else {
                setText("重发验证码");
//                setTextColor(getResources().getColor(R.color.FF333333));
                setEnabled(true);
                mCountTime = 60;
            }

        }
    };

    public void startCountDown() {
        post(runnable);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeRunnable();
    }

    private void removeRunnable() {
        removeCallbacks(runnable);
    }

}
