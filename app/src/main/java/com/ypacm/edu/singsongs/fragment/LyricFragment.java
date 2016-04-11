package com.ypacm.edu.singsongs.fragment;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
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
    private float density;
    private float width;
    private float height;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.lyric_fragment, container, false);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;
        density = dm.density;
        width = widthPixels / density;
        height = heightPixels / density;

        lyricView = (LyricView) mView.findViewById(R.id.lyricview);
        lyricView.setLyric(LyricUtils.parseLyric(getResources().openRawResource(R.raw.lovesong), "GB2312"));
        lyricView.setLyricIndex(0);

        lyricView.setOnLyricUpdateListener(new LyricView.OnLyricUpdateListener() {
            @Override
            public void onLyricUpdate() {
//                Toast.makeText(getActivity(),"计算上一句得分",Toast.LENGTH_SHORT).show();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
//                        textView.setText("update");
//                        YoYo.with(Techniques.FadeInUp).playOn(mView.findViewById(R.id.test));

//                        ObjectAnimator.ofFloat(mView, "alpha", 0.7f, 1f).start();
//                        ObjectAnimator.ofFloat(mView, "translationY", mView.getHeight()/8, 0).start();

                        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.7f, 1f);
                        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", mView.getHeight() / 8, 0);
                        ObjectAnimator.ofPropertyValuesHolder(mView, alpha, translationY).start();
                    }
                });
            }
        });
        return mView;
    }

    public void setEnabled(boolean statu) {
        if (statu && lyricView != null)
            lyricView.play();
        else
            lyricView.stop();
    }
}
