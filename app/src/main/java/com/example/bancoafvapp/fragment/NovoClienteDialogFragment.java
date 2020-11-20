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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.activity.CadastroClienteActivity;
import com.example.validators.CNPJValidator;
import com.example.validators.CPFValidator;
import com.google.android.material.textfield.TextInputLayout;


public class NovoClienteDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RadioButton radioButtonJuridica, radioButtonFisica;

    private CPFValidator cpfValidator;
    private CNPJValidator cnpjValidator;
    private TextInputLayout cpf, cnpj;

    View mView;

    public NovoClienteDialogFragment() {}

    public static NovoClienteDialogFragment newInstance(String param1, String param2) {
        NovoClienteDialogFragment fragment = new NovoClienteDialogFragment();
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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.fragment_novo_cliente_dialog, null, false);

        radioButtonFisica = mView.findViewById(R.id.radioButtonFisica);
        radioButtonJuridica = mView.findViewById(R.id.radioButtonJuridica);
        cnpj = mView.findViewById(R.id.textLayoutFieldCnpjEmp);
        cpf = mView.findViewById(R.id.textLayoutFieldCpfEmp);

        radioButtonJuridica.setOnClickListener(this);

        radioButtonFisica.setOnClickListener(this);


        //cnpjValidator = new CNPJValidator(cnpj);
        //cnpj = cnpjValidator.validateAfterTextChanged();
        //cpfValidator = new CPFValidator(cpf);
        //cpf = cpfValidator.validateAfterTextChanged();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Novo cliente");
        builder.setView(mView);
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(getActivity(), CadastroClienteActivity.class);

                if (radioButtonJuridica.isChecked()){

                    intent.putExtra("tipoPessoa", "juridica");
                    intent.putExtra("cpfcnpj", cnpj.getEditText().getText().toString());
                }else if(radioButtonFisica.isChecked()){
                    intent.putExtra("tipoPessoa", "fisica");
                    intent.putExtra("cpfcnpj", cpf.getEditText().getText().toString());
                }

                startActivityForResult(intent, CadastroClienteActivity.REQUEST_CODE);
            }
        });



        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.radioButtonJuridica:

                radioButtonJuridica.setChecked(true);
                radioButtonFisica.setChecked(false);
                cnpj.setVisibility(View.VISIBLE);
                cpf.setVisibility(View.GONE);
                break;

            case R.id.radioButtonFisica:

                radioButtonFisica.setChecked(true);
                radioButtonJuridica.setChecked(false);
                cpf.setVisibility(View.VISIBLE);
                cnpj.setVisibility(View.GONE);
                break;
        }
    }
}