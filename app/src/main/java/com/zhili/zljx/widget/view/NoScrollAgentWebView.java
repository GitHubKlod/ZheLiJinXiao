package com.zhili.zljx.widget.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import com.just.agentweb.AgentWebView;

/**
 * @Auther KLOD
 * 2023/4/19 19:57
 */
public class NoScrollAgentWebView  extends AgentWebView {
    public NoScrollAgentWebView(Context context) {
        super(context);
        setNestedScrollingEnabled(true);

    }

    public NoScrollAgentWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setNestedScrollingEnabled(true);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }

}
