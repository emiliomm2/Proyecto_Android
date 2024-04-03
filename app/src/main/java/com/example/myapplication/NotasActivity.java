package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityNotasBinding;

public class NotasActivity extends DrawerBaseActivity {

    ActivityNotasBinding activityNotasBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNotasBinding = activityNotasBinding.inflate(getLayoutInflater());
        setContentView(activityNotasBinding.getRoot());
    }
}