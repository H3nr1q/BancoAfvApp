package com.example.bancoafvapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.model.Endereco;
import com.example.bancoafvapp.utils.TouchView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class EnderecosAdapter extends RecyclerView.Adapter<EnderecosAdapter.MyViewHolder> {


    private List<Endereco> enderecos = new ArrayList<>();

    private OnRemoveAddressItem onRemoveAddressItem;

    private OnEnderecoClickListener onEnderecoClickListener;

    //private int itemCount = 3;

    public EnderecosAdapter(List<Endereco> enderecos) {

        this.enderecos = enderecos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.endereco_content, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (holder.adressTitle.getText().length()<10){
            holder.adressTitle.setText(holder.adressTitle.getText().toString() +( position + 1));
        }

        holder.endereco.setText(enderecos.get(position).getEndereco() + ", ");
        holder.numero.setText(enderecos.get(position).getNumero() + ", ");
        holder.complemento.setText(enderecos.get(position).getComplemento());
        holder.estado.setText(enderecos.get(position).getEstado());
        holder.cidade.setText(enderecos.get(position).getNomeMunicipio().trim() + ", ");
        holder.bairro.setText(enderecos.get(position).getBairro());

    }

    @Override
    public int getItemCount() {
        return enderecos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

       private TextView endereco, numero, complemento, bairro, estado, cidade;
       private TextView adressTitle;
       private ImageView removeButton;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           adressTitle = itemView.findViewById(R.id.enderecoTitleItem);
           endereco = itemView.findViewById(R.id.textViewEnderecoItem);
           numero = itemView.findViewById(R.id.textViewNumeroItem);
           complemento = itemView.findViewById(R.id.textViewComplementoItem);
           bairro = itemView.findViewById(R.id.textViewBairroItem);
           estado = itemView.findViewById(R.id.textViewEstadoItem);
           cidade = itemView.findViewById(R.id.textViewCidadeItem);
           removeButton = itemView.findViewById(R.id.removeAddressItemButton);

           TouchView.extend(removeButton, 50);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   onEnderecoClickListener.onAddressClick(getAdapterPosition(), enderecos.get(getAdapterPosition()));
               }
           });

           removeButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   onRemoveAddressItem.onRemoveAddressItem(getAdapterPosition());
               }
           });

       }
   }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
        notifyDataSetChanged();
    }

    public interface OnRemoveAddressItem{

        void onRemoveAddressItem(int position);
    }

    public void setOnRemoveAddressItem(OnRemoveAddressItem onRemoveAddressItem){

        this.onRemoveAddressItem = onRemoveAddressItem;
    }

    public interface OnEnderecoClickListener{

        void onAddressClick(int position, Endereco endereco);
    }
    public void setOnEnderecoClickListener(OnEnderecoClickListener onEnderecoClickListener){

        this.onEnderecoClickListener = onEnderecoClickListener;
    }

}
