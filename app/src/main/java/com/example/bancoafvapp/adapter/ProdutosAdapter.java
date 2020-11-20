package com.example.bancoafvapp.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.app.BancoAfvApp;
import com.example.bancoafvapp.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.MyViewHolder> {

    private List<Produto> produtoList;
    private OnProdutoClickListener mOnProdutoClickListener;

    public ProdutosAdapter(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.produto_list_content, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Produto produto = produtoList.get(position);
        String minValue = "R$ " + String.valueOf(produto.getPrecoMin());
        String maxValue = "R$ " + String.valueOf(produto.getPrecoMax());
        holder.codigo.setText(produto.getCodigo());
        holder.descricao.setText(produto.getDescricao());
        holder.estoque.setText(String.valueOf(produto.getEstoque()));
        holder.valorMin.setText(minValue);
        holder.valorMax.setText(maxValue);
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView codigo,descricao,estoque,valorMax,valorMin;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            codigo = itemView.findViewById(R.id.textViewCodigoProdutoContent);
            descricao = itemView.findViewById(R.id.textViewDescricaoProdutoContent);
            estoque = itemView.findViewById(R.id.textViewEstoqueProdutoContent);
            valorMax = itemView.findViewById(R.id.textViewMaxValueProdutoContent);
            valorMin = itemView.findViewById(R.id.textViewMinValueProdutoContent);
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnProdutoClickListener.onProdutoItemSelected(getAdapterPosition(), produtoList.get(getAdapterPosition()));
                }
            });
        }
    }

    public void setProdutoList(List<Produto> produtos){
        this.produtoList = produtos;
        notifyDataSetChanged();
    }

    public interface OnProdutoClickListener{

        void onProdutoItemSelected(int position, Produto produto);
    }
    public void setOnProdutoClickListener(OnProdutoClickListener onProdutoClickListener) {
        this.mOnProdutoClickListener = onProdutoClickListener;
    }

}
