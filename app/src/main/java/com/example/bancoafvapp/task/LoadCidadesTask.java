package com.example.bancoafvapp.task;

import android.os.AsyncTask;

import com.example.bancoafvapp.helper.EnderecoDAO;

import java.util.List;

public class LoadCidadesTask extends AsyncTask<String, Void, List<String>> {

    private OnLoadCidades onLoadCidades;

    @Override
    protected List<String> doInBackground(String... strings) {


        return EnderecoDAO.getInstance().selectCitiesByName(strings[0]);
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        onLoadCidades.onLoadCidades(strings);
    }

    public interface OnLoadCidades{

        void onLoadCidades(List<String> cidades);
    }
}
