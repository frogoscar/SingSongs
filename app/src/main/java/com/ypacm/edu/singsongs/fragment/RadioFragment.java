package com.ypacm.edu.singsongs.fragment;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ypacm.edu.singsongs.R;
import com.ypacm.edu.singsongs.fftpack.RealDoubleFFT;

import java.io.File;
import java.io.IOException;

/**
 * Created by DB on 2016/4/6.
 */
public class RadioFragment extends Fragment {


    private static final String TAG = "RadioFragment";
    private Handler handler = new Handler();
    private ImageView pacMan;


    static final int frequencyMax = 900;
    static final int frequencyMin = 100;
    static final int frequency = 11025;
    static final int channelConfig = AudioFormat.CHANNEL_IN_MONO;
    static final int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    static final int BLOCK_SIZE = 1 << 10;
    private RecordAudioTask task;


    private boolean startFlag = true;
    private RealDoubleFFT fftTrans;

    private float width;
    private float height;

    AnimationRunnable runnable = new AnimationRunnable();
    private int pacmanCount = 0;
    private int pacmanId[] = {R.drawable.pacman_right3, R.drawable.pacman_right2, R.drawable.pacman_right1, R.drawable.pacman_right0,};

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.pacman_fragment, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("configure", getActivity().MODE_PRIVATE);
        width = pref.getInt("width", 0);
        height = pref.getInt("height", 0);
        pacMan = (ImageView) mView.findViewById(R.id.iv_pacman);

        pacMan.setX(width / 4);
        pacMan.setScaleX(2.0f);
        pacMan.setScaleY(2.0f);

        pacMan.setImageResource(R.drawable.pacman_right3);
        handler.postDelayed(runnable, 200);
        fftTrans = new RealDoubleFFT(BLOCK_SIZE);
        task = new RecordAudioTask();
        task.execute();
        return mView;
    }

    private class RecordAudioTask extends AsyncTask<Void, double[], Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                int bufferSize = AudioRecord.getMinBufferSize(frequency,
                        channelConfig, audioFormat);
                //录音 MediaRecorder.AudioSource.MIC
                //只录音去掉播放的音乐 MediaRecorder.AudioSource.VOICE_COMMUNICATION
                AudioRecord audioRecord = new AudioRecord(
                        MediaRecorder.AudioSource.VOICE_COMMUNICATION, frequency,
                        channelConfig, audioFormat, bufferSize);

//                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/reverseme.pcm");
//
//                // Delete any previous recording.
//
//                if (file.exists())
//                    file.delete();
//                try {
//                    file.createNewFile();
//                } catch (IOException e) {
//                    throw new IllegalStateException("Failed to create " + file.toString());
//                }
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
                Log.e(TAG, "Recording failed");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(double[]... values) {
//            canvas.drawColor(Color.BLACK);
            int pos = 0;
            double maxn = Math.abs(values[0][0]);//基频，直流分量
            for (int i = 2; i <  values[0].length; i += 2) {
                double temp = Math.hypot(values[0][i], values[0][i + 1]);
                if (temp > maxn) {
                    maxn = temp;
                    pos = i;
                }
            }
            Log.d(TAG, "maxn:" + maxn);
            float p = height / 2 - (pos / 2 * frequency) / (values[0].length - 1);
            Log.d(TAG, "" + (pos / 2 * frequency) / (values[0].length - 1)+"hz");
            if (maxn > 1) {
                if (p > frequencyMax)
                    p = frequencyMax;
                if (p < frequencyMin)
                    p = frequencyMin;

                ObjectAnimator.ofFloat(pacMan, "translationY", p).start();
                ObjectAnimator.ofFloat(pacMan, "alpha", 1f).start();
                Log.d("pacman", "" + p);
            } else {
                ObjectAnimator.ofFloat(pacMan, "alpha", 0f).start();
            }
        }
    }

    class AnimationRunnable implements Runnable {
        @Override
        public void run() {
            pacMan.setImageResource(pacmanId[pacmanCount]);
            pacmanCount = (pacmanCount + 1) % 4;
            handler.postDelayed(runnable, 200);
        }
    }

    public void setEnabled(boolean statu) {
        startFlag = statu;
    }

}
