package com.example.bancoafvapp.task;

import android.os.AsyncTask;

import com.example.bancoafvapp.helper.ProdutoDAO;
import com.example.bancoafvapp.model.Produto;

import java.util.List;

public class LoadProdutosTask extends AsyncTask<Void, Void, List<Produto>> {

    private OnLoadProdutos onLoadProdutos;

    public LoadProdutosTask(OnLoadProdutos onLoadProdutos) {
        this.onLoadProdutos = onLoadProdutos;
    }

    @Override
    protected List<Produto> doInBackground(Void... voids) {


        return ProdutoDAO.getInstance().selectAll();
    }

    @Override
    protected void onPostExecute(List<Produto> produtos) {
        onLoadProdutos.onLoadProdutos(produtos);
    }

    public interface OnLoadProdutos{

        void onLoadProdutos(List<Produto> produtos);
    }

    public void setOnLoadProdutos(OnLoadProdutos onLoadProdutos){
        this.onLoadProdutos = onLoadProdutos;
    }
}
