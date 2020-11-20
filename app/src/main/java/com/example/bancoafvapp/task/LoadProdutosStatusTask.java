package com.example.bancoafvapp.task;

import android.os.AsyncTask;

import com.example.bancoafvapp.helper.ProdutoDAO;
import com.example.bancoafvapp.model.Produto;

import java.util.List;

public class LoadProdutosStatusTask extends AsyncTask<String, Void, List<Produto>> {

    private OnLoadProdutosByStatus onLoadProdutosByStatus;

    public LoadProdutosStatusTask(OnLoadProdutosByStatus onLoadProdutosByStatus) {
        this.onLoadProdutosByStatus = onLoadProdutosByStatus;
    }

    @Override
    protected List<Produto> doInBackground(String... strings) {
        return ProdutoDAO.getInstance().selectByStatus(strings[0]);
    }

    @Override
    protected void onPostExecute(List<Produto> produtos) {
        onLoadProdutosByStatus.onLoadProdutos(produtos);
    }

    public interface OnLoadProdutosByStatus{

        void onLoadProdutos(List<Produto> produtos);
    }

    public void setOnLoadProdutosByStatus(OnLoadProdutosByStatus onLoadProdutos){
        this.onLoadProdutosByStatus = onLoadProdutos;
    }
}
