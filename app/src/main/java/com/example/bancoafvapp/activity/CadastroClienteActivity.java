package com.example.bancoafvapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.adapter.ClientesPagerAdapter;
import com.example.bancoafvapp.app.BancoAfvApp;
import com.example.bancoafvapp.fragment.CadastroClienteFragment;
import com.example.bancoafvapp.fragment.ICadastroCliente;
import com.example.bancoafvapp.model.Cliente;
import com.example.bancoafvapp.model.Endereco;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastroClienteActivity extends AppCompatActivity implements ICadastroCliente {

    public final static int REQUEST_CODE = 10000;

    private Toolbar toolbar;
    private ClientesPagerAdapter clientesPagerAdapter;
    private ViewPager viewPager;
    private Cliente cliente;
    private FloatingActionButton floatingActionButton;

    private CadastroClientePresenter cadastroClientePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        if (savedInstanceState!=null){
            cliente = savedInstanceState.getParcelable("cliente");
            if (cliente == null) cliente = new Cliente();
        }else {
            cliente = new Cliente();
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String code = df.format(new Date().getTime());

            cliente.setCodigoCliente(code);
        }
        cadastroClientePresenter = new CadastroClientePresenter();
        bindViews();
    }
    private void bindViews(){
        toolbar = findViewById(R.id.cadastroClienteToolbar);
        toolbar.setTitle("Adicionar Cliente");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //floatingActionButton = findViewById(R.id.fButtonSaveClientes);

        clientesPagerAdapter = new ClientesPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.clientesPager);
        viewPager.setAdapter(clientesPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.client_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                isCurrentClienteValido(position);
                //isClienteValido();
                Toast.makeText(CadastroClienteActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

/*
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CadastroClienteActivity.this, cliente.getEmailPrincipal() + " " + cliente.getEmailSecundario(), Toast.LENGTH_LONG).show();
            }
        });*/

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (cliente != null){
            outState.putParcelable("cliente", cliente);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.opcoes_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.optionSalvar:


                Toast.makeText(CadastroClienteActivity.this, cliente.getEmailPrincipal() + " " + cliente.getCodigoCliente(), Toast.LENGTH_LONG).show();

                if(isClienteValido()){

                    if (cliente != null) {
                        if (cadastroClientePresenter.saveOrEditCliente(cliente)) {
                            if (cliente.getEnderecos()!=null) {
                                boolean response = false;
                                for (Endereco end : cliente.getEnderecos()) {

                                    response = cadastroClientePresenter.saveOrEditEndereco(end);
                                }
                                if (response) {
                                    Toast.makeText(CadastroClienteActivity.this, "Cliente salvo com sucesso", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }

                break;

            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


/*
    public boolean isCurrentClienteValido(int position){
        if(position>0) {
            Fragment fragment = getPage(position - 1);
            if (fragment instanceof CadastroClienteFragment) {
                if (!((CadastroClienteFragment) fragment).isValid()) {
                    viewPager.setCurrentItem(position - 1);
                    return false;
                }
            }
        }
        return true;
    }*/

    public boolean isCurrentClienteValido(int position){
        if (position > 0) {
            for (int i = 0; i < position; i++) {

                Fragment fragment = getPage(i);
                if (fragment instanceof CadastroClienteFragment) {
                    if (!((CadastroClienteFragment) fragment).isValid()) {
                        viewPager.setCurrentItem(i);
                        return false;
                    }
                }
            }
        }else {
            Fragment fragment = getPage(viewPager.getCurrentItem());
            if (fragment instanceof  CadastroClienteFragment){
                if (!((CadastroClienteFragment) fragment).isValid()){
                    viewPager.setCurrentItem(viewPager.getCurrentItem());
                }
                return false;
            }
        }
        return true;
    }

    public boolean isClienteValido(){
        for (int position = 0; position < clientesPagerAdapter.getCount(); position++) {
            Fragment fragment = getPage(position);
            if(fragment instanceof CadastroClienteFragment){
                if(!((CadastroClienteFragment) fragment).isValid()){
                    viewPager.setCurrentItem(position);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }

    public Fragment getPage(int position){
        return (Fragment) clientesPagerAdapter.instantiateItem(viewPager, position);
    }
}