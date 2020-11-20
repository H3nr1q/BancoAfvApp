package com.example.bancoafvapp.helper;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.example.bancoafvapp.AfvAppSharedRep;
import com.example.bancoafvapp.app.BancoAfvApp;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static DbHelper dbHelper;

    public DbHelper(String dbPath){
        super(BancoAfvApp.getInstance(),dbPath, null, VERSION );
    }

    public synchronized static DbHelper getInstance(){
        if (dbHelper == null){
            dbHelper = new DbHelper(
                    BancoAfvApp.getInstance().getExternalFilesDir(null).getPath().concat("/db1/bancomovel"));
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
