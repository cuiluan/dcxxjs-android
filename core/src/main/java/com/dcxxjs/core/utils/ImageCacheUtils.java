//package com.dcxxjs.core.utils;
//
//import android.graphics.drawable.Drawable;
//import android.widget.ImageView;
//
//
//import org.xutils.common.Callback;
//import org.xutils.common.util.DensityUtil;
//import org.xutils.image.ImageOptions;
//import org.xutils.x;
//
//import java.io.File;
//
///**
// * 图片缓存
// */
//public class ImageCacheUtils {
//    /**
//     *
//     * @param filename 文件名
//     * @param url 服务地址，不包含文件名
//     * @param filecachepath  缓存路径，不包含文件名
//     * @return
//     */
//    public static void LoadImage(String filename,String url,String filecachepath)
//    {
//        ImageOptions imageOptions = new ImageOptions.Builder()
//                .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
//                //.setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
//                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)//缩放
//                .setLoadingDrawableId(R.drawable.icon_default_avatar)//加载中默认显示图片
//                .setCircular(true)
//                .setFailureDrawableId(R.drawable.icon_default_avatar)//加载失败后默认显示图片
//                .build();
//         LoadImage(filename,url,filecachepath,imageOptions);
//    }
//
//    /**
//     * 缓存图片
//     * @param filename
//     * @param url
//     * @param filecachepath
//     * @param imageOptions
//     */
//    public static void LoadImage(String filename, String url,String filecachepath,ImageOptions imageOptions)
//    {
//        //加载图片
//        x.image().loadFile(AppServerIp.headerIP + IMSServiceConfig.getHead(), imageOptions, new Callback.CacheCallback<File>() {
//            @Override
//            public boolean onCache(File result) {
//                return false;
//            }
//
//            @Override
//            public void onSuccess(File result) {
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//
//    }
//
//    public static void BindImage(ImageView view,String filename, String url,String filecachepath)
//    {
//        //判断是否存在缓存文件
//
//        ImageOptions imageOptions = new ImageOptions.Builder()
//                .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
//                //.setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
//                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)//缩放
//                .setLoadingDrawableId(R.drawable.icon_default_avatar)//加载中默认显示图片
//                .setCircular(true)
//                .setUseMemCache(true)
//                .setFailureDrawableId(R.drawable.icon_default_avatar)//加载失败后默认显示图片
//                .build();
//        BindImage(view, filename, url, filecachepath,imageOptions);
//    }
//
//    public static void BindImage(ImageView view,String filename, String url,String filecachepath,ImageOptions imageOptions)
//    {
//        //判断是否存在缓存文件
//        File file=new File(filecachepath+filename);
//        if(file.exists()&&file.isFile())
//        {
//            x.image().bind(view,filecachepath+filename,imageOptions);
//            return ;
//        }
//        x.image().bind(view, url + filename ,imageOptions, new Callback.CacheCallback<Drawable>() {
//            @Override
//            public boolean onCache(Drawable result) {
//                return false;
//            }
//
//            @Override
//            public void onSuccess(Drawable result) {
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//
//
//    }
//}
