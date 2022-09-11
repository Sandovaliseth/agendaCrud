package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud.db.DbUsuarios;
import com.example.crud.entidades.SharePreference;
import com.example.crud.entidades.Usuarios;

public class LoginActivity extends AppCompatActivity {

    EditText correo, contrasena;
    Button inicarSesion;
    DbUsuarios dbUsuarios;
    TextView irRegistrar;
    Usuarios usuarios;

    SharePreference sharePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = findViewById(R.id.txtCorreoLogin);
        contrasena = findViewById(R.id.txtContrasenaLogin);
        inicarSesion = findViewById(R.id.btnIniciar);

        sharePreference= new SharePreference(LoginActivity.this);

        inicarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(correo.getText().toString().equals("")) {
                        Toast.makeText(LoginActivity.this, "Ingrese el correo", Toast.LENGTH_LONG).show();
                } else if (contrasena.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Ingrese la contraseña", Toast.LENGTH_LONG
                    ).show();
                } else {
                    usuarios.getCorreo().equals(correo.getText().toString());
                    usuarios.getContrasena().equals(contrasena.getText().toString());
                    Boolean validar = dbUsuarios.obtenerCorreoContraseña(usuarios);
                    if(validar==true){

                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG
                        ).show();
                        Intent intent = new Intent(LoginActivity.this, NuevoActivity.class);
                        startActivity(intent);

                        //Guardar la información del correo logueado
                        sharePreference.setSharedPreferences(correo.getText().toString());

                    } else {
                        Toast.makeText(LoginActivity.this, "Error de credenciales", Toast.LENGTH_LONG
                        ).show();
                    }
                }
            }
        });

        irRegistrar = findViewById(R.id.irRegistrar);
        irRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(intent);
            }
        });
    }
}