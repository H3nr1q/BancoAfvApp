package com.example.bancoafvapp;

import android.os.Environment;

import com.example.bancoafvapp.app.BancoAfvApp;

public class AfvAppSharedRep {

    public static AfvAppSharedRep afvAppSharedRep;
    public static String BANCO_1 = Environment.getExternalStorageDirectory().getPath().
            concat("/Android/data/com.example.bancoafvapp/files/db1/bancomovel"); ;
    public static String BANCO_2 = Environment.getExternalStorageDirectory().getPath().
            concat("/Android/data/com.example.bancoafvapp/files/db2/bancomovel"); ;
    public static String BANCO_3 = Environment.getExternalStorageDirectory().getPath().
            concat("/Android/data/com.example.bancoafvapp/files/db3/bancomovel"); ;

    public AfvAppSharedRep() {

    }

    public synchronized static AfvAppSharedRep getInstance(){

        if (afvAppSharedRep == null){

            afvAppSharedRep = new AfvAppSharedRep();
        }
        return afvAppSharedRep;
    }
}
