package com.example.bancoafvapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.model.Municipio;
import com.example.bancoafvapp.utils.StringUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class NovoEnderecoDialogFragment extends DialogFragment implements CadastroEnderecoPresenter.View {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private AutoCompleteTextView estadosAutoComplete, cidadesAutoComplete;
    private ArrayAdapter<CharSequence> adapterEstados;
    private ArrayAdapter<String> adapterCidades;
    private List<String> cidades = new ArrayList<>();
    private List<Municipio> municipios = new ArrayList<>();

    private CadastroEnderecoPresenter presenter;

    private View mView;

    private TextInputLayout endereco, numero, complemento,
            bairro, estado, cidade;

    public NovoEnderecoDialogFragment() { }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.fragment_novo_cliente_dialog, null, false);

        presenter = new CadastroEnderecoPresenter(this);

        estadosAutoComplete = mView.findViewById(R.id.textInputEstado);
        adapterEstados = ArrayAdapter.createFromResource(getActivity(),
                R.array.estados_brasil, android.R.layout.simple_spinner_dropdown_item);
        estadosAutoComplete.setAdapter(adapterEstados);
        adapterCidades = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, cidades);
        cidadesAutoComplete = mView.findViewById(R.id.textInputCidade);
        cidadesAutoComplete.setAdapter(adapterCidades);

        endereco = mView.findViewById(R.id.textLayoutFieldEndereco);
        numero = mView.findViewById(R.id.textLayoutFieldNumero);
        complemento = mView.findViewById(R.id.textLayoutFieldComplemento);
        bairro = mView.findViewById(R.id.textLayoutFieldBairro);
        estado = mView.findViewById(R.id.textLayoutFieldEstado);
        cidade = mView.findViewById(R.id.textLayoutFieldCidade);


        estadosAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                cidadesAutoComplete.getText().clear();
                presenter.listarCidadesEstado(s.toString());
                cidadesAutoComplete.setAdapter(adapterCidades);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cidadesAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cidadesAutoComplete.setTag(municipios.get(position));
            }
        });



        return super.onCreateDialog(savedInstanceState);

    }

    private Dialog createDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Adicionar endereço");
        builder.setView(mView);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        });


        return alertDialog;
    }

    @Override
    public void refreshMunicipiosList(List<Municipio> municipios) {

        cidades.clear();
        this.municipios = municipios;
        for (int i = 0; i< municipios.size();i++){
            cidades.add(municipios.get(i).getNome().trim());
        }
        adapterCidades.addAll(cidades);
        cidadesAutoComplete.setAdapter(adapterCidades);
    }

    public boolean isValid() {

        boolean isValid =true;

        isValid = validateEditText(endereco);
        isValid = validateEditText(numero);
        //isValid = validateEditText(complemento);
        isValid = validateEditText(bairro);
        isValid = validateEditText(estado);
        isValid = validateEditText(cidade);

        return isValid;
    }

    private boolean validateEditText(TextInputLayout textInputLayout){

        if (textInputLayout != null){

            if (StringUtils.isNullOrEmpty(textInputLayout.getEditText().getText().toString())){
                textInputLayout.setError("Campo obrigatório");
                return false;
            }else if (textInputLayout.getError()!= null){

                textInputLayout.setError(null);
            }
        }
        return true;
    }


}