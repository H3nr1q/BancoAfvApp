package com.example.bancoafvapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.model.Cliente;
import com.example.bancoafvapp.utils.StringUtils;
import com.example.validators.CNPJValidator;
import com.example.validators.CPFValidator;
import com.example.validators.CpfCnpjMaks;
import com.example.validators.CpfCnpjMask;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class CadastroDadosClienteFragment extends CadastroClienteFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String savePessoa;
    private String sRazaoSocial;
    private String sNomeFantasia;

    private TextInputLayout razaoSocial, nomeFantasia;

    private String mParam1;
    private String mParam2;
    

    public CadastroDadosClienteFragment() { }

    public static CadastroDadosClienteFragment newInstance() {
        CadastroDadosClienteFragment fragment = new CadastroDadosClienteFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
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

        View view = inflater.inflate(R.layout.fragment_cadastro_dados_cliente, container, false);

        razaoSocial = view.findViewById(R.id.textLayoutFieldRazaoSocial);

        nomeFantasia = view.findViewById(R.id.textLayoutFieldNomeFantasia);


        if (getCliente() != null) {
            if (getCliente().getRazaoSocial() != null) {
                Objects.requireNonNull(razaoSocial.getEditText()).setText(getCliente().getRazaoSocial());
            }
            if (getCliente().getNomeFantasia() != null) {
                Objects.requireNonNull(nomeFantasia.getEditText()).setText(getCliente().getNomeFantasia());
            }
            razaoSocial.getEditText().setText(getCliente().getRazaoSocial());
            nomeFantasia.getEditText().setText(getCliente().getNomeFantasia());
        }

        if (getActivity() != null){
            if (getCliente() != null) {
                if (getActivity().getIntent().getStringExtra("cpfcnpj") != null) {

                    getCliente().setCpfCnpj(getActivity().getIntent().getStringExtra("cpfcnpj"));
                    String tipo = getActivity().getIntent().getStringExtra("tipoPessoa");

                    switch (tipo) {
                        case "fisica":
                            nomeFantasia.setVisibility(View.GONE);
                            break;

                        case "juridica":
                            nomeFantasia.setVisibility(View.VISIBLE);
                            break;
                    }
                } else {
                    if (getCliente().getNomeFantasia() != null) {
                        if (!getCliente().getNomeFantasia().isEmpty()) {
                            nomeFantasia.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }

        return view;
    }


    @Override
    public boolean isValid() {

        boolean isValid = true;

        if(razaoSocial!=null){
            if(StringUtils.isNullOrEmpty(razaoSocial.getEditText().getText().toString())){
                razaoSocial.setError("Campo obrigatório");
                isValid = false;
            }else if(razaoSocial.getError()!=null){
                razaoSocial.setError(null);
            }
            if (getCliente()!=null) {
                getCliente().setRazaoSocial(razaoSocial.getEditText().getText().toString());
                sRazaoSocial = razaoSocial.getEditText().getText().toString();
            }
        }

        if(nomeFantasia!=null && nomeFantasia.getVisibility() == View.VISIBLE){
            if(StringUtils.isNullOrEmpty(Objects.requireNonNull(nomeFantasia.getEditText()).getText().toString())){
                nomeFantasia.setError("Campo obrigatório");
                isValid = false;
            }else if (nomeFantasia.getError()!=null){
                nomeFantasia.setError(null);
            }
            if (getCliente()!=null) {
                getCliente().setNomeFantasia(nomeFantasia.getEditText().getText().toString());
                sNomeFantasia = nomeFantasia.getEditText().getText().toString();
            }
        }
        return isValid;
    }

}