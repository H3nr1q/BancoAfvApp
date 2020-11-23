package com.example.bancoafvapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.adapter.ClientesPagerAdapter;
import com.example.bancoafvapp.app.BancoAfvApp;
import com.example.bancoafvapp.fragment.CadastroClienteFragment;
import com.example.bancoafvapp.fragment.ICadastroCliente;
import com.example.bancoafvapp.model.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class CadastroClienteActivity extends AppCompatActivity implements ICadastroCliente {

    public final static int REQUEST_CODE = 10000;

    private Toolbar toolbar;
    private ClientesPagerAdapter clientesPagerAdapter;
    private ViewPager viewPager;
    private Cliente cliente;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        cliente = new Cliente();
        bindViews();
    }

    private void bindViews(){
        toolbar = findViewById(R.id.cadastroClienteToolbar);
        toolbar.setTitle("Adicionar Cliente");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        floatingActionButton = findViewById(R.id.fButtonSaveClientes);

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


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CadastroClienteActivity.this, cliente.getRazaoSocial() + " " + cliente.getNomeFantasia(), Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home ){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

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