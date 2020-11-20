package com.example.bancoafvapp.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Municipio implements Parcelable {

    private String cogigo;
    private String nome;
    private String estado;

    public Municipio() {}

    protected Municipio(Parcel in) {
        cogigo = in.readString();
        nome = in.readString();
        estado = in.readString();
    }

    public static final Creator<Municipio> CREATOR = new Creator<Municipio>() {
        @Override
        public Municipio createFromParcel(Parcel in) {
            return new Municipio(in);
        }

        @Override
        public Municipio[] newArray(int size) {
            return new Municipio[size];
        }
    };

    public String getCogigo() {
        return cogigo;
    }

    public void setCogigo(String cogigo) {
        this.cogigo = cogigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cogigo);
        dest.writeString(nome);
        dest.writeString(estado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Municipio municipio = (Municipio) o;
        return cogigo.equals(municipio.cogigo);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(cogigo);
    }
}
