package com.example.bancoafvapp.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Endereco implements Parcelable {

    private Integer codigoEndereco;
    private String codigoCliente;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String estado;
    private String codMunicipio;

    public Endereco() {
    }

    protected Endereco(Parcel in) {
        if (in.readByte() == 0) {
            codigoEndereco = null;
        } else {
            codigoEndereco = in.readInt();
        }
        codigoCliente = in.readString();
        endereco = in.readString();
        numero = in.readString();
        complemento = in.readString();
        bairro = in.readString();
        estado = in.readString();
        codMunicipio = in.readString();
    }

    public static final Creator<Endereco> CREATOR = new Creator<Endereco>() {
        @Override
        public Endereco createFromParcel(Parcel in) {
            return new Endereco(in);
        }

        @Override
        public Endereco[] newArray(int size) {
            return new Endereco[size];
        }
    };

    public Integer getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Integer codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
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
        if (codigoEndereco == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(codigoEndereco);
        }
        dest.writeString(codigoCliente);
        dest.writeString(endereco);
        dest.writeString(numero);
        dest.writeString(complemento);
        dest.writeString(bairro);
        dest.writeString(estado);
        dest.writeString(codMunicipio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return codigoEndereco.equals(endereco.codigoEndereco);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(codigoEndereco);
    }
}
