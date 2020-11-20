package com.example.validators;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class CNPJValidator {

    private TextInputLayout textInputLayout;
    private final String PATTERN = "/^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$/";
    private TextWatcher cnpjMask;

    public CNPJValidator(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        cnpjMask = CpfCnpjMask.insert("##.###.###/####-##",textInputLayout.getEditText());
        if (textInputLayout.getEditText() != null)
            textInputLayout.getEditText().addTextChangedListener(cnpjMask);
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public void setTextInputLayout(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    public boolean validate(){
        String cpfCnpj = textInputLayout.getEditText().toString();

        if (!cpfCnpj.isEmpty() && cpfCnpj.matches(PATTERN)){
            textInputLayout.setError(null);
            return true;
        }
        textInputLayout.setError("CNPJ inválido");
        return false;
    }

    /*
    public TextInputLayout validateAfterTextChanged(){

        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s != null && s.toString().matches(PATTERN)) {
                    textInputLayout.setError(null);
                } else {
                    textInputLayout.setError("Insira um CNPJ válido");
                }
            }
        });

        return textInputLayout;
    }

     */
}
