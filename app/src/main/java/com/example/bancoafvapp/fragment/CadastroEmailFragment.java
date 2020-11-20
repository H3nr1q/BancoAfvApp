package com.example.bancoafvapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.model.Cliente;
import com.example.validators.EmailValidator;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroEmailFragment extends CadastroClienteFragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextInputLayout emailPrincipal, emailSecundario;
    private EmailValidator emailValidator1, emailValidator2;
    private ICadastroCliente listener;

    public CadastroEmailFragment() { }

    public static CadastroEmailFragment newInstance(String param1, String param2) {
        CadastroEmailFragment fragment = new CadastroEmailFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_email, container, false);
        emailPrincipal = view.findViewById(R.id.textLayoutFieldEmailPrincipal);
        emailSecundario = view.findViewById(R.id.textLayoutFieldEmaileSecundario);

        emailValidator1 = new EmailValidator(emailPrincipal);
        emailValidator2 = new EmailValidator(emailSecundario);

        emailPrincipal = emailValidator1.validateAfterTextChanged();
        emailSecundario = emailValidator2.validateAfterTextChanged();

        return view;
    }

    @Override
    public boolean isValid() {

        if (emailValidator1 != null || emailValidator2 != null){
            if (emailValidator1.validate()){
                return emailValidator2.validate();
            }
        }
        return true;
    }

    @Override
    public Cliente getCliente() {
        return null;
    }
    /*
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public Cliente clienteData() {
        Cliente cliente = new Cliente();
        cliente.setEmailPrincipal(emailPrincipal.getEditText().getText().toString());
        cliente.setEmailSecundario(emailSecundario.getEditText().getText().toString());
        return cliente;
    }
    *
     */
}