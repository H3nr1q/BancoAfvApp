package com.example.bancoafvapp.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.adapter.ProdutosAdapter;
import com.example.bancoafvapp.model.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProdutoStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProdutoStatusFragment extends Fragment implements ProdutosFragmentPresenter.View, ProdutosAdapter.OnProdutoClickListener{

    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_STATUS = "status";

    private ProdutosAdapter produtosAdapter;
    private List<Produto> produtoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProdutosFragmentPresenter presenter;

    private String status;

    public ProdutoStatusFragment() {
        // Required empty public constructor
    }

    public static ProdutoStatusFragment newInstance(String status) {
        ProdutoStatusFragment fragment = new ProdutoStatusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            status = getArguments().getString(ARG_STATUS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produto_normal, container, false);

        bindView(view);

        return view;
    }

    public void bindView(View view){

        recyclerView = view.findViewById(R.id.recyclerViewProdutosNormal);
        presenter = new ProdutosFragmentPresenter(this);
        produtosAdapter = new ProdutosAdapter(produtoList);
        produtosAdapter.setOnProdutoClickListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        presenter.listarProdutosByStatus(status);
        recyclerView.setAdapter(produtosAdapter);

    }
    @Override
    public void refreshAdapterList(List<Produto> produtos) {
        produtosAdapter.setProdutoList(produtos);
    }

    @Override
    public void onProdutoItemSelected(int position, Produto produto) {

        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("teste");
        builder.setMessage(produto.getDescricao());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        */
        PriceDialogFragment priceDialogFragment = PriceDialogFragment.newInstance(produto.getCodigo());
        priceDialogFragment.show(getActivity().getSupportFragmentManager(), "pricedialog");

    }
}