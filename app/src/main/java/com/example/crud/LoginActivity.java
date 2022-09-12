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

        inicarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(correo.getText().toString().equals("")) {
                        Toast.makeText(LoginActivity.this, "Ingrese el correo", Toast.LENGTH_LONG).show();
                } else if (contrasena.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Ingrese la contraseña", Toast.LENGTH_LONG
                    ).show();
                } else {
                    String usuario= correo.getText().toString();
                    String contrasenaValidar= contrasena.getText().toString();

                    if(usuario.equals("santi@gmail.com") && contrasenaValidar.equals("123")){
                        //Guardar las credenciales del usuario logueado
                        sharePreference.guardarSesion(GuardarCredenciales.isChecked());
                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG
                        ).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                        startActivity(intent);
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