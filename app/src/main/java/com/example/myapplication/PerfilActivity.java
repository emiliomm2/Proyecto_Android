package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivityPerfilBinding;

public class PerfilActivity extends DrawerBaseActivity {

    ActivityPerfilBinding activityPerfilBinding;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String PREF_PHOTO = "photo";


    private ImageView imageViewPerfil;
    private TextView textViewFrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPerfilBinding = activityPerfilBinding.inflate(getLayoutInflater());
        setContentView(activityPerfilBinding.getRoot());;

        imageViewPerfil = findViewById(R.id.imageViewPerfil);
        textViewFrase = findViewById(R.id.textViewFrase);

        // Cargar datos del usuario
        cargarDatosUsuario();

        imageViewPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoSeleccionImagen();
            }
        });
    }

    private void cargarDatosUsuario() {
        // Obtener el correo electrónico guardado en las preferencias compartidas
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        int photoId = prefs.getInt(PREF_PHOTO, R.drawable.planta);

        imageViewPerfil.setImageResource(photoId);

        String frase = "Cuando las cosas se pongan difíciles, recuerda que el crecimiento ocurre fuera de la zona de confort.";
        textViewFrase.setText(frase);
        textViewFrase.setTypeface(textViewFrase.getTypeface(), Typeface.ITALIC);
    }

    private void mostrarDialogoSeleccionImagen() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar imagen de perfil");
        builder.setItems(new CharSequence[]{"Planta", "Hombre", "Mujer"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int photoId;
                switch (which) {
                    case 0: // Planta
                        photoId = R.drawable.planta;
                        break;
                    case 1: // Hombre
                        photoId = R.drawable.hombre;
                        break;
                    case 2: // Mujer
                        photoId = R.drawable.mujer;
                        break;
                    default:
                        photoId = R.drawable.planta;
                        break;
                }
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt(PREF_PHOTO, photoId);
                editor.apply();

                // Actualizar la imagen del perfil
                imageViewPerfil.setImageResource(photoId);
            }
        });
        builder.show();
    }
}