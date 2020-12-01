package com.example.bancoafvapp.helper;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.example.bancoafvapp.AfvAppSharedRep;
import com.example.bancoafvapp.app.BancoAfvApp;

import java.io.File;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static DbHelper dbHelper;

    public static final String DB_1 = "/db1/bancomovel";
    public static final String DB_2 = "/db2/bancomovel";
    public static final String DB_3 = "/db3/bancomovel";

    public DbHelper(String dbPath){
        super(BancoAfvApp.getInstance(),dbPath, null, VERSION );
    }

    public synchronized static DbHelper getInstance(String bdName){
        if (dbHelper == null){
            dbHelper = new DbHelper(
                    BancoAfvApp.getInstance().getExternalFilesDir(null).getPath().concat(bdName));

            File[] file = (BancoAfvApp.getInstance().getExternalFilesDir(null)).listFiles(File::isDirectory);


        }
        return dbHelper;
    }
    public void setNull(){
        dbHelper = null;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
