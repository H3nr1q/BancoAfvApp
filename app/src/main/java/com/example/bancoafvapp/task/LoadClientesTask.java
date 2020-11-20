package com.example.bancoafvapp.task;

import android.os.AsyncTask;

import com.example.bancoafvapp.helper.ClienteDAO;
import com.example.bancoafvapp.model.Cliente;

import java.util.List;

public class LoadClientesTask extends AsyncTask<Void, Void, List<Cliente>> {

    private OnLoadClientes onLoadClientes;

    public LoadClientesTask(OnLoadClientes onLoadClientes){

        this.onLoadClientes = onLoadClientes;
    }

    @Override
    protected List<Cliente> doInBackground(Void... voids) {

        return ClienteDAO.getInstance().selectAll();
    }

    @Override
    protected void onPostExecute(List<Cliente> clientes) {
        onLoadClientes.onLoadClientesAdapter(clientes);
    }

    public interface OnLoadClientes{

        void onLoadClientesAdapter(List<Cliente> clientes);

    }

    public void setOnLoadClientes(OnLoadClientes onLoadClientes){
        this.onLoadClientes = onLoadClientes;
    }
}
