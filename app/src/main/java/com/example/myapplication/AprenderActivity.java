package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityAprenderBinding;

public class AprenderActivity extends DrawerBaseActivity {

    ActivityAprenderBinding activityAprenderBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAprenderBinding = activityAprenderBinding.inflate(getLayoutInflater());
        setContentView(activityAprenderBinding.getRoot());
    }
}