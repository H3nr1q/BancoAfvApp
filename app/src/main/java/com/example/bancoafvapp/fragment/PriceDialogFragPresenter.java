package com.example.bancoafvapp.fragment;

import com.example.bancoafvapp.task.LoadPrecosTask;

import java.util.List;

public class PriceDialogFragPresenter implements LoadPrecosTask.OnLoadPrecos {

    View mView;

    public PriceDialogFragPresenter(View mView) {
        this.mView = mView;
    }

    public void listarPrecos(String proCode){

        LoadPrecosTask loadPrecosTask = new LoadPrecosTask(this);
        loadPrecosTask.execute(proCode);

    }

    @Override
    public void onLoadPrecos(List<String> precos) {
        mView.refreshPrecoAdapter(precos);
    }

    public interface View{

        void refreshPrecoAdapter(List<String> precos);
    }
}
