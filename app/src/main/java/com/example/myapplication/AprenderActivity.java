package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityAprenderBinding;
import com.example.myapplication.databinding.ActivityPlataformaBinding;

public class AprenderActivity extends DrawerBaseActivity {

    ActivityAprenderBinding activityAprenderBinding;
    private Button btnVideo1, btnVideo2, btnVideo3;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAprenderBinding = activityAprenderBinding.inflate(getLayoutInflater());
        setContentView(activityAprenderBinding.getRoot());

        // Inicializar los botones y el VideoView
        btnVideo1 = findViewById(R.id.btnVideo1);
        btnVideo2 = findViewById(R.id.btnVideo2);
        btnVideo3 = findViewById(R.id.btnVideo3);
        videoView = findViewById(R.id.videoView);

        // Configurar los listeners para los botones
        btnVideo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirVideo(R.raw.video1);
            }
        });

        btnVideo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirVideo(R.raw.video2);
            }
        });

        btnVideo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirVideo(R.raw.video3);
            }
        });
    }

    private void reproducirVideo(int videoRawId) {
        // Obtener la URI del vídeo seleccionado
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videoRawId);

        // Configurar el VideoView para reproducir el vídeo seleccionado
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

}

