package com.example.bancoafvapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
    private OnClienteClick onClienteClick;

    public ClientesAdapter(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public void removeCliente(int position){

        if (position>0){
            listClientes.remove(position);
            notifyItemChanged(position);
            notifyItemRangeChanged(position, getItemCount());
        }
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

        holder.buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(holder.itemView.getContext(), holder.buttonOptions);
                popupMenu.inflate(R.menu.button_options);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.optionEdit:
                                onClienteClick.onEditCliente(holder.getAdapterPosition(), listClientes.get(holder.getAdapterPosition()));
                                break;
                            case R.id.optionDelete:
                                onClienteClick.onDeleteCliente(holder.getAdapterPosition(), listClientes.get(holder.getAdapterPosition()));
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClienteClick.onItemClick(holder.getAdapterPosition(), listClientes.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listClientes.size();
    }

    public void setOnClienteClick(OnClienteClick onClienteClick) {
        this.onClienteClick = onClienteClick;
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

    public interface OnClienteClick{
        void onDeleteCliente(int position, Cliente cliente);
        void onEditCliente(int position, Cliente cliente);
        void onItemClick(int position, Cliente cliente);
    }

}

