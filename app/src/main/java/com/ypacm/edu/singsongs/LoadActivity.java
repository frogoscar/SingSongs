package com.ypacm.edu.singsongs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.ypacm.edu.singsongs.progressbar.NumberProgressBar;
import com.ypacm.edu.singsongs.progressbar.OnProgressBarListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DB on 2016/4/13.
 */
public class LoadActivity extends AppCompatActivity  implements OnProgressBarListener{
    private Timer timer;

    private int width;
    private int height;
    private Bitmap bitmap;
    private Canvas canvas;
    private ImageView heicat;
    private RelativeLayout layout;
    private NumberProgressBar bnp;
    private int heicatCount = 0;
    private int heicatId[] = {R.drawable.heicat5,
            R.drawable.heicat4,
            R.drawable.heicat3,
            R.drawable.heicat2,
            R.drawable.heicat1};
    private Bitmap heicatBitmap[];
    private Handler handler = new Handler();
    AnimationRunnable runnable = new AnimationRunnable();


    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SharedPreferences pref = getSharedPreferences("configure", MODE_PRIVATE);
        width = pref.getInt("width",0);
        height = pref.getInt("height",0);
        heicat = (ImageView) findViewById(R.id.iv_heicat);
        layout = (RelativeLayout) findViewById(R.id.ll_progress);
        layout.setBackgroundResource(R.drawable.backgroud);
        bnp = (NumberProgressBar) findViewById(R.id.numberbar1);
        bnp.setOnProgressBarListener(this);
        bnp.setProgressTextSize(40);
        bnp.setReachedBarHeight(40);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(1);
                    }
                });
            }
        }, 0, 100);

        heicatBitmap = new Bitmap[heicatId.length];

        handler.postDelayed(runnable, 200);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onProgressChange(int current, int max) {

        heicat.setX(current * width / 100 - heicat.getWidth() - 16);
        if (current == max) {
//            Toast.makeText(getApplicationContext(), getString(R.string.finish), Toast.LENGTH_SHORT).show();
            i = new Intent();
            setResult(RESULT_OK, i);
            LoadActivity.this.finish();
//            startActivity(new Intent(this,MainActivity.class));
//            onDestroy();
        }
    }

    class AnimationRunnable implements Runnable {
        @Override
        public void run() {

            if (heicatBitmap[heicatCount] == null) {
                bitmap = BitmapFactory.decodeResource(getResources(), heicatId[heicatCount]);
                heicatBitmap[heicatCount] = reverseBitmap(bitmap, 0);
            }
//            bitmap = BitmapFactory.decodeResource(getResources(), heicatId[heicatCount]);
            heicat.setImageBitmap(heicatBitmap[heicatCount]);
//            heicat.setImageResource(heicatId[heicatCount]);
            heicatCount = (heicatCount + 1) % 4;
            handler.postDelayed(runnable, 200);
        }
    }


    public static Bitmap reverseBitmap(Bitmap bmp, int flag) {
        float[] floats = null;
        switch (flag) {
            case 0:
                floats = new float[]{-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
                break;
            case 1:
                floats = new float[]{1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f};
                break;
        }

        if (floats != null) {
            Matrix matrix = new Matrix();
            matrix.setValues(floats);
            return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        }

        return null;
    }
}
