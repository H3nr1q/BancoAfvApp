package com.example.bancoafvapp.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bancoafvapp.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends DAO<Cliente>{

    private static final String TABLE = "GUA_CLIENTES";
    private static final String TABLE_ENDERECOS = "GUA_ENDERECOSADICIONAIS";
    private static final String KEY_CGCCPF = "CLI_CGCCPF";
    private static final String KEY_CODIGOCLIENTE = "CLI_CODIGOCLIENTE";
    private static final String KEY_RAZAOSOCIAL = "CLI_RAZAOSOCIAL";
    private static final String KEY__NOMEFANTASIA = "CLI_NOMEFANTASIA";
    private static final String KEY_TELEFONE = "CLI_TELEFONE";
    private static final String KEY_TELEFONE2 = "CLI_FAX";
    private static final String KEY_EMAIL = "CLI_EMAIL";
    private static final String KEY_EMAILSECUNDARIO = "CLI_EMAILSECUNDARIO";
    private static final String KEY_ENDERECO = "CLI_ENDERECO";
    private static final String KEY_NUMERO = "CLI_NUMERO";
    private static final String KEY_COMPLEMENTO = "CLI_COMPLEMENTO";
    private static final String KEY_BAIRRO = "CLI_BAIRRO";
    private static final String KEY_CODIGOMUNICIPIO = "CLI_CODIGOMUNICIPIO";


    public static ClienteDAO clienteDAO;

    public ClienteDAO(){}

    public synchronized static ClienteDAO getInstance(){

        if (clienteDAO == null){
            clienteDAO = new ClienteDAO();
        }
        return clienteDAO;
    }

    @Override
    public List<Cliente> selectAll() {

        List<Cliente> clienteList = new ArrayList<>();
        Cursor c = getReadableDB().query(TABLE,new String[]{"*"}, null, null, null, null, null);

        while (c.moveToNext()){
            clienteList.add(bind(c));
        }

        return clienteList;
    }

    @Override
    public List<Cliente> selectByAttributes(String query) {

        List<Cliente> clientes = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE + " WHERE ? LIKE " + KEY_CGCCPF + " OR ? LIKE " + KEY_RAZAOSOCIAL +
                " OR ? LIKE " + KEY__NOMEFANTASIA + ";";

        Cursor c = getReadableDB().rawQuery(sql, new String[]{"%".concat(query)});

        while (c.moveToNext()){
            clientes.add(bind(c));
        }

        return clientes;
    }

    @Override
    public boolean saveOrEdit(Cliente cliente) {

        try {
            getWritableDB().insertWithOnConflict(TABLE, null, bindValues(cliente), SQLiteDatabase.CONFLICT_REPLACE);

        }catch (Exception e){

            Log.i("INFO", "Erro ao salvar o cliente" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Cliente cliente) {

        String[] args = {cliente.getCodigoCliente()};

        try {
            getWritableDB().delete(TABLE, "CLI_CODIGOCLIENTE LIKE ?", args );
            getWritableDB().delete(TABLE_ENDERECOS, "EAD_CODIGOCLIENTE LIKE ?", args);
            Log.i("INFO", "Sucesso ao deletar o cliente.");
        }catch (Exception e){
            Log.i("INFO", "Erro ao deletar o cliente" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    protected ContentValues bindValues(Cliente cliente) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_CODIGOCLIENTE, cliente.getCodigoCliente());
        contentValues.put(KEY_CGCCPF, cliente.getCpfCnpj());
        contentValues.put(KEY_RAZAOSOCIAL, cliente.getRazaoSocial());
        contentValues.put(KEY__NOMEFANTASIA, cliente.getNomeFantasia());
        contentValues.put(KEY_TELEFONE, cliente.getTelefone1());
        contentValues.put(KEY_TELEFONE2, cliente.getTelefone2());
        contentValues.put(KEY_EMAIL, cliente.getEmailPrincipal());
        contentValues.put(KEY_EMAILSECUNDARIO, cliente.getEmailSecundario());
        contentValues.put(KEY_ENDERECO, cliente.getEndereco());
        contentValues.put(KEY_NUMERO, cliente.getNumero());
        contentValues.put(KEY_COMPLEMENTO, cliente.getComplemento());
        contentValues.put(KEY_BAIRRO, cliente.getBairro());
        contentValues.put(KEY_CODIGOMUNICIPIO, cliente.getCodMunicipio());

        return contentValues;
    }

    @Override
    protected Cliente bind(Cursor c) {

        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(getString(c, KEY_CODIGOCLIENTE));
        cliente.setCpfCnpj(getString(c, KEY_CGCCPF));
        cliente.setRazaoSocial(getString(c, KEY_RAZAOSOCIAL));
        cliente.setNomeFantasia(getString(c, KEY__NOMEFANTASIA));
        cliente.setTelefone1(getString(c, KEY_TELEFONE));
        cliente.setTelefone2(getString(c, KEY_TELEFONE2));
        cliente.setEmailPrincipal(getString(c, KEY_EMAIL));
        cliente.setEmailSecundario(getString(c, KEY_EMAILSECUNDARIO));
        cliente.setEndereco(getString(c, KEY_ENDERECO));
        cliente.setNumero(getString(c,KEY_NUMERO));
        cliente.setComplemento(getString(c, KEY_COMPLEMENTO));
        cliente.setBairro(getString(c, KEY_BAIRRO));
        cliente.setCodigoCliente(getString(c, KEY_CODIGOMUNICIPIO));

        return cliente;
    }
}
