package com.qf.utillibary.util;

import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 网络请求工具类
 * Created by Ken on 2016/7/25.
 */
public class HttpUtil {

    private static ExecutorService executor = Executors.newFixedThreadPool(5);
    private static Handler handler = new Handler();

    private static byte[] getBytesByUrl(String url){
        InputStream inputStream = null;
        try {
            URL url1 = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setDoInput(true);

            inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024 * 2];
            int length = 0;
            while((length = inputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, length);
            }

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * 下载json的方法
     * @param url
     * @param downLoadListener
     * @return
     */
    public static String downJson(final String url, final DownLoadListener downLoadListener){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = getBytesByUrl(url);
                final String json = new String(bytes);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        downLoadListener.downSucc(url, json);
                    }
                });
            }
        });
        return null;
    }

    public interface DownLoadListener{
        void downSucc(String url, String json);
    }
}
