package com.ypacm.edu.singsongs;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ypacm.edu.singsongs.fragment.LyricFragment;
import com.ypacm.edu.singsongs.fragment.MediaFragment;
import com.ypacm.edu.singsongs.fragment.RadioFragment;

import java.io.IOException;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private MediaPlayer mMediaPlayer;
    private Visualizer mVisualizer;
    public Context mContext;
    static final int BLOCK_SIZE = 1 << 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        final MediaFragment mediaFragment = new MediaFragment();
        beginTransaction.add(R.id.ll_media, mediaFragment, "mediaFragment");
        RadioFragment radioFragment = new RadioFragment();
        beginTransaction.add(R.id.ll_pacman, radioFragment, "radioFragment");
        LyricFragment lyricFragment = new LyricFragment();
        beginTransaction.add(R.id.ll_lyric, lyricFragment, "lyricFragment");
        beginTransaction.commit();


        mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.standard_tone);
//        mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.libai2);
        mMediaPlayer.setLooping(true);
        final int maxCR = Visualizer.getMaxCaptureRate();
        mVisualizer = new Visualizer(mMediaPlayer.getAudioSessionId());
//        mVisualizer.setCaptureSize(BLOCK_SIZE);
        mVisualizer.setDataCaptureListener(
                new Visualizer.OnDataCaptureListener() {
                    public void onWaveFormDataCapture(Visualizer visualizer,
                                                      byte[] bytes, int samplingRate) {
                    }

                    public void onFftDataCapture(Visualizer visualizer,
                                                 byte[] fft, int samplingRate) {
                        Log.i(TAG,""+ mVisualizer.getCaptureSize());
                        Log.i(TAG,""+ samplingRate);
                        mediaFragment.MyDraw(fft,samplingRate);
                    }
                }, maxCR / 2, false, true);

        mVisualizer.setEnabled(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //空的音频会放不起来

                if (mMediaPlayer != null) {
                    mMediaPlayer.start();
                } else {
                    Toast.makeText(MainActivity.this, "音乐加载不成功", Toast.LENGTH_LONG).show();
                }
            }
        }).start();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
