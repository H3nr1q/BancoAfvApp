package com.example.bancoafvapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.activity.CadastroClienteActivity;
import com.example.bancoafvapp.adapter.ClientesAdapter;
import com.example.bancoafvapp.model.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ClientesFragment extends Fragment implements ClientesFragmentPresenter.View, View.OnClickListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private List<Cliente> clienteList = new ArrayList<>();
    private ClientesAdapter clientesAdapter;
    private ClientesFragmentPresenter presenter;
    private FloatingActionButton floatingActionButton;

    public ClientesFragment() {}

    public static ClientesFragment newInstance(String param1, String param2) {
        ClientesFragment fragment = new ClientesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        bindView(view);
        // Inflate the layout for this fragment
        return view;
    }

    public void bindView(View view){

        floatingActionButton = view.findViewById(R.id.fButtonClientesFragment);
        presenter = new ClientesFragmentPresenter(this);
        recyclerView = view.findViewById(R.id.recyclerViewClientes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        presenter.listarClientes();
        clientesAdapter = new ClientesAdapter(clienteList);
        recyclerView.setAdapter(clientesAdapter);
        floatingActionButton.setOnClickListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 && floatingActionButton.getVisibility() == View.VISIBLE){
                    floatingActionButton.hide();
                }else if(dy < 0 && floatingActionButton.getVisibility() != View.VISIBLE){
                    floatingActionButton.show();
                }
            }
        });
    }

    @Override
    public void refreshAdapterList(List<Cliente> clientes) {
        clientesAdapter.setListClientes(clientes);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fButtonClientesFragment:

                NovoClienteDialogFragment dialogFragment = new NovoClienteDialogFragment();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "novoClienteDialog" );
                break;
        }
    }
}