package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityPerfilBinding;

public class PerfilActivity extends DrawerBaseActivity {

    ActivityPerfilBinding activityPerfilBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPerfilBinding = activityPerfilBinding.inflate(getLayoutInflater());
        setContentView(activityPerfilBinding.getRoot());
    }
}