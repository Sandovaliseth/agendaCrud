package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud.db.DbContactos;
import com.example.crud.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    //Variables
    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuarda;
    boolean correcto = false;
    Contactos contacto;
    int id=0;
    FloatingActionButton fabEditar, fabEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoElectronico);
        btnGuarda = findViewById(R.id.btn_guardar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        //Recibir variable
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else{
                id = extras.getInt("ID");
            }
        } else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        //Consulta para mostrar los datos del usuario seleccionado
        //Ver detalles
        final DbContactos dbContactos = new DbContactos(EditarActivity.this);
        contacto = dbContactos.verContacto(id);

        if(contacto!=null){
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo_electronico());
        }

        //Guardar modificaciones
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si se hicieron cambios y no esta vacío
                if(!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")){
                    String nombreC = txtNombre.getText().toString();
                    contacto.setNombre(nombreC);
                    String telefonoC = txtTelefono.getText().toString();
                    contacto.setTelefono(telefonoC);
                    String correoC = txtCorreo.getText().toString();
                    contacto.setCorreo_electronico(correoC);
                    correcto= dbContactos.editarContacto(contacto);

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "Registro modificado", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else{
                        Toast.makeText(EditarActivity.this, "Error al modificar registro", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(EditarActivity.this, "Debe llenar los cambios obligatorios", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //Al registrar enviar a otra vista
    private void verRegistro() {
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", contacto.getId());
        startActivity(intent);
    }
}
