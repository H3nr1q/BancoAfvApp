package com.example.bancoafvapp.fragment;

import android.view.View;

import com.example.bancoafvapp.model.Produto;
import com.example.bancoafvapp.task.LoadProdutosStatusTask;
import com.example.bancoafvapp.task.LoadProdutosTask;

import java.util.List;

public class ProdutosFragmentPresenter implements LoadProdutosStatusTask.OnLoadProdutosByStatus {

    View mView;

    public ProdutosFragmentPresenter(View mView) {
        this.mView = mView;
    }

    public void onAttachView(View view){
        this.mView = view;
    }

    public void listarProdutosByStatus(String status){

        LoadProdutosStatusTask loadProdutosStatusTask = new LoadProdutosStatusTask(this);
        loadProdutosStatusTask.execute(status);
    }

    @Override
    public void onLoadProdutos(List<Produto> produtos) {

        mView.refreshAdapterList(produtos);
    }

    public interface View{

        void refreshAdapterList(List<Produto> produtos);
    }

}
