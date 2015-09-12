package com.cibertec.grupo7.pizzahut.dao;

import android.database.Cursor;

import com.cibertec.grupo7.pizzahut.entities.Locales;
import com.cibertec.grupo7.pizzahut.entities.Provincias;

import java.util.ArrayList;

/**
 * Created by bgeek05 on 11/09/2015.
 */
public class ProvinciasDao {

    public ArrayList<Provincias> listProvinciasSpn() {
        ArrayList<Provincias> lstProvincias = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = DataBaseHelper.myDataBase.rawQuery("Select Distinct provincia From Provincias", null);

            if (cursor.moveToFirst()) {
                do {
                    Provincias provincia = new Provincias();
                    provincia.setProvincia(cursor.isNull(cursor.getColumnIndex("provincia")) ? "" : cursor.getString(cursor.getColumnIndex("provincia")));
                    lstProvincias.add(provincia);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return lstProvincias;
    }

    public ArrayList<Provincias> listDistritosSpn(Provincias provincias) {
        ArrayList<Provincias> lstProvincias = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = DataBaseHelper.myDataBase.rawQuery("Select Distinct distrito From Provincias Where provincia = ?", new String[]{provincias.getProvincia()});

            if (cursor.moveToFirst()) {
                do {
                    Provincias provincia = new Provincias();
                    provincia.setDistrito(cursor.isNull(cursor.getColumnIndex("distrito")) ? "" : cursor.getString(cursor.getColumnIndex("distrito")));
                    lstProvincias.add(provincia);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return lstProvincias;
    }
}
