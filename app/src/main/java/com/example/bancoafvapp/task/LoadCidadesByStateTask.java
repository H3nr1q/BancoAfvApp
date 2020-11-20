package com.example.bancoafvapp.task;

import android.os.AsyncTask;

import com.example.bancoafvapp.helper.EnderecoDAO;
import com.example.bancoafvapp.model.Municipio;

import java.util.List;

public class LoadCidadesByStateTask extends AsyncTask<String, Void, List<Municipio>> {

    OnloadCitiesByState onloadCitiesByState;

    public LoadCidadesByStateTask(OnloadCitiesByState onloadCitiesByState) {
        this.onloadCitiesByState = onloadCitiesByState;
    }

    @Override
    protected List<Municipio> doInBackground(String... strings) {

        return EnderecoDAO.getInstance().selectCitiesByState(strings[0]);
    }

    @Override
    protected void onPostExecute(List<Municipio> municipios) {
        onloadCitiesByState.onCitiesByState(municipios);
    }

    public interface OnloadCitiesByState{
        void onCitiesByState(List<Municipio> municipios);
    }
}
