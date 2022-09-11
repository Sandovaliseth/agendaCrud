package com.example.crud.entidades;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreference {
     Context context;
     SharedPreferences.Editor editor;
     SharedPreferences sp;

    public SharePreference(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("bd_shared", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public String getSharedPreferences() {
        return sp.getString("dato", "No se encontro dato");
    }

    public void setSharedPreferences(String datoG) {
        editor.putString("dato", datoG);
        editor.apply();
    }
}
