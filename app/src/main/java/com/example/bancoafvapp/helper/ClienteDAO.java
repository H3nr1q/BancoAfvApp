package com.example.bancoafvapp.helper;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.bancoafvapp.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends DAO<Cliente>{

    private static final String TABLE = "GUA_CLIENTES";
    private static final String KEY_CGCCPF = "CLI_CGCCPF";
    private static final String KEY_CODIGOCLIENTE = "CLI_CODIGOCLIENTE";
    private static final String KEY_RAZAOSOCIAL = "CLI_RAZAOSOCIAL";
    private static final String KEY__NOMEFANTASIA = "CLI_NOMEFANTASIA";
    private static final String KEY_TELEFONE = "CLI_TELEFONE";
    private static final String KEY_TELEFONE2 = "CLI_FAX";
    private static final String KEY_EMAIL = "CLI_EMAIL";
    private static final String KEY_EMAILSECUNDARIO = "CLI_EMAILSECUNDARIO";


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
        return cliente;
    }
}
