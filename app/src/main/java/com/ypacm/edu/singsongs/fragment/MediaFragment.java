package com.ypacm.edu.singsongs.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ypacm.edu.singsongs.MainActivity;
import com.ypacm.edu.singsongs.R;

/**
 * Created by DB on 2016/4/7.
 */
public class MediaFragment extends Fragment {

    public Bitmap bitmap;
    public Canvas canvas;
    private Paint paint;
    private Point point;

    private int width = 1080;
    private int height = 1920;

    private int drawCount = 0;
    private int len = 50;
    private int drawPoint[] = new int[len];
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.game_backgroud, container, false);
        imageView = (ImageView) mView.findViewById(R.id.iv_backgroud);
//        imageView.setImageResource(R.drawable.ic_menu_camera);
        bitmap = Bitmap.createBitmap(width, height / 2, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();;
        paint.setColor(Color.GREEN);
        imageView.setImageBitmap(bitmap);

        return mView;
    }

    public void MyDraw(byte[] values, int samplingRate) {
        canvas.drawColor(Color.BLACK);
        int pos = 0;
//        matlab画图
//        x = audioread('440Hz_44100Hz_16bit_05sec.mp3');
//        y = x;
//        N = length(y); %默认1024
//        plot(1:N,y)
//        fs = 44100;%单位是Hz samplingRate单位为mHz
//        df=fs/(N-1);
//        f=(0:N-1)*df;%f = n * fs /(n-1)
//        Y=fft(y)/N*2;%某频率的能量
//        plot(f(1:N/2),abs(Y(1:N/2)));

        double maxn = Math.abs(values[0]);//基频，直流分量
        for (int i = 2; i < values.length; i += 2) {
            double temp = Math.hypot(values[i], values[i + 1]);
            if (temp > maxn) {
                maxn = temp;
                pos = i;
            }
        }
//        file:///E:/adt-bundle-windows-x86-20131030/sdk/docs/reference/android/media/audiofx/Visualizer.html
//        频率计算有误
        int position = (pos / 2 * (samplingRate / 1000)) / (values.length - 1);
        drawPoint[drawCount] = (height / 2 - (position));
        for (int i = 0; i < len; i++) {
            canvas.drawLine((len - i - 1) * width / len, drawPoint[(drawCount - i + len) % len], (len - i) * width / len, drawPoint[(drawCount - i + len) % len], paint);
        }
        drawCount = (drawCount + 1) % len;
        Log.d("Hz", "pos: " + pos + "positon:" + position);
        imageView.invalidate();
    }
}
