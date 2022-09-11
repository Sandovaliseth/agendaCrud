package com.example.crud.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "agenda.db";
    public static final String TABLE_CONTACTOS = "t_contactos";

    //Constructor
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    //CREAR LA BASE DE DATOS
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo_electronico TEXT)");
    }

    /* En el caso de que algun nombre de la tabla quede mal poner
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_CONTACTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo_electronico TEXT)");
    }
     */

    //Eliminar y volver a agregar bd
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTACTOS);
        onCreate(sqLiteDatabase);
    }
}
