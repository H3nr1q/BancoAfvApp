package com.example.bancoafvapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bancoafvapp.fragment.ProdutoStatusFragment;

public class ProdutosPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 4;
    public ProdutosPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        String status;
        switch (position){
            case 1: status = "P";break;
            case 2: status = "L";break;
            case 3: status = "R";break;
            default:
                status = "N";break;
        }

        return ProdutoStatusFragment.newInstance(status);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        CharSequence title;
        switch (position){
            case 0: title = "NORMAL";break;
            case 1: title = "P.ESTOQUE";break;
            case 2: title = "Lançamento";break;
            case 3: title = "Promoção";break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return title;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

}
