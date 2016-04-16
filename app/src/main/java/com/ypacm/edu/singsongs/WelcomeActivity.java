package com.ypacm.edu.singsongs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ypacm.edu.singsongs.util.ImageLoader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by DB on 2016/4/14.
 * app欢迎界面，用于加载以及初始化
 */
public class WelcomeActivity extends AppCompatActivity {

    private int width;
    private int height;
    private ImageView imageView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView = (ImageView) findViewById(R.id.iv_welcome);

        SharedPreferences pref = getSharedPreferences("configure", MODE_PRIVATE);
        width = pref.getInt("width", 0);
        height = pref.getInt("height", 0);
        if (width == 0 && height == 0) {

            WindowManager wm = this.getWindowManager();
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            width = dm.widthPixels;
            height = dm.heightPixels;
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("width", width);
            editor.putInt("height", height);
            editor.commit();
        }


        imageView.setTag("http://img.mukewang.com/551b92340001c9f206000338.jpg");
        new ImageLoader().showImageByThread(imageView, "http://img.mukewang.com/551b92340001c9f206000338.jpg");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        };
        timer.schedule(task, 1000 * 1);
    }
}
