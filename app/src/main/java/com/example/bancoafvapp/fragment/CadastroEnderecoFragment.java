package com.example.bancoafvapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.app.BancoAfvApp;
import com.example.bancoafvapp.model.Cliente;
import com.example.bancoafvapp.model.Endereco;
import com.example.bancoafvapp.model.Municipio;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class CadastroEnderecoFragment extends CadastroClienteFragment implements CadastroEnderecoPresenter.View{

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

    private ICadastroCliente onCadastroCliente;

    private TextInputLayout endereco, numero, complemento,
                            bairro, estado, cidade;
    private List<Endereco> enderecos = new ArrayList<>();


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


        presenter = new CadastroEnderecoPresenter(this);
        View view = inflater.inflate(R.layout.fragment_cadastro_endereco, container, false);




        //buttonTag = view.findViewById(R.id.testTAG);
        estadosAutoComplete = view.findViewById(R.id.textInputEstado);
        adapterEstados = ArrayAdapter.createFromResource(getActivity(),
                R.array.estados_brasil, android.R.layout.simple_spinner_dropdown_item);
        estadosAutoComplete.setAdapter(adapterEstados);
        adapterCidades = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, cidades);
        cidadesAutoComplete = view.findViewById(R.id.textInputCidade);
        cidadesAutoComplete.setAdapter(adapterCidades);

        endereco = view.findViewById(R.id.textLayoutFieldEndereco);
        numero = view.findViewById(R.id.textLayoutFieldNumero);
        complemento = view.findViewById(R.id.textLayoutFieldComplemento);
        bairro = view.findViewById(R.id.textLayoutFieldBairro);
        estado = view.findViewById(R.id.textLayoutFieldEstado);
        cidade = view.findViewById(R.id.textLayoutFieldCidade);



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
        return view;
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

    @Override
    public boolean isValid() {
        return false;
    }


    /*
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public Cliente clienteData() {

        Endereco endereco1 = new Endereco();
        endereco1.setEndereco(endereco.getEditText().getText().toString());
        endereco1.setNumero(numero.getEditText().getText().toString());
        endereco1.setComplemento(complemento.getEditText().getText().toString());
        endereco1.setBairro(bairro.getEditText().getText().toString());
        endereco1.setEstado(estado.getEditText().getText().toString());
        endereco1.setCodMunicipio(((Municipio)cidadesAutoComplete.getTag()).getCogigo());
        enderecos.add(endereco1);

        Cliente cliente = new Cliente();
        cliente.setEnderecos(enderecos);

        return cliente;
    }
    *
     */
}