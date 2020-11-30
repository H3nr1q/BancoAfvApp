package com.example.bancoafvapp.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.Objects;

public class Cliente implements Parcelable {

    private String codigoCliente;
    private String cpfCnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String telefone1;
    private String telefone2;
    private String emailPrincipal;
    private String emailSecundario;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String codMunicipio;
    private List<Endereco> enderecos;

    public Cliente() {
    }

    protected Cliente(Parcel in) {
        codigoCliente = in.readString();
        cpfCnpj = in.readString();
        razaoSocial = in.readString();
        nomeFantasia = in.readString();
        telefone1 = in.readString();
        telefone2 = in.readString();
        emailPrincipal = in.readString();
        emailSecundario = in.readString();
        endereco = in.readString();
        numero = in.readString();
        complemento = in.readString();
        bairro = in.readString();
        codMunicipio = in.readString();
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String getTelefone2) {
        this.telefone2 = getTelefone2;
    }

    public String getEmailPrincipal() {
        return emailPrincipal;
    }

    public void setEmailPrincipal(String emailPrincipal) {
        this.emailPrincipal = emailPrincipal;
    }

    public String getEmailSecundario() {
        return emailSecundario;
    }

    public void setEmailSecundario(String emailSecundario) {
        this.emailSecundario = emailSecundario;
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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigoCliente);
        dest.writeString(cpfCnpj);
        dest.writeString(razaoSocial);
        dest.writeString(nomeFantasia);
        dest.writeString(telefone1);
        dest.writeString(telefone2);
        dest.writeString(emailPrincipal);
        dest.writeString(emailSecundario);
        dest.writeString(endereco);
        dest.writeString(numero);
        dest.writeString(complemento);
        dest.writeString(bairro);
        dest.writeString(codMunicipio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return codigoCliente.equals(cliente.codigoCliente);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(codigoCliente);
    }


}
