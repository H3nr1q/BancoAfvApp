package com.example.bancoafvapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.model.Endereco;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class EnderecosAdapter extends RecyclerView.Adapter<EnderecosAdapter.MyViewHolder> {


    private List<Endereco> enderecos = new ArrayList<>();

    private int itemCount = 1;

    public EnderecosAdapter() { }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.endereco_content, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String string = holder.adressTitle.getText() + String.valueOf(position + 1);
        holder.adressTitle.setText(string);
        /*
        holder.endereco.getEditText().setText("");
        holder.numero.getEditText().setText("");
        holder.complemento.getEditText().setText("");
        holder.estado.getEditText().setText("");
        holder.cidade.getEditText().setText("");
        holder.bairro.getEditText().setText("");*/
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void addCount(){

        itemCount += 1;
        notifyDataSetChanged();
    }

    public void removeCount(){

        if (itemCount <=1 ){

            itemCount -= 1;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

       private TextInputLayout endereco, numero, complemento, bairro, estado, cidade;
       private TextView adressTitle;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           adressTitle = itemView.findViewById(R.id.enderecoTitle);
           endereco = itemView.findViewById(R.id.textLayoutFieldEndereco);
           numero = itemView.findViewById(R.id.textLayoutFieldNumero);
           complemento = itemView.findViewById(R.id.textLayoutFieldComplemento);
           bairro = itemView.findViewById(R.id.textLayoutFieldBairro);
           estado = itemView.findViewById(R.id.textLayoutFieldEstado);
           cidade = itemView.findViewById(R.id.textLayoutFieldCidade);

       }
   }

}
