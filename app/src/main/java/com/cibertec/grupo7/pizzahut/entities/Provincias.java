package com.cibertec.grupo7.pizzahut.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bgeek05 on 11/09/2015.
 */
public class Provincias implements Parcelable {

    public int idprovincia;
    public String provincia;
    public String distrito;
    public String ubigeo;

    public Provincias() {
    }

    public int getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    protected Provincias(Parcel in) {
        idprovincia = in.readInt();
        provincia = in.readString();
        distrito = in.readString();
        ubigeo = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idprovincia);
        dest.writeString(provincia);
        dest.writeString(distrito);
        dest.writeString(ubigeo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Provincias> CREATOR = new Parcelable.Creator<Provincias>() {
        @Override
        public Provincias createFromParcel(Parcel in) {
            return new Provincias(in);
        }

        @Override
        public Provincias[] newArray(int size) {
            return new Provincias[size];
        }
    };
}