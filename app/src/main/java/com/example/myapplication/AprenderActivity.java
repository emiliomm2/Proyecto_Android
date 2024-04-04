package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ActivityAprenderBinding;

import java.util.ArrayList;
import java.util.List;

public class AprenderActivity extends DrawerBaseActivity {

    ActivityAprenderBinding activityAprenderBinding;
    List<Video> listaVideos;
    RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAprenderBinding = activityAprenderBinding.inflate(getLayoutInflater());
        setContentView(activityAprenderBinding.getRoot());
        listaVideos = obtenerListaVideos();


        VideoAdapter adapter = new VideoAdapter(this, listaVideos);
        activityAprenderBinding.rvVideos.setAdapter(adapter);
        lm = new LinearLayoutManager(this);
        activityAprenderBinding.rvVideos.setLayoutManager(lm);

    }

    private List<Video> obtenerListaVideos() {
        List<Video> lista = new ArrayList<>();
        lista.add(new Video(R.drawable.js, "JavaScript"));
        lista.add(new Video(R.drawable.html, "HTML5"));
        lista.add(new Video(R.drawable.css, "CSS3"));
        return lista;
    }

}