package com.example.bancoafvapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.activity.CadastroClienteActivity;
import com.example.bancoafvapp.utils.MaskEditUtil;
import com.example.bancoafvapp.utils.StringUtils;
import com.example.validators.CNPJValidator;
import com.example.validators.CPFValidator;
import com.google.android.material.textfield.TextInputLayout;


public class NovoClienteDialogFragment extends DialogFragment implements View.OnClickListener {

    private RadioButton radioButtonJuridica, radioButtonFisica;

    private TextWatcher cpfCnpjTextWatcher;

    private TextInputLayout cpfCnpj;

    View mView;

    public NovoClienteDialogFragment() {}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.fragment_novo_cliente_dialog, null, false);

        radioButtonFisica = mView.findViewById(R.id.radioButtonFisica);
        radioButtonJuridica = mView.findViewById(R.id.radioButtonJuridica);
        cpfCnpj = mView.findViewById(R.id.textLayoutFieldCpfCnpj);
        //cpf = mView.findViewById(R.id.textLayoutFieldCpfEmp);


        radioButtonJuridica.setOnClickListener(this);

        radioButtonFisica.setOnClickListener(this);

        if (radioButtonJuridica.isChecked()){
            radioButtonJuridica.setChecked(true);
            radioButtonFisica.setChecked(false);
            if (!StringUtils.isNullOrEmpty(cpfCnpj.getEditText().getText().toString()))
                cpfCnpj.getEditText().getText().clear();
            cpfCnpj.setHint("CNPJ");
            cpfCnpjTextWatcher = MaskEditUtil.mask(cpfCnpj.getEditText(), MaskEditUtil.FORMAT_CNPJ);
        }

        cpfCnpj.getEditText().addTextChangedListener(cpfCnpjTextWatcher);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Novo cliente");
        builder.setView(mView);
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(getActivity(), CadastroClienteActivity.class);

                if (radioButtonJuridica.isChecked()){
                    intent.putExtra("tipoPessoa", "juridica");
                    intent.putExtra("cpfcnpj", cpfCnpj.getEditText().getText().toString());
                }else if(radioButtonFisica.isChecked()){
                    intent.putExtra("tipoPessoa", "fisica");
                    intent.putExtra("cpfcnpj", cpfCnpj.getEditText().getText().toString());
                }

                startActivityForResult(intent, CadastroClienteActivity.REQUEST_CODE);
            }
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                cpfCnpj.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }
                    @Override
                    public void afterTextChanged(Editable s) {

                        if (!StringUtils.isNullOrEmpty(s.toString())){
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                        }else {
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                        }
                    }
                });
            }
        });


        return alertDialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.radioButtonJuridica:

                radioButtonJuridica.setChecked(true);
                radioButtonFisica.setChecked(false);
                if (!StringUtils.isNullOrEmpty(cpfCnpj.getEditText().getText().toString()))
                cpfCnpj.getEditText().getText().clear();
                cpfCnpj.setHint("CNPJ");
                cpfCnpjTextWatcher = MaskEditUtil.mask(cpfCnpj.getEditText(), MaskEditUtil.FORMAT_CNPJ);
                break;

            case R.id.radioButtonFisica:

                radioButtonFisica.setChecked(true);
                radioButtonJuridica.setChecked(false);
                if (!StringUtils.isNullOrEmpty(cpfCnpj.getEditText().getText().toString()))
                    cpfCnpj.getEditText().getText().clear();
                cpfCnpj.setHint("CPF");
                cpfCnpjTextWatcher = MaskEditUtil.mask(cpfCnpj.getEditText(), MaskEditUtil.FORMAT_CPF);
                break;
        }
    }
}