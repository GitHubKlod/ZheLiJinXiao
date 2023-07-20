package com.huantansheng.easyphotos.ui;

import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.huantansheng.easyphotos.R;


/**
 * 创建日期：2022/7/18 13:34
 *
 * @author KLOD
 * 包名： com.ixiu.jingmai.ui.message
 * 类说明：
 */
public class EPVideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_play_ep);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
    }

    private void init() {

        String source1 = getIntent().getStringExtra("videoUri");
        if(TextUtils.isEmpty(source1)){
            source1 = "";
        }
        videoView = (VideoView)findViewById(R.id.videoView);
//        videoView.setMediaController(new MediaController(this));
// 设置视频源播放res/raw中的文件,文件名小写字母,格式: 3gp,mp4等,flv的不一定支持;
        Uri rawUri = Uri.parse(source1);
        videoView.setVideoURI(rawUri);
        videoView.start();
        videoView.requestFocus();

    }


    @Override
    protected void onPause() {
        super.onPause();

        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        videoView.stopPlayback();
        videoView.stopPlayback();
        videoView = null;
    }

    @Override
    public void onBackPressed() {
///       不需要回归竖屏
//        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            videoPlayer.getFullscreenButton().performClick();
//            return;
//        }

        super.onBackPressed();
    }
}