package com.example.myapplication;

public class Video {
    private String titulo;
    private String videoUri;

    public Video(String titulo, String videoUri) {
        this.titulo = titulo;
        this.videoUri = videoUri;
    }
    //Getter y Setter de las variables de videos
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }
}

