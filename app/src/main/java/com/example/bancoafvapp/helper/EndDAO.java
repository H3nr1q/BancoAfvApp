package com.example.bancoafvapp.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bancoafvapp.model.Endereco;

import java.util.List;

public abstract class EndDAO<Param> {

    public EndDAO(){}

    public final SQLiteDatabase getWritableDB(){
        return DbHelper.getInstance().getWritableDatabase();
    }
    public final SQLiteDatabase getReadableDB(){

        return DbHelper.getInstance().getReadableDatabase();
    }

    public abstract List<Param> selectCitiesByState(String estado);
    public abstract List<String> selectCitiesByName(String cidade);

    protected abstract ContentValues bindValues(Param param);
    protected abstract Param bind(Cursor c);

    protected abstract ContentValues bindValuesEnd(Endereco param);
    protected abstract Endereco bindEnd(Cursor c);

    protected String getString(Cursor c, String columnName, String defaultField){
        if(columnName == null || columnName.isEmpty()) return defaultField;
        final String field = c.getString(c.getColumnIndex(columnName));
        if(field == null){
            return defaultField;
        }
        return field;
    }

    protected String getString(Cursor c, String columnName){
        return getString(c, columnName, null);
    }

    public abstract boolean saveOrEdit(Endereco object);

}
