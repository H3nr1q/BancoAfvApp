package com.example.bancoafvapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.bancoafvapp.R;
import com.example.bancoafvapp.model.Cliente;
import com.example.bancoafvapp.utils.TouchView;

import java.util.List;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.MyViewHolder> {

    private List<Cliente> listClientes;

    public ClientesAdapter(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cliente_list_content, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cliente cliente = listClientes.get(position);
        holder.cpfCnpj.setText(cliente.getCpfCnpj());
        holder.nome.setText(cliente.getRazaoSocial());

        char letra = cliente.getRazaoSocial().charAt(0);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(letra).toUpperCase(), Color.GRAY);
        holder.imageCliente.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return listClientes.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView cpfCnpj;
        private TextView nome;
        private ImageView buttonOptions;
        private ImageView imageCliente;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cpfCnpj = itemView.findViewById(R.id.textViewCpfCnpjContent);
            nome = itemView.findViewById(R.id.textViewNomeClienteContent);
            buttonOptions = itemView.findViewById(R.id.buttonOptionsClienteContent);
            imageCliente = itemView.findViewById(R.id.imageViewClienteContent);

            TouchView.extend(buttonOptions, 50);

        }
    }
    public void setListClientes(List<Cliente> clientes){
        this.listClientes = clientes;
        notifyDataSetChanged();
    }

}
