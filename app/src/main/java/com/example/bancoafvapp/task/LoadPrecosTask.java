package com.example.bancoafvapp.task;

import android.os.AsyncTask;

import com.example.bancoafvapp.helper.ProdutoDAO;

import java.util.List;

public class LoadPrecosTask extends AsyncTask<String, Void, List<String>> {

    private OnLoadPrecos onLoadPrecos;

    public LoadPrecosTask(OnLoadPrecos onLoadPrecos) {
        this.onLoadPrecos = onLoadPrecos;
    }
    @Override
    protected List<String> doInBackground(String... strings) {

        return ProdutoDAO.getInstance().selectPrices(strings[0]);
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        onLoadPrecos.onLoadPrecos(strings);
    }

    public interface OnLoadPrecos{
        void onLoadPrecos(List<String> precos);
    }

}
