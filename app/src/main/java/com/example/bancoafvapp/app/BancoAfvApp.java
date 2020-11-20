package com.example.bancoafvapp.app;

import androidx.multidex.MultiDexApplication;

public class BancoAfvApp extends MultiDexApplication {

    private static BancoAfvApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static BancoAfvApp getInstance(){
        return instance;
    }
}
