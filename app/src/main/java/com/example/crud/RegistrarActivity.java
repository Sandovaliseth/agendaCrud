package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.db.DbContactos;
import com.example.crud.db.DbUsuarios;
import com.example.crud.entidades.Usuarios;

public class RegistrarActivity extends AppCompatActivity {

    EditText nombre, apellido, correo, contrasena;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nombre = findViewById(R.id.txtNombreU);
        apellido = findViewById(R.id.txtApellidoU);
        correo = findViewById(R.id.txtCorreo);
        contrasena = findViewById(R.id.txtContrasena);
        registrar = findViewById(R.id.btnRegistrar);
        Usuarios usuarios = new Usuarios();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUsuarios dbUsuarios = new DbUsuarios(RegistrarActivity.this);

                String nombreU = nombre.getText().toString();
                usuarios.setNombre(nombreU);
                String apellidoU = apellido.getText().toString();
                usuarios.setNombre(apellidoU);
                String correoU = correo.getText().toString();
                usuarios.setNombre(correoU);
                String contrasenaU = contrasena.getText().toString();
                usuarios.setNombre(contrasenaU);
                long id= dbUsuarios.insertarUsuario(usuarios);
                if (id>0){
                    Toast.makeText(RegistrarActivity.this, "Registro guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else if (correoU==correoU) {
                    Toast.makeText(RegistrarActivity.this, "El correo electronico ya existe", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegistrarActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void limpiar(){
        nombre.setText("");
        apellido.setText("");
        correo.setText("");
        contrasena.setText("");
    }
}