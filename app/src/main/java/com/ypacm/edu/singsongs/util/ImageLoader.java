package com.ypacm.edu.singsongs.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DB on 2016/1/28.
 */
public class ImageLoader {

    private ImageView mImageView;
    private String mUrl;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    public void showImageByThread(ImageView imageView, final String url) {
        mImageView = imageView;
        mUrl = url;
        new Thread() {

            @Override
            public void run() {
                Bitmap bitmap = getBitmapFromURL(url);
                mImageView.setTag(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
//    public void showImageByAsyncTask(ImageView imageView,String url) {
//        new NewsAsyncTack(imageView,url).execute(url);
//    }
//
//    public class NewsAsyncTack extends AsyncTask<String,Void,Bitmap> {
//
//        private  ImageView mImageView;
//        private String mUrl;
//        public NewsAsyncTack(ImageView imageView,String url) {
//            mImageView = imageView;
//            mUrl = url;
//        }
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            return getBitmapFromURL(params[0]);
//        }
//
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//            if(mImageView.getTag().equals(mUrl)) {
//                mImageView.setImageBitmap(bitmap);
//            }
//        }
//    }
}
