package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.db.DbContactos;
import com.example.crud.entidades.Contactos;

public class NuevoActivity extends AppCompatActivity {

    //Definicr variables
    EditText txtNombre, txtTelefono, txtCorreoElectronico;
    Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        //Asignar las variables correspondientes desde la vista
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        btn_guardar = findViewById(R.id.btn_guardar);
        Contactos contactos = new Contactos();

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                String nombreC = txtNombre.getText().toString();
                contactos.setNombre(nombreC);
                String telefonoC = txtTelefono.getText().toString();
                contactos.setTelefono(telefonoC);
                String correoC = txtCorreoElectronico.getText().toString();
                contactos.setCorreo_electronico(correoC);
                long id= dbContactos.insertarContacto(contactos);

                if(id>0){
                    Toast.makeText(NuevoActivity.this, "Registro guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                    Intent intent = new Intent(NuevoActivity.this, MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(NuevoActivity.this, "Error al guardar registro", Toast.LENGTH_LONG
                    ).show();
                }
            }
        });
    }

    //Metodo para limpiar formulario o dejarlos vacíos cada que se ejecute la aplicación
    private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }
}