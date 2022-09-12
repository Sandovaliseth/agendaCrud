package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crud.adaptadores.listaContactosAdapter;
import com.example.crud.db.DbContactos;
import com.example.crud.db.DbHelper;
import com.example.crud.entidades.Contactos;
import com.example.crud.entidades.SharePreference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreference;
    /*Definir variables
    Button btnCrear;
     */
    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreference = getSharedPreferences("bd_shared", Context.MODE_PRIVATE);

        /*llamar o asignar el boton
        btnCrear= findViewById(R.id.btnCrear);
        */
        listaContactos= findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        DbContactos dbContactos = new DbContactos(MainActivity.this);
        listaArrayContactos = new ArrayList<>();
        listaContactosAdapter adapter = new listaContactosAdapter(dbContactos.mostrarContactos());
        listaContactos.setAdapter(adapter);

        /* cuando se oprima
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LLamar nuestra clase y crear instancia
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                //Crear
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db!=null){
                    Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(MainActivity.this, "Error al crer base de datos", Toast.LENGTH_LONG).show();
                }
            }
        }); */
    }

    //Acceder o mostrar la vista del menu o los puntos
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    //Mostrar todas las opciones que tiene el menu
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            case R.id.cerrarSesion:
                CerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Pasar de una vista a otra, o indicar cual vista es la de dicha opcion
    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    private void CerrarSesion(){
        sharedPreference.edit().clear().apply();

        Toast.makeText(MainActivity.this, "Sesion finalizada", Toast.LENGTH_LONG
        ).show();
        Intent intent = new Intent(this, LoginActivity.class);
        //Cerrar lo anterior y salirse de la app
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}