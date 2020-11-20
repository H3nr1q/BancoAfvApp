package com.example.bancoafvapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.adapter.ClientesPagerAdapter;
import com.example.bancoafvapp.model.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public abstract class CadastroClienteFragment extends Fragment {

    private ICadastroCliente mOnCadastroClienteListener;

    public CadastroClienteFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ICadastroCliente){
            mOnCadastroClienteListener = (ICadastroCliente) context;
        }
    }

    protected Cliente getCliente(){
        return mOnCadastroClienteListener.getCliente();
    }

    public abstract boolean isValid();
}