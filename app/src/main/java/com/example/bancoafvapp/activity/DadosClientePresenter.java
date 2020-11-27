package com.example.bancoafvapp.activity;

import com.example.bancoafvapp.helper.DbHelper;
import com.example.bancoafvapp.helper.EnderecoDAO;
import com.example.bancoafvapp.task.LoadCidadesTask;

import java.util.List;

public class DadosClientePresenter{


    public DadosClientePresenter() {
    }
    public String getNomeCidade(String code){

        return EnderecoDAO.getInstance().selectCityNameByCode(code);
    }

}
