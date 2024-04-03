package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import com.example.myapplication.databinding.ActivityPlataformaBinding;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Plataforma extends DrawerBaseActivity {
    ActivityPlataformaBinding activityPlataformaBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPlataformaBinding = activityPlataformaBinding.inflate(getLayoutInflater());
        setContentView(activityPlataformaBinding.getRoot());

    }
}