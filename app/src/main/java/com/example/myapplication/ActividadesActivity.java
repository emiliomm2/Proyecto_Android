package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityActividadesBinding;
import com.example.myapplication.databinding.ActivityPlataformaBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ActividadesActivity extends DrawerBaseActivity {

    ActivityActividadesBinding activityActividadesBinding;
    private static final String PDF_DIRECTORY = "pdf_files";
    private static final int REQUEST_CODE_OPEN_PDF = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityActividadesBinding = activityActividadesBinding.inflate(getLayoutInflater());
        setContentView(activityActividadesBinding.getRoot());


        // Configurar los botones de actividad
        configureActivityButton(R.id.btnActividad1, "actividad1.pdf");
        configureActivityButton(R.id.btnActividad2, "actividad2.pdf");

        // Configurar el botón de subir actividad
        findViewById(R.id.btnSubirActividad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para subir la actividad
                // Puedes implementar la lógica necesaria para subir la actividad aquí
                // Por ejemplo, podrías llamar a un método para subir la actividad
                uploadPDF();
            }
        });
    }

    private void configureActivityButton(int buttonId, final String pdfFileName) {
        findViewById(buttonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadPDF(pdfFileName);
            }
        });
    }

    private void downloadPDF(String fileName) {
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = assetManager.open(PDF_DIRECTORY + File.separator + fileName);

            // Crear el archivo destino en el directorio de descargas
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!downloadsDir.exists()) {
                // Si la carpeta de descargas no existe, intenta crearla
                if (!downloadsDir.mkdirs()) {
                    // Si no se puede crear la carpeta, muestra un mensaje de error y regresa
                    Toast.makeText(this, "Error al crear la carpeta de descargas", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            File outputFile = new File(downloadsDir, fileName);

            // Copiar el contenido del archivo PDF al archivo de destino
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Notificar al usuario que la descarga se ha completado
            Toast.makeText(this, "PDF descargado correctamente: " + fileName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar cualquier error que pueda ocurrir durante la descarga
            Toast.makeText(this, "Error al descargar el PDF", Toast.LENGTH_SHORT).show();
        } finally {
            // Cerrar los flujos de entrada y salida
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadPDF() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_OPEN_PDF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPEN_PDF && resultCode == RESULT_OK && data != null) {
            // Aquí puedes manejar el archivo PDF seleccionado
            // Por ejemplo, obtén la URI del archivo seleccionado
            Uri selectedFileUri = data.getData();
            if (selectedFileUri != null) {
                Toast.makeText(this, "Archivo PDF seleccionado: " + selectedFileUri.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}



