package com.example.bancoafvapp.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.activity.CadastroClienteActivity;
import com.example.bancoafvapp.adapter.EnderecosAdapter;
import com.example.bancoafvapp.app.BancoAfvApp;
import com.example.bancoafvapp.model.Cliente;
import com.example.bancoafvapp.model.Endereco;
import com.example.bancoafvapp.model.Municipio;
import com.example.bancoafvapp.utils.StringUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class CadastroEnderecoFragment extends CadastroClienteFragment implements View.OnClickListener, NovoEnderecoDialogFragment.OnAddAdress,
        EnderecosAdapter.OnRemoveAddressItem, EnderecosAdapter.OnEnderecoClickListener, CadastroEnderecoPresenter.View{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private List<Endereco> enderecos = new ArrayList<>();

    private RecyclerView recyclerView;

    private EnderecosAdapter enderecosAdapter;

    private FloatingActionButton floatingActionButton;

    private OnActionEnderecoListener onActionEnderecoListener;

    private CadastroEnderecoPresenter presenter;

    private String clienteCode;

    public CadastroEnderecoFragment() {}


    public static CadastroEnderecoFragment newInstance(String param1, String param2) {
        CadastroEnderecoFragment fragment = new CadastroEnderecoFragment();
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

        View view = inflater.inflate(R.layout.fragment_cadastro_endereco, container, false);
        floatingActionButton = view.findViewById(R.id.fbAddMoreAddress);
        recyclerView = view.findViewById(R.id.recyclerViewCadastroEndereco);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        presenter = new CadastroEnderecoPresenter(this);
        enderecosAdapter = new EnderecosAdapter(enderecos);
        if (getCliente()!=null) {

            clienteCode = getCliente().getCodigoCliente();
            Endereco endereco = new Endereco();
            if (getCliente().getEndereco() != null){
                endereco.setEndereco(getCliente().getEndereco());
            }
            if (getCliente().getNumero()!=null){
                endereco.setNumero(getCliente().getNumero());
            }
            if (getCliente().getNumero()!=null){
                endereco.setComplemento(getCliente().getComplemento());
            }
            if (getCliente().getComplemento()!=null){
                endereco.setBairro(getCliente().getBairro());
            }
            if (getCliente().getCodMunicipio()!=null){
                endereco.setCodMunicipio(getCliente().getCodMunicipio());
                endereco.setNomeMunicipio(presenter.getCityName(endereco.getCodMunicipio()));
            }
            enderecos.add(endereco);

            if (getCliente().getEnderecos() != null && !getCliente().getEnderecos().isEmpty()) {
                enderecos = getCliente().getEnderecos();

                for (Endereco end:enderecos) {

                    end.setNomeMunicipio(presenter.getCityName(end.getCodMunicipio()));
                }

                enderecosAdapter = new EnderecosAdapter(enderecos);
                //enderecosAdapter.setEnderecos(enderecos);
            } else {
                getCliente().setEnderecos(enderecos);
                enderecosAdapter = new EnderecosAdapter(enderecos);
            }
        }
        enderecosAdapter.setOnEnderecoClickListener(this);
        recyclerView.setAdapter(enderecosAdapter);
        floatingActionButton.setOnClickListener(this);
        enderecosAdapter.setOnRemoveAddressItem(this);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnActionEnderecoListener){
            this.onActionEnderecoListener = (OnActionEnderecoListener) context;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fbAddMoreAddress:
                    NovoEnderecoDialogFragment dialogFragment = new NovoEnderecoDialogFragment();
                    dialogFragment.setOnAddAdress(this);
                    dialogFragment.show(getActivity().getSupportFragmentManager(), "novoEnderecoDialog");
                    break;
        }
    }
    @Override
    public boolean isValid() {

        if (enderecos.size() <= 0){
            Toast.makeText(getContext(), "Adicione pelo menos um endereço", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @Override
    public void addAddress(Endereco endereco) {
        endereco.setCodigoCliente(clienteCode);
        endereco.setNomeMunicipio(presenter.getCityName(endereco.getCodMunicipio()));
        enderecos.add(endereco);
        if (getCliente()!=null)
        getCliente().setEnderecos(enderecos);
        enderecosAdapter.setEnderecos(enderecos);
    }

    @Override
    public void editAddress(Endereco endereco, int position) {

        enderecos.set(position, endereco);
        enderecosAdapter.setEnderecos(enderecos);
    }

    @Override
    public void onRemoveAddressItem(int position) {

        if (position > 0){

            dialogResponse(position);
        }else{

            Toast.makeText(getActivity(), "É necessário pelo menos um endereço", Toast.LENGTH_LONG).show();
        }
    }


    private void dialogResponse(int position){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Excluir endereço");
        alertDialog.setMessage("Você realmente quer remover este endereço?");
        alertDialog.setNegativeButton("Não", null);
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                enderecos.remove(position);
                enderecosAdapter.setEnderecos(enderecos);
                if (getCliente()!=null)
                getCliente().setEnderecos(enderecos);
            }
        });

        alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void onAddressClick(int position, Endereco endereco) {

        NovoEnderecoDialogFragment fragment = NovoEnderecoDialogFragment.newInstance(endereco, position);
        fragment.setOnAddAdress(this);
        fragment.show(getActivity().getSupportFragmentManager(), "NovoDialog");
    }

    @Override
    public void refreshMunicipiosList(List<Municipio> municipios) {

    }
}