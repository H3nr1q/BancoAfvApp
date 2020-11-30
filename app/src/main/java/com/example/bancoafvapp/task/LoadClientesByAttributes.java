package com.example.bancoafvapp.task;

import android.os.AsyncTask;

import com.example.bancoafvapp.helper.ClienteDAO;
import com.example.bancoafvapp.model.Cliente;

import java.util.List;

public class LoadClientesByAttributes extends AsyncTask<String, Void, List<Cliente>> {

    OnSelectClientesByAttributes onSelectClientesByAttributes;

    public LoadClientesByAttributes(OnSelectClientesByAttributes onSelectClientesByAttributes) {
        this.onSelectClientesByAttributes = onSelectClientesByAttributes;
    }

    @Override
    protected List<Cliente> doInBackground(String... strings) {
        return ClienteDAO.getInstance().selectByAttributes(strings[0]);
    }

    @Override
    protected void onPostExecute(List<Cliente> clientes) {
        onSelectClientesByAttributes.onClientesByAttributes(clientes);
    }

    public interface OnSelectClientesByAttributes{
        void onClientesByAttributes(List<Cliente> clientes);
    }
}
