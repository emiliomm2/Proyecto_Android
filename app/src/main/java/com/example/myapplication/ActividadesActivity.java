package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityActividadesBinding;

public class ActividadesActivity extends DrawerBaseActivity {

    ActivityActividadesBinding activityActividadesBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityActividadesBinding = activityActividadesBinding.inflate(getLayoutInflater());
        setContentView(activityActividadesBinding.getRoot());
    }
}