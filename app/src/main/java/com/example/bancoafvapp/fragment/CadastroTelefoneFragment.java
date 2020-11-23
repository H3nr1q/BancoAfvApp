package com.example.bancoafvapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.model.Cliente;
import com.example.bancoafvapp.utils.MaskEditUtil;
import com.example.bancoafvapp.utils.StringUtils;
import com.example.validators.CpfCnpjMaks;
import com.example.validators.PhoneNumberValidator;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class CadastroTelefoneFragment extends CadastroClienteFragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private TextInputLayout telefonePrincipal;
    private TextInputLayout telefoneSecundario;

    private String savedTelefonePrincipal;
    private String savedTelefoneSecundario;

    private TextWatcher telefone1Mask, telefone2Mask;


    private PhoneNumberValidator phoneNumberValidator, phoneNumberValidator2;

    public CadastroTelefoneFragment() {
        // Required empty public constructor
    }

    public static CadastroTelefoneFragment newInstance(String param1, String param2) {
        CadastroTelefoneFragment fragment = new CadastroTelefoneFragment();
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
        if(savedTelefonePrincipal != null && !savedTelefonePrincipal.isEmpty()){
            savedInstanceState.getString("savedTelefone1", savedTelefonePrincipal);
            telefonePrincipal.getEditText().setText(savedTelefonePrincipal);

        }if (savedTelefoneSecundario != null && !savedTelefoneSecundario.isEmpty()){
            savedInstanceState.getString("savedTelefone2", savedTelefoneSecundario);
            telefoneSecundario.getEditText().setText(savedTelefoneSecundario);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastro_telefones, container, false);
        telefonePrincipal = view.findViewById(R.id.textLayoutFieldTelefonePrincipal);
        telefoneSecundario = view.findViewById(R.id.textLayoutFieldTelefoneSecundario);

        telefone1Mask = MaskEditUtil.mask(telefonePrincipal.getEditText(), MaskEditUtil.FORMAT_FONE);
        telefone2Mask = MaskEditUtil.mask(telefoneSecundario.getEditText(), MaskEditUtil.FORMAT_FONE);

        telefonePrincipal.getEditText().addTextChangedListener(telefone1Mask);
        telefoneSecundario.getEditText().addTextChangedListener(telefone2Mask);


        //phoneNumberValidator = new PhoneNumberValidator(telefonePrincipal);
        //phoneNumberValidator2 = new PhoneNumberValidator(telefoneSecundario);

        Objects.requireNonNull(telefonePrincipal.getEditText()).addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        Objects.requireNonNull(telefoneSecundario.getEditText()).addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        getCliente().setTelefone1("131231321");

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (telefonePrincipal.getEditText().getText() != null){
            savedTelefonePrincipal = telefonePrincipal.getEditText().getText().toString();
        }
        if (telefoneSecundario.getEditText().getText() != null){
            savedTelefoneSecundario = telefoneSecundario.getEditText().getText().toString();
        }
        outState.putString("saveTelefone1", savedTelefonePrincipal);
        outState.putString("saveTelefone2", savedTelefoneSecundario);
    }


    @Override
    public boolean isValid() {

        boolean isValid = true;

        if(telefonePrincipal.getEditText()!=null){
            if(StringUtils.isNullOrEmpty(telefonePrincipal.getEditText().getText().toString())){
                telefonePrincipal.setError("Campo obrigatório");
                isValid = false;
            }else if(telefonePrincipal.getError()!=null){
                telefonePrincipal.setError(null);
            }
            getCliente().setRazaoSocial(telefonePrincipal.getEditText().getText().toString());
        }

        if(telefoneSecundario.getEditText()!=null){
            if(StringUtils.isNullOrEmpty(telefoneSecundario.getEditText().getText().toString())){
                telefoneSecundario.setError("Campo obrigatório");
                isValid = false;
            }else if(telefoneSecundario.getError()!=null){
                telefoneSecundario.setError(null);
            }
            getCliente().setRazaoSocial(telefoneSecundario.getEditText().getText().toString());
        }

        return isValid;
    }
}