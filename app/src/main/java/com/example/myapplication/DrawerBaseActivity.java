package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    TextView usernameTV;

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        drawerLayout.closeDrawer(GravityCompat.START);
        manejarAccionDeNavegacion(item.getItemId());
        return false;
    }

    private void manejarAccionDeNavegacion (int itemId){
        if (itemId == R.id.nav_inicio){
            iniciarNuevaActividad(Plataforma.class);
        } else if (itemId == R.id.nav_aprender) {
            iniciarNuevaActividad(AprenderActivity.class);
        } else if (itemId == R.id.nav_actividades) {
            iniciarNuevaActividad(ActividadesActivity.class);
        } else if (itemId == R.id.nav_notas) {
            iniciarNuevaActividad(NotasActivity.class);
        } else if (itemId == R.id.nav_perfil) {
            iniciarNuevaActividad(PerfilActivity.class);
        } else if (itemId == R.id.nav_cerrar_sesion) {
            this.logout();
        }
    }
    private void iniciarNuevaActividad(Class<?> destinoactividad){
        startActivity(new Intent(this, destinoactividad));
        overridePendingTransition(0,0);
    }

    private void logout(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cerrando sesión");
        progressDialog.show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        onBackPressed();
    }

}