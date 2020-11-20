package com.example.bancoafvapp.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bancoafvapp.fragment.CadastroClienteFragment;
import com.example.bancoafvapp.fragment.CadastroDadosClienteFragment;
import com.example.bancoafvapp.fragment.CadastroEmailFragment;
import com.example.bancoafvapp.fragment.CadastroEnderecoFragment;
import com.example.bancoafvapp.fragment.CadastroTelefoneFragment;

public class ClientesPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 4;

    public ClientesPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 1: return new CadastroTelefoneFragment();
            case 2: return new CadastroEmailFragment();
            case 3: return new CadastroEnderecoFragment();
        }
        return new CadastroDadosClienteFragment();

    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        CharSequence title;
        switch (position){
            case 0: title = "DADOS";break;
            case 1: title = "TELEFONE";break;
            case 2: title = "EMAIL";break;
            case 3: title = "ENDEREÇO";break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return title;
    }
}
