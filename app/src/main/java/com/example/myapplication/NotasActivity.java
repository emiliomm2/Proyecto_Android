package com.example.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityNotasBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class NotasActivity extends DrawerBaseActivity {

    ActivityNotasBinding activityNotasBinding;
    EditText editTextNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNotasBinding = activityNotasBinding.inflate(getLayoutInflater());
        setContentView(activityNotasBinding.getRoot());

        editTextNota = findViewById(R.id.editTextNota);
        findViewById(R.id.btnDescargar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descargarNota();
            }
        });
    }

    //Descarga del apartado de notas
    private void descargarNota() {
        String textoNota = editTextNota.getText().toString();
        if (!textoNota.isEmpty()) {
            try {
                //Creacion de un archivo de texto en el directorio de descargas
                File dirDescargas = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File archivoNota = new File(dirDescargas, "nota.txt");

                //Escritura del texto en el archivo
                FileOutputStream fos = new FileOutputStream(archivoNota);
                fos.write(textoNota.getBytes());
                fos.close();

                editTextNota.setText("");

                Toast.makeText(this, "Nota descargada correctamente", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                //Mensaje de error si ocurre un problema al guardar la nota
                Toast.makeText(this, "Error al descargar la nota", Toast.LENGTH_SHORT).show();
            }
        } else {
            //Mensaje de error si el texto de la nota está vacío
            Toast.makeText(this, "La nota está vacía", Toast.LENGTH_SHORT).show();
        }
    }
}
