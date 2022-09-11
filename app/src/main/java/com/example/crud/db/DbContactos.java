package com.example.crud.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.crud.NuevoActivity;
import com.example.crud.entidades.Contactos;

import java.util.ArrayList;

public class DbContactos extends DbHelper {

    Context context;

    public DbContactos(@Nullable Context context){
        super(context);
        this.context = context;
    }

    //Insertar contactos
    public long insertarContacto(Contactos contactos){
        long id=0;
        try{
            DbHelper dbHelper= new DbHelper(context);
            SQLiteDatabase db= dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", contactos.getNombre());
            values.put("telefono", contactos.getTelefono());
            values.put("correo_electronico", contactos.getCorreo_electronico());
            id = db.insert(TABLE_CONTACTOS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    //Mostrar o visualizar contactos dentro de una lista
    public ArrayList<Contactos> mostrarContactos(){
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);

        if(cursorContactos.moveToFirst()){
            do{
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo_electronico(cursorContactos.getString(3));
                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
        }
        cursorContactos.close();
        return listaContactos;
    }

    //Seleccionar para ver detalle contacto en otra actividad
    public Contactos verContacto(int id){
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db= dbHelper.getWritableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " WHERE id= " + id + " LIMIT 1", null);

        if(cursorContactos.moveToFirst()){
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo_electronico(cursorContactos.getString(3));
        }
        //Cerrar base de datos db
        cursorContactos.close();
        return contacto;
    }

    //Editar contactos o registros
    public boolean editarContacto(Contactos contactos){

        boolean correcto = false;

        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db= dbHelper.getWritableDatabase();

        try{
            db.execSQL("UPDATE " + TABLE_CONTACTOS + " SET nombre= '" +contactos.getNombre()+"', telefono= '" +contactos.getTelefono()+"', correo_electronico= '" + contactos.getCorreo_electronico() +"' WHERE id= '" +contactos.getId()+"'");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close(); //Cerrar la conexion sin importar si se actualiza o no
        }
        return correcto;
    }

    //Eliminar contacto en la base de datos
    public boolean eliminarContacto(Contactos contactos){

        boolean correcto = false;

        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db= dbHelper.getWritableDatabase();

        try{
            db.execSQL("DELETE FROM " + TABLE_CONTACTOS + " WHERE id= '" +contactos.getId()+"'");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close(); //Cerrar la base de datos
        }
        return correcto;
    }
}
