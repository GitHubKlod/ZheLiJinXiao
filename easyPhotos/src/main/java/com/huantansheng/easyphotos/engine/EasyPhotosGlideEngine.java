package com.huantansheng.easyphotos.engine;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * 创建日期：2022/3/15 20:22
 *
 * @author KLOD
 * 包名： com.ixiu.jingmai.common
 * 类说明：
 */
public class EasyPhotosGlideEngine implements ImageEngine {
    //单例
    private static EasyPhotosGlideEngine instance = null;
    //单例模式，私有构造方法
    private EasyPhotosGlideEngine() {
    }
    //获取单例
    public static EasyPhotosGlideEngine getInstance() {
        if (null == instance) {
            synchronized (EasyPhotosGlideEngine.class) {
                if (null == instance) {
                    instance = new EasyPhotosGlideEngine();
                }
            }
        }
        return instance;
    }

    /**
     * 加载图片到ImageView
     *
     * @param context   上下文
     * @param uri 图片路径Uri
     * @param imageView 加载到的ImageView
     */
    //安卓10推荐uri，并且path的方式不再可用
    @Override
    public void loadPhoto(Context context, Uri uri, ImageView imageView) {
        Glide.with(context).load(uri).transition(withCrossFade()).into(imageView);
    }

    /**
     * 加载gif动图图片到ImageView，gif动图不动
     *
     * @param context   上下文
     * @param gifUri   gif动图路径Uri
     * @param imageView 加载到的ImageView
     *                  <p>
     *                  备注：不支持动图显示的情况下可以不写
     */
    //安卓10推荐uri，并且path的方式不再可用
    @Override
    public void loadGifAsBitmap(Context context, Uri gifUri, ImageView imageView) {
        Glide.with(context).asBitmap().load(gifUri).into(imageView);
    }

    /**
     * 加载gif动图到ImageView，gif动图动
     *
     * @param context   上下文
     * @param gifUri   gif动图路径Uri
     * @param imageView 加载动图的ImageView
     *                  <p>
     *                  备注：不支持动图显示的情况下可以不写
     */
    //安卓10推荐uri，并且path的方式不再可用
    @Override
    public void loadGif(Context context, Uri gifUri, ImageView imageView) {
        Glide.with(context).asGif().load(gifUri).transition(withCrossFade()).into(imageView);
    }


    /**
     * 获取图片加载框架中的缓存Bitmap
     *
     * @param context 上下文
     * @param uri    图片路径
     * @param width   图片宽度
     * @param height  图片高度
     * @return Bitmap
     * @throws Exception 异常直接抛出，EasyPhotos内部处理
     */
    //安卓10推荐uri，并且path的方式不再可用
    @Override
    public Bitmap getCacheBitmap(Context context, Uri uri, int width, int height) throws Exception {
        return Glide.with(context).asBitmap().load(uri).submit(width, height).get();
    }


}
