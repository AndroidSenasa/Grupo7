package com.cibertec.grupo7.pizzahut.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.cibertec.grupo7.pizzahut.entities.Locales;
import com.cibertec.grupo7.pizzahut.entities.Provincias;

import java.util.ArrayList;

/**
 * Created by bgeek05 on 11/09/2015.
 */
public class LocalesDao {

    public ArrayList<Locales> listLocales(Provincias provincia) {
    ArrayList<Locales> lstLocales = new ArrayList<>();
    Cursor cursor = null;

    try {
        String sql = "Select idlocal,nombre,latitud,longitud,direccion From Locales Where idprovincia In (Select idprovincia From Provincias Where provincia = '" +
                provincia.getProvincia() + "' And distrito = '" + provincia.getDistrito() + "')";
        cursor = DataBaseHelper.myDataBase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Locales locales = new Locales();
                locales.setIdlocal(cursor.isNull(cursor.getColumnIndex("idlocal")) ? 0 : cursor.getInt(cursor.getColumnIndex("idlocal")));
                locales.setNombre(cursor.isNull(cursor.getColumnIndex("nombre")) ? "" : cursor.getString(cursor.getColumnIndex("nombre")));
                locales.setLatitud(cursor.isNull(cursor.getColumnIndex("latitud")) ? "" : cursor.getString(cursor.getColumnIndex("latitud")));
                locales.setLongitud(cursor.isNull(cursor.getColumnIndex("longitud")) ? "" : cursor.getString(cursor.getColumnIndex("longitud")));
                locales.setDireccion(cursor.isNull(cursor.getColumnIndex("direccion")) ? "" : cursor.getString(cursor.getColumnIndex("direccion")));
                lstLocales.add(locales);
            } while (cursor.moveToNext());
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        if (cursor != null)
            cursor.close();
    }

    return lstLocales;
}

    public void addLocales(Locales local) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("nombre", local.getNombre());
            cv.put("latitud", local.getLatitud());
            cv.put("longitud", local.getLongitud());
            cv.put("direccion", local.getDireccion());
            DataBaseHelper.myDataBase.insert("Locales", null, cv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateLocal(Locales local) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("nombre", local.getNombre());
            cv.put("latitud", local.getLatitud());
            cv.put("longitud", local.getLongitud());
            cv.put("direccion", local.getDireccion());
            DataBaseHelper.myDataBase.update("Locales", cv, "idlocal = ?", new String[]{String.valueOf(local.getIdlocal())});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteLocal(Locales local) {
        try {
            DataBaseHelper.myDataBase.delete("Locales", "idlocal = ?", new String[]{String.valueOf(local.getIdlocal())});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
