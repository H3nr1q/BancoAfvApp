package com.example.bancoafvapp.activity;

import com.example.bancoafvapp.helper.ClienteDAO;
import com.example.bancoafvapp.helper.DbHelper;
import com.example.bancoafvapp.helper.EnderecoDAO;
import com.example.bancoafvapp.model.Cliente;
import com.example.bancoafvapp.model.Endereco;

public class CadastroClientePresenter {

    public CadastroClientePresenter() {
    }

    public boolean saveOrEditCliente(Cliente cliente){
        return ClienteDAO.getInstance().saveOrEdit(cliente);
    }

    public boolean saveOrEditEndereco(Endereco endereco){

        return EnderecoDAO.getInstance().saveOrEdit(endereco);
    }
}
