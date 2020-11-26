package com.example.bancoafvapp.fragment;

import android.view.View;

import com.example.bancoafvapp.helper.ClienteDAO;
import com.example.bancoafvapp.model.Cliente;
import com.example.bancoafvapp.task.LoadClientesTask;

import java.util.List;

public class ClientesFragmentPresenter implements LoadClientesTask.OnLoadClientes {

    View mView;

    public ClientesFragmentPresenter(View mView) {
        this.mView = mView;
    }

    public void onAttachView(View view){
        mView = view;
    }

    public void listarClientes(){

        LoadClientesTask loadClientesTask = new LoadClientesTask(this);
        loadClientesTask.execute();
    }

    public boolean deletarCliente(Cliente cliente){

        return ClienteDAO.getInstance().delete(cliente);
    }

    @Override
    public void onLoadClientesAdapter(List<Cliente> clientes) {

        mView.refreshAdapterList(clientes);
    }

    public interface View{

        void refreshAdapterList(List<Cliente> clientes);
    }
}
