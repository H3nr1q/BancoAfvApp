package com.example.validators;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class PhoneNumberValidator {

    private TextInputLayout textInputLayout;

    private final String pattern  = "^\\s*(\\d{2}|\\d{0})[-. ]?(\\d{5}|\\d{4})[-. ]?(\\d{4})[-. ]?\\s*$";

    public PhoneNumberValidator(TextInputLayout textInputLayout){

        this.textInputLayout = textInputLayout;
    }

    public boolean validate(){
        String phone = textInputLayout.getEditText().getText().toString();
        if (phone.matches(pattern) && !phone.isEmpty()){
            textInputLayout.setError(null);
            return true;
        }
        textInputLayout.setError("Número inválido");
        return false;
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public void setTextInputLayout(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

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

                if (s !=null && s.toString().matches(pattern)) {
                    textInputLayout.setError(null);
                } else {
                    textInputLayout.setError("Insira um telefone válido");
                }
            }
        });

        return textInputLayout;
    }

}
