package com.example.myapplication;

public class Video {
    private int miniatura;
    private String titulo;

    public Video(int miniatura, String titulo) {
        this.miniatura = miniatura;
        this.titulo = titulo;
    }

    public int getMiniatura() {
        return miniatura;
    }

    public String getTitulo() {
        return titulo;
    }
}
