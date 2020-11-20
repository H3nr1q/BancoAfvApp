package com.example.bancoafvapp.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Produto implements Parcelable {

    private String codigo;
    private String descricao;
    private Double estoque;
    private String status;
    private Double precoMax;
    private Double precoMin;

    public Produto() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getEstoque() {
        return estoque;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrecoMax() {
        return precoMax;
    }

    public void setPrecoMax(Double precoMax) {
        this.precoMax = precoMax;
    }

    public Double getPrecoMin() {
        return precoMin;
    }

    public void setPrecoMin(Double precoMin) {
        this.precoMin = precoMin;
    }

    public static Creator<Produto> getCREATOR() {
        return CREATOR;
    }

    protected Produto(Parcel in) {
        codigo = in.readString();
        descricao = in.readString();
        if (in.readByte() == 0) {
            estoque = null;
        } else {
            estoque = in.readDouble();
        }
        status = in.readString();
        if (in.readByte() == 0) {
            precoMax = null;
        } else {
            precoMax = in.readDouble();
        }
        if (in.readByte() == 0) {
            precoMin = null;
        } else {
            precoMin = in.readDouble();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigo);
        dest.writeString(descricao);
        if (estoque == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(estoque);
        }
        dest.writeString(status);
        if (precoMax == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(precoMax);
        }
        if (precoMin == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(precoMin);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto= (Produto) o;
        return codigo.equals(produto.codigo);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

}
