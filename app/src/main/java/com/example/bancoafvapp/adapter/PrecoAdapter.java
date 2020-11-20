package com.example.bancoafvapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bancoafvapp.R;

import java.util.List;

public class PrecoAdapter extends RecyclerView.Adapter<PrecoAdapter.MyViewHolder> {

    public List<String> precosList;

    public PrecoAdapter(List<String> precosList) {
        this.precosList = precosList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.price_list_content, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String preco = precosList.get(position);
        holder.preco.setText(preco);
    }

    @Override
    public int getItemCount() {
        return precosList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView preco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            preco = itemView.findViewById(R.id.textViewPriceListContent);
        }
    }

    public void setPrecosList(List<String> precosList) {
        this.precosList = precosList;
    }
}
