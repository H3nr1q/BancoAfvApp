package com.example.bancoafvapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.adapter.PrecoAdapter;
import com.example.bancoafvapp.app.BancoAfvApp;

import java.util.ArrayList;
import java.util.List;

public class PriceDialogFragment extends DialogFragment implements PriceDialogFragPresenter.View {

    private static final String KEY_PROCODE = "productCode";

    private String productCode;
    private PrecoAdapter precoAdapter;
    private RecyclerView recyclerView;
    private View mView;
    private PriceDialogFragPresenter presenter;
    List<String> precos = new ArrayList<>();

    public PriceDialogFragment() {}

    public static PriceDialogFragment newInstance(String productCode) {
        PriceDialogFragment fragment = new PriceDialogFragment();
        Bundle args = new Bundle();
        args.putString(KEY_PROCODE, productCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productCode = getArguments().getString(KEY_PROCODE);
        }
        presenter = new PriceDialogFragPresenter(this);

    }
    /*
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PriceDialogFragment() {
    }

    public static PriceDialogFragment newInstance(String param1, String param2) {
        PriceDialogFragment fragment = new PriceDialogFragment();
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
        // Inflate the layout for this fragment

        return mView ;
    }
        */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.fragment_price_dialog, null, false);
        recyclerView = mView.findViewById(R.id.priceDialogRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        precoAdapter = new PrecoAdapter(precos);
        presenter.listarPrecos(productCode);
        recyclerView.setAdapter(precoAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Preços");
        builder.setView(mView);
        builder.setNegativeButton("FECHAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    @Override
    public void refreshPrecoAdapter(List<String> precos) {
        precoAdapter.setPrecosList(precos);
    }
}