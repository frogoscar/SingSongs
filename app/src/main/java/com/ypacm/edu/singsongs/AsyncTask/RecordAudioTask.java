package com.ypacm.edu.singsongs.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import com.ypacm.edu.singsongs.fftpack.RealDoubleFFT;

/**
 * Created by DB on 2016/4/6.
 */
public class RecordAudioTask extends AsyncTask<Void, double[], Void> {
    //当年频率范围为4000
    static final int frequency = 8000;
    static final int channelConfig = AudioFormat.CHANNEL_IN_MONO;
    static final int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    static final int BLOCK_SIZE = 1 << 10;

    private boolean startFlag = true;
    private RealDoubleFFT fftTrans;
    public Bitmap bitmap;
    public Canvas canvas;
    private Paint paint;
    private Point point;

    private int width = 720;
    private int height = 1280;

    public RecordAudioTask() {
        fftTrans = new RealDoubleFFT(BLOCK_SIZE);
        bitmap = Bitmap.createBitmap(width, height / 4, Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);
        paint = new Paint();
        point = new Point();
        paint.setColor(Color.GREEN);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            int bufferSize = AudioRecord.getMinBufferSize(frequency,
                    channelConfig, audioFormat);
            Log.v("bufSize", String.valueOf(bufferSize));
            //录音 MediaRecorder.AudioSource.MIC
            //只录音去掉播放的音乐 MediaRecorder.AudioSource.VOICE_COMMUNICATION
            AudioRecord audioRecord = new AudioRecord(
                    MediaRecorder.AudioSource.DEFAULT, frequency,
                    channelConfig, audioFormat, bufferSize);

            short[] audioBuffer = new short[BLOCK_SIZE];
            double[] toTrans = new double[BLOCK_SIZE];

            audioRecord.startRecording();

            while (startFlag) {
                int result = audioRecord.read(audioBuffer, 0, BLOCK_SIZE);
                int maxn = 0;
                //audioBuffer为mic采集音频信号，复习数字信号处理，写出自己的FFT
                for (int i = 0; i < BLOCK_SIZE && i < result; i++) {
                    if (audioBuffer[i] > maxn)
                        maxn = audioBuffer[i];
                }
                for (int i = 0; i < BLOCK_SIZE && i < result; i++) {
                    toTrans[i] = (double) audioBuffer[i] / Short.MAX_VALUE;
                    //采集到的音频为short类型，除以short的最大值，归一化
                }

                //publishProgress(toTrans);
                fftTrans.ft(toTrans);
                publishProgress(toTrans);
            }
            audioRecord.stop();
        } catch (Throwable t) {
            Log.e("AudioRecord", "Recording failed");
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(double[]... values) {

        canvas.drawColor(Color.BLACK);
        int maxn = 0;
        for (int i = 0; i < values[0].length; i++) {
            if (values[0][i] > maxn) {
                maxn = i * 4;
            }
            int x;
            x = width / values[0].length * (i + 1);
            int downy = (int) (height / 4 - (values[0][i] * 10));
            //频率为 i*frequency/BLOCK_SIZE
            int upy = height / 4;

            canvas.drawLine(x, downy, x, upy, paint);
        }
        Log.d("Hz",Integer.toString(maxn));
    }
}