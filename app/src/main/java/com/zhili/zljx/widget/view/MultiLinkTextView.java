package com.zhili.zljx.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;


import com.zhili.zljx.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Custom Link TextView
 *
 * Created by @author chengyong on 2018/2/8.
 */

public class MultiLinkTextView extends AppCompatTextView {

    private String mLink1;
    private String mLink2;
    private String mLink3;
    private int mLink1Color;
    private int mLink2Color;
    private int mLink3Color;
    private boolean mShowUnderline;
    private OnLink1ClickListener mLink1Listener;
    private OnLink2ClickListener mLink2Listener;
    private OnLink3ClickListener mLink3Listener;

    public MultiLinkTextView(Context context) {
        this(context,null);
    }

    public MultiLinkTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiLinkTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiLinkTextView, defStyleAttr, 0);
        mLink1 = typedArray.getString(R.styleable.MultiLinkTextView_link1);
        mLink2 = typedArray.getString(R.styleable.MultiLinkTextView_link2);
        mLink3 = typedArray.getString(R.styleable.MultiLinkTextView_link3);
        mLink1Color = typedArray.getResourceId(R.styleable.MultiLinkTextView_linkColor1,-1);
        mLink2Color = typedArray.getResourceId(R.styleable.MultiLinkTextView_linkColor2,-1);
        mLink3Color = typedArray.getResourceId(R.styleable.MultiLinkTextView_linkColor3,-1);
        mShowUnderline = typedArray.getBoolean(R.styleable.MultiLinkTextView_multi_showUnderline,true);
        autoLink();
    }

    private void autoLink(){
        if (!TextUtils.isEmpty(getText()) && !TextUtils.isEmpty(mLink1)
                && !TextUtils.isEmpty(mLink2) && !TextUtils.isEmpty(mLink3) ) {
            int[]data = matchStartAndEnd(getText(), mLink1,mLink2,mLink3);
            SpannableString ss1 = new SpannableString(getText());
            ClickableSpan clickableSpan1 = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    if (mLink1Listener != null) {
                        mLink1Listener.onLink1Click();
                    }
                }
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(mShowUnderline);
                    ds.setColor(getResources().getColor(mLink1Color));
                }
            };
            ss1.setSpan(clickableSpan1, data[0], data[1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(ss1);
            setMovementMethod(LinkMovementMethod.getInstance());
            setHighlightColor(Color.TRANSPARENT);


            SpannableString ss2 = new SpannableString(getText());
            ClickableSpan clickableSpan2 = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    if (mLink2Listener != null) {
                        mLink2Listener.onLink2Click();
                    }
                }
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(mShowUnderline);
                    ds.setColor(getResources().getColor(mLink2Color));
                }
            };
            ss2.setSpan(clickableSpan2, data[2], data[3], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(ss2);
            setMovementMethod(LinkMovementMethod.getInstance());
            setHighlightColor(Color.TRANSPARENT);


            SpannableString ss3 = new SpannableString(getText());
            ClickableSpan clickableSpan3 = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    if (mLink3Listener != null) {
                        mLink3Listener.onLink3Click();
                    }
                }
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(mShowUnderline);
                    ds.setColor(getResources().getColor(mLink3Color));
                }
            };
            ss3.setSpan(clickableSpan3, data[4], data[5], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(ss3);
            setMovementMethod(LinkMovementMethod.getInstance());
            setHighlightColor(Color.TRANSPARENT);
        }
    }

    public interface OnLink1ClickListener {
        void onLink1Click();
    }

    public interface OnLink2ClickListener {
        void onLink2Click();
    }

    public interface OnLink3ClickListener {
        void onLink3Click();
    }

//    public void setLinkText(String link){
//        this.mLink1 = link;
//        autoLink();
//    }

    public void setOnLink1ClickListener(OnLink1ClickListener listener){
        this.mLink1Listener = listener;
    }

    public void setOnLink2ClickListener(OnLink2ClickListener listener){
        this.mLink2Listener = listener;
    }

    public void setOnLink3ClickListener(OnLink3ClickListener listener){
        this.mLink3Listener = listener;
    }

    private int[] matchStartAndEnd(CharSequence text, String regex1,String regex2,String regex3) {
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(text);

        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(text);

        Pattern pattern3 = Pattern.compile(regex3);
        Matcher matcher3 = pattern3.matcher(text);

        int[] data = new int[6];
        while (matcher1.find()) {
            data[0] = matcher1.start();
            data[1] = matcher1.end();
        }
        while (matcher2.find()) {
            data[2] = matcher2.start();
            data[3] = matcher2.end();
        }
        while (matcher3.find()) {
            data[4] = matcher3.start();
            data[5] = matcher3.end();
        }

        Log.e("Muti","data="+data[0]);
        Log.e("Muti","data="+data[1]);
        return data;
    }

}
