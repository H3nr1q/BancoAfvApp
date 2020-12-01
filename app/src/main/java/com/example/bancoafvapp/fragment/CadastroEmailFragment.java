package com.example.bancoafvapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.model.Cliente;
import com.example.bancoafvapp.utils.StringUtils;
import com.example.bancoafvapp.utils.Validate;
import com.example.validators.EmailValidator;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CadastroEmailFragment extends CadastroClienteFragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextInputLayout emailPrincipal, emailSecundario;


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

        if (getCliente()!=null) {
            if (getCliente().getEmailPrincipal() != null) {
                Objects.requireNonNull(emailPrincipal.getEditText()).setText(getCliente().getEmailPrincipal());
            }
            if (emailSecundario.getEditText()!=null && getCliente().getEmailSecundario() != null) {
                emailSecundario.getEditText().setText(getCliente().getEmailSecundario());
            }
        }

        return view;
    }

    @Override
    public boolean isValid() {

        boolean isValid = isEmailValido(emailPrincipal);
        isValid &= isEmailValido(emailSecundario);

        if (isValid && getCliente()!=null) {
            getCliente().setEmailPrincipal(emailPrincipal.getEditText().getText().toString());
            getCliente().setEmailSecundario(emailSecundario.getEditText().getText().toString());
        }

        return isValid;
    }

    private boolean isEmailValido(TextInputLayout email) {

        if(email==null || email.getEditText()==null) return false;

        if (StringUtils.isNullOrEmpty(email.getEditText().getText().toString())){
            email.setError(getString(R.string.campo_obrigatorio));
            return false;
        }

        if (!email.getEditText().getText().toString().matches(Validate.EMAIL_REGEX)){
            email.setError(getString(R.string.digite_email_valido));
            return false;
        }

        if (email.getError()!=null) {
            email.setError(null);
        }

        return true;
    }

}