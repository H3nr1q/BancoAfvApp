package com.example.bancoafvapp.fragment;

import android.view.View;

import com.example.bancoafvapp.helper.ClienteDAO;
import com.example.bancoafvapp.model.Cliente;
import com.example.bancoafvapp.task.LoadClientesByAttributes;
import com.example.bancoafvapp.task.LoadClientesTask;

import java.util.List;

public class ClientesPresenter implements LoadClientesTask.OnLoadClientes, LoadClientesByAttributes.OnSelectClientesByAttributes {

    private View mView;

    public ClientesPresenter(View mView) {
        this.mView = mView;
    }

    public void onAttachView(View view){
        mView = view;
    }

    public void listarClientes(){

        LoadClientesTask loadClientesTask = new LoadClientesTask(this);
        loadClientesTask.execute();
    }

    public void clientesQuery(String query){

        LoadClientesByAttributes loadClientesByAttributes = new LoadClientesByAttributes(this);
        loadClientesByAttributes.execute(query);
    }

    public boolean deletarCliente(Cliente cliente){

        return ClienteDAO.getInstance().delete(cliente);
    }

    @Override
    public void onLoadClientesAdapter(List<Cliente> clientes) {

        mView.refreshAdapterList(clientes);
    }

    @Override
    public void onClientesByAttributes(List<Cliente> clientes) {
        mView.refreshAdapterList(clientes);
    }

    public interface View{

        void refreshAdapterList(List<Cliente> clientes);
    }
}
