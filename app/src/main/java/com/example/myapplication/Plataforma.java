package com.example.myapplication;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.myapplication.databinding.ActivityPlataformaBinding;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Plataforma extends DrawerBaseActivity {
    ActivityPlataformaBinding activityPlataformaBinding;
    VideoView videoView;
    MediaController mediaController;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPlataformaBinding = activityPlataformaBinding.inflate(getLayoutInflater());
        setContentView(activityPlataformaBinding.getRoot());

        videoView = findViewById(R.id.videoView);

        String path="android.resource://"+getPackageName()+"/"+R.raw.video_introduccion;
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();

        if (this.mediaController == null){
            this.mediaController = new MediaController(Plataforma.this);

            this.mediaController.setAnchorView(videoView);

            this.videoView.setMediaController(mediaController);
        }

        this.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.seekTo(position);
                if (position== 0){
                    videoView.start();
                }

                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

    }
}