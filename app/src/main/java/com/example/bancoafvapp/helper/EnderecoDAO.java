package com.example.bancoafvapp.helper;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.bancoafvapp.model.Municipio;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO extends EndDAO<Municipio> {

    public static final String TABLE_MUNICIPIOS = "GUA_MUNICIPIOS";
    public static final String KEY_ESTADO = "MUN_ESTADO";
    public static final String KEY_CODMUNICIPIO = "MUN_CODIGOMUNICIPIO";
    public static final String KEY_NOME = "MUN_NOME";


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
}
