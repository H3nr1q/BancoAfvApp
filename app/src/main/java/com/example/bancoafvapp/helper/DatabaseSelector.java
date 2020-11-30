package com.example.bancoafvapp.helper;

public class DatabaseSelector {

    private static DatabaseSelector databaseSelector;
    private String dbName;


    public DatabaseSelector() {
    }

    public synchronized static DatabaseSelector getInstance(){

        if (databaseSelector == null){

            databaseSelector = new DatabaseSelector();
        }
        return databaseSelector;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
