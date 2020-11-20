package com.example.bancoafvapp.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public abstract class DAO<Param> {

    public DAO(){}

    public final SQLiteDatabase getWritableDB(){

        return DbHelper.getInstance().getWritableDatabase();
    }
    public final SQLiteDatabase getReadableDB(){

        return DbHelper.getInstance().getReadableDatabase();
    }

    public abstract List<Param> selectAll();


    protected abstract ContentValues bindValues(Param param);
    protected abstract Param bind(Cursor c);


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

}