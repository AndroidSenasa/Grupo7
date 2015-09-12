package com.cibertec.grupo7.pizzahut.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bgeek05 on 10/09/2015.
 */
public class Locales implements Parcelable {
    private int idlocal;
    private String nombre;
    private String latitud;
    private String longitud;
    private String direccion;

    public Locales() {
    }

    public int getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(int idlocal) {
        this.idlocal = idlocal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    protected Locales(Parcel in) {
        idlocal = in.readInt();
        nombre = in.readString();
        latitud = in.readString();
        longitud = in.readString();
        direccion = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idlocal);
        dest.writeString(nombre);
        dest.writeString(latitud);
        dest.writeString(longitud);
        dest.writeString(direccion);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Locales> CREATOR = new Parcelable.Creator<Locales>() {
        @Override
        public Locales createFromParcel(Parcel in) {
            return new Locales(in);
        }

        @Override
        public Locales[] newArray(int size) {
            return new Locales[size];
        }
    };
}