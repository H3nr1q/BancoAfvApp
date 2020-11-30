package com.example.bancoafvapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.model.Cliente;

public class DadosClienteActivity extends AppCompatActivity {

    private Cliente cliente;

    private TextView razaoSocial, nomeFantasia, telefone1, telefone2, email1, email2, endereco;

    private DadosClientePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cliente);

        Toolbar toolbar = findViewById(R.id.dadosClienteToolbar);
        setSupportActionBar(toolbar);
        cliente = new Cliente();
        presenter = new DadosClientePresenter();

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("Dados Cliente");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (getIntent().getExtras()!=null){

            cliente = getIntent().getExtras().getParcelable("cliente");
        }
        razaoSocial = findViewById(R.id.razaoSocialDadosCliente);
        nomeFantasia = findViewById(R.id.nomeFansatiaDadosCliente);
        telefone1 = findViewById(R.id.telefone1DadosCliente);
        telefone2 = findViewById(R.id.telefone2DadosCliente);
        email1 = findViewById(R.id.email1DadosCliente);
        email2 = findViewById(R.id.email2DadosCliente);
        endereco = findViewById(R.id.enderecoDadosCliente);

        String cidade = "";

        if (cliente.getCodMunicipio()!= null){
            cidade = presenter.getNomeCidade(cliente.getCodigoCliente());
        }

        if (cliente!=null){
            razaoSocial.setText(cliente.getRazaoSocial());
            nomeFantasia.setText(cliente.getNomeFantasia());
            telefone1.setText(cliente.getTelefone1());
            telefone2.setText(cliente.getTelefone2());
            email1.setText(cliente.getEmailPrincipal());
            email2.setText(cliente.getEmailSecundario());
            endereco.setText(String.format("%s, %s, %s\n%s, %s", cliente.getEndereco(), cliente.getNumero(), cliente.getComplemento(), cliente.getBairro(), cidade));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}