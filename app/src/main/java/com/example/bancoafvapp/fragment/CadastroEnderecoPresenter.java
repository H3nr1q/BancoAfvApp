package com.example.bancoafvapp.fragment;

import com.example.bancoafvapp.model.Municipio;
import com.example.bancoafvapp.task.LoadCidadesByStateTask;

import java.util.List;

public class CadastroEnderecoPresenter implements LoadCidadesByStateTask.OnloadCitiesByState {

    View mView;

    public CadastroEnderecoPresenter(View mView) {
        this.mView = mView;
    }

    public void listarCidadesEstado(String estado){

        LoadCidadesByStateTask loadCidadesByStateTask = new LoadCidadesByStateTask(this);
        loadCidadesByStateTask.execute(estado);
    }

    @Override
    public void onCitiesByState(List<Municipio> municipios) {
        mView.refreshMunicipiosList(municipios);
    }

    public interface View{
        void refreshMunicipiosList(List<Municipio> municipios);
    }
}
