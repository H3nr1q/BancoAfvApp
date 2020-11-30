package com.example.bancoafvapp.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bancoafvapp.model.Endereco;
import com.example.bancoafvapp.model.Municipio;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO extends EndDAO<Municipio> {

    public static final String TABLE_MUNICIPIOS = "GUA_MUNICIPIOS";
    public static final String KEY_ESTADO = "MUN_ESTADO";
    public static final String KEY_CODMUNICIPIO = "MUN_CODIGOMUNICIPIO";
    public static final String KEY_NOME = "MUN_NOME";

    public static final String TABLE_ENDERECOS = "GUA_ENDERECOSADICIONAIS";
    public static final String KEY_END_CODIGOCLIENTE = "EAD_CODIGOCLIENTE";
    public static final String KEY_END_ENDERECO = "EAD_ENDERECO";
    public static final String KEY_END_NUMERO = "EAD_NUMERO";
    public static final String KEY_END_COMPLEMENTO = "EAD_COMPLEMENTO";
    public static final String KEY_END_BAIRRO = "EAD_BAIRRO";
    public static final String KEY_END_CODIGOMUNICIPIO = "EAD_CODIGOMUNICIPIO";

    public static EnderecoDAO enderecoDAO;

    public EnderecoDAO(){}

    public synchronized static EnderecoDAO getInstance(){

        if (enderecoDAO == null){

            enderecoDAO = new EnderecoDAO();
        }
        return enderecoDAO;
    }

    @Override
    public List<Municipio> selectCitiesByState(String estado) {

        List<Municipio> municipios = new ArrayList<>();
        String sql = " SELECT " + KEY_CODMUNICIPIO + ", " + KEY_NOME + ", " + KEY_ESTADO +
                        " FROM " + TABLE_MUNICIPIOS + " WHERE " + KEY_ESTADO + " LIKE ? ; ";

        Cursor c = getReadableDB().rawQuery(sql, new String[]{"%".concat(estado).concat("%")});

        while (c.moveToNext()){
           municipios.add(bind(c));
        }
        return municipios;
    }

    @Override
    public List<String> selectCitiesByName(String cidade) {

        List<String> cidades = new ArrayList<>();
        String sql = " SELECT " + KEY_NOME + " FROM " + TABLE_MUNICIPIOS +
            " WHERE " + KEY_NOME + " LIKE ? ";
        Cursor c = getReadableDB().rawQuery(sql, new String[]{"%".concat(cidade)});

        while (c.moveToNext())
            cidades.add(c.getString(c.getColumnIndex(KEY_NOME)));
        return cidades;
    }

    @Override
    public String selectCityNameByCode(String code) {

        String nomeCidade;

        String sql = " SELECT " + KEY_NOME + " FROM " + TABLE_MUNICIPIOS +
                " WHERE " + KEY_CODMUNICIPIO + " LIKE ? ;";
        Cursor c = getReadableDB().rawQuery(sql, new String[]{"%".concat(code).concat("%")});

        nomeCidade = c.getString(c.getColumnIndex(KEY_NOME)).trim();

        return nomeCidade;
    }

    @Override
    protected ContentValues bindValues(Municipio municipio) {
        return null;
    }

    @Override
    protected Municipio bind(Cursor c) {

        Municipio municipio = new Municipio();
        municipio.setCogigo(c.getString(c.getColumnIndex(KEY_CODMUNICIPIO)));
        municipio.setEstado(c.getString(c.getColumnIndex(KEY_ESTADO)));
        municipio.setNome(c.getString(c.getColumnIndex(KEY_NOME)));
        return municipio;
    }

    @Override
    protected ContentValues bindValuesEnd(Endereco endereco) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_END_CODIGOCLIENTE, endereco.getCodigoCliente());
        contentValues.put(KEY_END_NUMERO, endereco.getNumero());
        contentValues.put(KEY_END_ENDERECO, endereco.getEndereco());
        contentValues.put(KEY_END_COMPLEMENTO, endereco.getComplemento());
        contentValues.put(KEY_END_BAIRRO, endereco.getBairro());
        contentValues.put(KEY_END_CODIGOMUNICIPIO, endereco.getCodMunicipio());

        return contentValues;
    }

    @Override
    protected Endereco bindEnd(Cursor c) {

        Endereco endereco = new Endereco();
        endereco.setCodigoCliente(getString(c, KEY_END_CODIGOCLIENTE));
        endereco.setCodMunicipio(getString(c, KEY_END_CODIGOMUNICIPIO));
        endereco.setEndereco(getString(c, KEY_END_ENDERECO));
        endereco.setComplemento(getString(c, KEY_END_COMPLEMENTO));
        endereco.setNumero(getString(c, KEY_END_NUMERO));
        endereco.setBairro(getString(c, KEY_END_BAIRRO));


        return endereco;
    }

    @Override
    public boolean saveOrEdit(Endereco endereco) {


        try {
            getWritableDB().insertWithOnConflict(TABLE_ENDERECOS, null, bindValuesEnd(endereco), SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e){
            Log.i("INFO", "Erro ao inserir os endereços");
            return false;
        }

        return true;
    }
}
