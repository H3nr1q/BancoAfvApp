package com.example.bancoafvapp.helper;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.bancoafvapp.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends ProDAO<Produto> {

    private static final String TABLE_PRODUTOS = "GUA_PRODUTOS";
    private static final String KEY_CODIGO = "PRO_CODIGO";
    private static final String KEY_DESCRICAO = "PRO_DESCRICAO";
    private static final String KEY_STATUS = "PRO_STATUS";

    private static final String TABLE_ESTOQUE = "GUA_ESTOQUEEMPRESA";
    private static final String KEY_CODIGOPESTOQUE = "ESE_CODIGO";
    private static final String KEY_ESTOQUE = "ESE_ESTOQUE";

    private static final String TABLE_PRECOS = "GUA_PRECOS";
    private static final String KEY_PRECOD = "PRP_CODIGO";
    private static final String KEY_PRECO = "PRP_PRECOS";
    private static final String KEY_PRECOMIN = "PRECO_MIN";
    private static final String KEY_PRECOMAX = "PRECO_MAX";


    public static ProdutoDAO produtoDAO;

    public ProdutoDAO(){}

    public synchronized static ProdutoDAO getInstance(){

        if (produtoDAO == null){
            produtoDAO = new ProdutoDAO();
        }
        return produtoDAO;

    }

    @Override
    public List<Produto> selectByStatus(String status) {

        List<Produto> produtoList = new ArrayList<>();
        String sql = "SELECT p."+KEY_CODIGO+" ,p."+KEY_DESCRICAO+" ,p."+KEY_STATUS + ", e."+KEY_ESTOQUE+
                ", MIN("+"pc."+KEY_PRECO+")"+ " AS " + KEY_PRECOMIN +
                ", MAX("+"pc."+KEY_PRECO+")"+ " AS " + KEY_PRECOMAX +
                " FROM " + TABLE_PRODUTOS + " p " +
                " INNER JOIN "+ TABLE_ESTOQUE + " e "+
                " ON " + "p."+ KEY_CODIGO + " = " + "e." +KEY_CODIGOPESTOQUE+
                " INNER JOIN " + TABLE_PRECOS + " pc" +
                " ON p." + KEY_CODIGO + " = "+"pc." + KEY_PRECOD +
                " WHERE" + " p."+KEY_STATUS +" LIKE ?" +
                " GROUP BY" + " p." + KEY_CODIGO + ";";

        Cursor c = getReadableDB().rawQuery(sql, new String[]{"%".concat(status).concat("%")});
        while (c.moveToNext()){

            produtoList.add(bind(c));
        }

        return produtoList;
    }

    @Override
    public List<String> selectPrices(String code) {

        List<String> precosList = new ArrayList<>();
        String sql = " SELECT " + KEY_PRECO + " FROM " + TABLE_PRECOS +
                " WHERE " + KEY_PRECOD + " LIKE ?" +
                " GROUP BY " + KEY_PRECO +
                " ORDER BY " + KEY_PRECO+
                " ASC";

        Cursor c = getReadableDB().rawQuery(sql, new String[]{"%".concat(code).concat("%")});
        while (c.moveToNext()){
            precosList.add(c.getString(c.getColumnIndex(KEY_PRECO)));
        }
        return precosList;
    }

    @Override
    public List<Produto> selectAll() {

        List<Produto> produtoList = new ArrayList<>();
        String sql = "SELECT p."+KEY_CODIGO+" ,p."+KEY_DESCRICAO+" ,p."+KEY_STATUS + ", e."+KEY_ESTOQUE+
                ", MIN("+"pc."+KEY_PRECO+")"+ " AS " + KEY_PRECOMIN +
                ", MAX("+"pc."+KEY_PRECO+")"+ " AS " + KEY_PRECOMAX +
                " FROM " + TABLE_PRODUTOS + " p " +
                " INNER JOIN "+ TABLE_ESTOQUE + " e "+
                " ON " + "p."+ KEY_CODIGO + " = " + "e." +KEY_CODIGOPESTOQUE+
                " INNER JOIN " + TABLE_PRECOS + " pc" +
                " ON p." + KEY_CODIGO + " = "+"pc." + KEY_PRECOD +
                " GROUP BY" + " p." + KEY_CODIGO;

        Cursor c = getReadableDB().rawQuery(sql, null);
        while (c.moveToNext()){

            produtoList.add(bind(c));
        }

        return produtoList;
    }

    @Override
    protected ContentValues bindValues(Produto produto) {
        return null;
    }

    @Override
    protected Produto bind(Cursor c) {

        Produto produto = new Produto();
        produto.setCodigo(getString(c,KEY_CODIGO));
        produto.setDescricao(getString(c, KEY_DESCRICAO));
        produto.setEstoque(Double.valueOf( getString(c, KEY_ESTOQUE)));
        produto.setPrecoMax(Double.valueOf(getString(c, KEY_PRECOMAX)));
        produto.setPrecoMin(Double.valueOf(getString(c, KEY_PRECOMIN)));
        produto.setStatus(getString(c, KEY_STATUS));

        return produto;
    }
}
