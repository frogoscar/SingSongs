package com.ypacm.edu.singsongs.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ypacm.edu.singsongs.animations.Techniques;
import com.ypacm.edu.singsongs.animations.YoYo;
import com.ypacm.edu.singsongs.lyric.LyricUtils;
import com.ypacm.edu.singsongs.R;
import com.ypacm.edu.singsongs.lyric.widget.LyricView;


/**
 * Created by DB on 2016/4/7.
 */
public class LyricFragment extends Fragment {
    private Handler mHandler = new Handler();
    private LyricView lyricView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.lyric_fragment, container, false);

        lyricView = (LyricView) mView.findViewById(R.id.lyricview);
        lyricView.setLyric(LyricUtils.parseLyric(getResources().openRawResource(R.raw.libai), "UTF-8"));
        lyricView.setLyricIndex(0);
        lyricView.play();
//        lyricView.stop();
        lyricView.setOnLyricUpdateListener(new LyricView.OnLyricUpdateListener() {
            @Override
            public void onLyricUpdate() {
//                Toast.makeText(getActivity(),"计算上一句得分",Toast.LENGTH_SHORT).show();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
//                        textView.setText("update");
                        YoYo.with(Techniques.FadeInUp).playOn(mView.findViewById(R.id.test));
                    }
                });
            }
        });
        return mView;
    }
}
