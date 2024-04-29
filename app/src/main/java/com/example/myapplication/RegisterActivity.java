package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Registro en la plataforma pasando por el php de registro de Android
public class RegisterActivity extends AppCompatActivity {

    EditText t_nombre_completo, t_email, t_password, t_repite_email, t_repite_password;
    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Entrada de los datos
        t_nombre_completo = (EditText) findViewById(R.id.nombre_completo);
        t_email = (EditText) findViewById(R.id.email);
        t_password = (EditText) findViewById(R.id.password);
        t_repite_email = (EditText) findViewById(R.id.repite_email);
        t_repite_password = (EditText) findViewById(R.id.repite_password);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarDatos();
            }
        });
    }

    //Pasar a texto y quitar los espacios en blanco
    public void insertarDatos() {
        final String nombre_completo = t_nombre_completo.getText().toString().trim();
        final String email = t_email.getText().toString().trim();
        final String password = t_password.getText().toString().trim();
        final String repite_email = t_repite_email.getText().toString().trim();
        final String repite_password = t_repite_password.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);


        if (nombre_completo.isEmpty()){
            t_nombre_completo.setError("Complete los campos");
            return;
        } else if (email.isEmpty()){
            t_email.setError("Complete los campos");
            return;
        } else if (!repite_email.equalsIgnoreCase(email)){
            t_repite_email.setError("Los emails tienen que coincidir");
        } else if (password.length()<8){
            t_password.setError("La contraseña debe tener entre 8 y 16 caracteres");
        } else if (!repite_password.equalsIgnoreCase(password)){
            t_repite_password.setError("Las contraseñas deben ser iguales");
        }

        else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.146/proyecto_final/php/registro_be_android.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("registro completado")) {
                        Toast.makeText(RegisterActivity.this, "datos insertados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    } else {
                        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "No se pudo insertar", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String>params = new HashMap<>();
                    params.put("nombre_completo",nombre_completo);
                    params.put("email",email);
                    params.put("password",password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            requestQueue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void login (View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}