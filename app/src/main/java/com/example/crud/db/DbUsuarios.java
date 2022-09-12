package com.example.crud.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.crud.entidades.Usuarios;

public class DbUsuarios extends SQLiteOpenHelper {

    Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "agendaU.db";
    public static final String TABLE_USUARIOS = "t_usuarios";

    public DbUsuarios(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL," +
                "apellido TEXT," +
                "correo TEXT NOT NULL," +
                "contrasena TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    public long insertarUsuario(Usuarios usuarios) {
        long id = 0;
        try {
            DbUsuarios dbUsuarios = new DbUsuarios(context);
            SQLiteDatabase db = dbUsuarios.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", usuarios.getNombre());
            values.put("apellido", usuarios.getApellido());
            values.put("correo", usuarios.getCorreo());
            values.put("contrasena", usuarios.getContrasena());
            id = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

}
