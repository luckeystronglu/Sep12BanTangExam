package com.qf.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import com.qf.sep12bantangexam.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ken on 2016/9/9.17:09
 */
public class DownUtil {

    /*
    创建一个拥有5个线程的线程池
     */
    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    private OnDownListener onDownListener;

    private Handler handler = new Handler();

    private ImageView iv;

    //设置解析JSON的接口回调
    public DownUtil setOnDownListener(OnDownListener onDownListener) {
        this.onDownListener = onDownListener;
        return this;
    }

    private OnDownImageListener onDownImageListener;

    //设置图片下载的接口
    public DownUtil setOnDownImageListener(OnDownImageListener onDownImageListener) {
        this.onDownImageListener = onDownImageListener;
        return this;
    }

    /**
     * 下载JSON数据
     */
    public void downJSON(final String url){
        executor.submit(new Runnable() {
            @Override
            public void run() {
                //在子线程中执行
                byte[] bytes = requestURL(url);
                if(bytes != null){
                    String json = new String(bytes);
                    //解析JSON
                    if(onDownListener != null){
                        final Object object = onDownListener.paresJson(json);
                        //将解析的结果回传给主线程
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //在主线程中执行
                                onDownListener.downSucc(object);
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * 下载图片
     * @param url
     */
    public void downBitmap(final String url){
        if(iv != null){
            iv.setTag(url);
        }
        executor.submit(new Runnable() {
            @Override
            public void run() {
                //在子线程中执行
                byte[] bytes = requestURL(url);
                if(bytes != null) {
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    if(bitmap != null){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
//                                onDownImageListener.downSuccImg(bitmap);
                                if(url.equals(iv.getTag())){
                                    iv.setImageBitmap(bitmap);
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * 下载资源
     * @return
     */
    private byte[] requestURL(String urlStr){
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(3000);

            conn.connect();

            if(conn.getResponseCode() == 200){
                in = conn.getInputStream();
                out = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024 * 8];
                int len;
                while((len = in.read(buffer)) != -1){
                    out.write(buffer, 0, len);
                }

                //得到下载好的json字符串
                return out.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null){
                    in.close();
                }

                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public DownUtil with(ImageView imageView){
        this.iv = imageView;
        this.iv.setImageResource(R.drawable.bg_personal_wish_item_drawable_day);
        return this;
    }

    /**
     * 接口回调
     */
    public interface OnDownListener{
        //解析JSON时回调
        Object paresJson(String json);

        //解析完成后回调
        void downSucc(Object object);
    }


    /**
     * 下载图片的接口回调
     */
    public interface OnDownImageListener{
        void downSuccImg(Bitmap bitmap);
    }
}
