package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud.db.DbUsuarios;
import com.example.crud.entidades.SharePreference;
import com.example.crud.entidades.Usuarios;

public class LoginActivity extends AppCompatActivity {

    EditText correo, contrasena;
    Button inicarSesion;
    TextView irRegistrar;
    DbUsuarios dbUsuarios;
    Usuarios usuarios;
    CheckBox GuardarCredenciales;
    SharePreference sharePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = findViewById(R.id.txtCorreoLogin);
        contrasena = findViewById(R.id.txtContrasenaLogin);
        inicarSesion = findViewById(R.id.btnIniciar);
        GuardarCredenciales = findViewById(R.id.GuardarCredenciales);
        sharePreference= new SharePreference(LoginActivity.this);

        dbUsuarios = new DbUsuarios(this);
        usuarios = new Usuarios();

        inicarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(correo.getText().toString().equals("")) {
                        Toast.makeText(LoginActivity.this, "Ingrese el correo", Toast.LENGTH_LONG).show();
                } else if (contrasena.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Ingrese la contraseña", Toast.LENGTH_LONG
                    ).show();
                } else {

                    String email= correo.getText().toString();
                    usuarios.setCorreo(email);
                    String contrasenaV= contrasena.getText().toString();
                    usuarios.setContrasena(contrasenaV);

                    Boolean validarCC = dbUsuarios.obtenerCorreoycontra(usuarios);
                    if(validarCC==true){
                        //Guardar las credenciales del usuario logueado
                        sharePreference.guardarSesion(GuardarCredenciales.isChecked());
                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG
                        ).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error de credenciales", Toast.LENGTH_LONG
                        ).show();
                    }
                }
            }
        });

        if(sharePreference.revisarSesion()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Iniciar sesion", Toast.LENGTH_LONG
            ).show();
        }

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