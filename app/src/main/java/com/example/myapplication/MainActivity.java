package com.example.myapplication;

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
import androidx.constraintlayout.widget.ConstraintLayout;
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
//Activity del inicio para iniciar sesión
public class MainActivity extends AppCompatActivity {

    EditText t_email, t_password;

    String email, password;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        t_email = findViewById(R.id.email);
        t_password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
    }

    public void login (View view){
        if(t_email.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese el correo",Toast.LENGTH_SHORT).show();
        } else if (t_password.getText().toString().equals("")){
            Toast.makeText(this, "Completa la contraseña", Toast.LENGTH_SHORT).show();
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Cargando");
            progressDialog.show();

            email = t_email.getText().toString().trim();
            password = t_password.getText().toString().trim();

            //Comprobación con la base de datos a través del php de Android
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.146/proyecto_final/php/login_be_android.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if (response.equalsIgnoreCase("Ingreso correctamente")) {
                        t_email.setText("");
                        t_password.setText("");
                        startActivity(new Intent(getApplicationContext(), Plataforma.class));
                    } else {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);

        }

    }
    //Acceso al registro
    public void registro (View view){
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        finish();
    }
}