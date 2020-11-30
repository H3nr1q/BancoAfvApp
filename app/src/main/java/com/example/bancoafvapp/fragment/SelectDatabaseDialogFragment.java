package com.example.bancoafvapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bancoafvapp.R;
import com.example.bancoafvapp.app.BancoAfvApp;
import com.example.bancoafvapp.helper.DatabaseSelector;
import com.example.bancoafvapp.helper.DbHelper;

public class SelectDatabaseDialogFragment extends DialogFragment implements View.OnClickListener {


    private View mview;
    private Button bntBanco1, bntBanco2, bntBanco3;
    private OnDialogButtonClick onDialogButtonClick;


    public SelectDatabaseDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        mview = inflater.inflate(R.layout.fragment_select_database_dialog, null, false);

        bntBanco1 = mview.findViewById(R.id.bntSelectDatabase1);
        bntBanco2 = mview.findViewById(R.id.bntSelectDatabase2);
        bntBanco3 = mview.findViewById(R.id.bntSelectDatabase3);

        bntBanco1.setOnClickListener(this);
        bntBanco2.setOnClickListener(this);
        bntBanco3.setOnClickListener(this);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(mview);

        return builder.create();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bntSelectDatabase1:
                DbHelper.getInstance(DatabaseSelector.getInstance().getDbName()).setNull();
                DatabaseSelector.getInstance().setDbName(DbHelper.DB_1);
                onDialogButtonClick.onButtonClick("");
                dismiss();
                break;
            case R.id.bntSelectDatabase2:
                DbHelper.getInstance(DatabaseSelector.getInstance().getDbName()).setNull();
                DatabaseSelector.getInstance().setDbName(DbHelper.DB_2);
                onDialogButtonClick.onButtonClick("");
                dismiss();
                break;
            case R.id.bntSelectDatabase3:
                DbHelper.getInstance(DatabaseSelector.getInstance().getDbName()).setNull();
                DatabaseSelector.getInstance().setDbName(DbHelper.DB_3);
                onDialogButtonClick.onButtonClick("");
                dismiss();
                break;
        }
    }

    public interface OnDialogButtonClick{

        void onButtonClick(String string);
    }

    public void setOnDialogButtonClick(OnDialogButtonClick onDialogButtonClick){

        this.onDialogButtonClick = onDialogButtonClick;
    }

}