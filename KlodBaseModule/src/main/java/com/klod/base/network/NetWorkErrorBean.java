package com.klod.base.network;

import android.text.TextUtils;
import android.util.Log;

import com.klod.base.ext.util.LogExtKt;

/**
 * @Auther KLOD
 * 2023/3/31 20:49
 */
public class NetWorkErrorBean {

    private String ErrMsg;
    private String error;
    private String msg;
    private String errorMsg;

    private String message;

    private String title;

    public String getErrMsg() {
        Log.e("查看数据",
                "消息 = " + ErrMsg
                        + " 2 = " + error
                        + "  3 = " + msg
                        + "  4=" + errorMsg
                        + "  5=" + message
                        + "  6=" + title
        );
        String response = "";
        if (!TextUtils.isEmpty(ErrMsg)) {
            response = ErrMsg;
        } else if (!TextUtils.isEmpty(error)) {
            response = error;
        } else if (!TextUtils.isEmpty(msg)) {
            response = msg;
        } else if (!TextUtils.isEmpty(errorMsg)) {
            response = errorMsg;
        } else if (!TextUtils.isEmpty(message)) {
            response = message;
        } else if (!TextUtils.isEmpty(title)) {
            response = title;
        }
        Log.e("查看数据", "显示的消息 =  " + response);

        return response;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
