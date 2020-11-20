package com.example.validators;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class EmailValidator {

    private TextInputLayout textInputLayout;

    private final String pattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    // private final String pattern = Patterns.EMAIL_ADDRESS.pattern();

    public EmailValidator(TextInputLayout textInputLayout){

        this.textInputLayout = textInputLayout;
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public void setTextInputLayout(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    public boolean validate(){

        String email = textInputLayout.getEditText().getText().toString();
        if (!email.isEmpty() && email.matches(pattern)){
            textInputLayout.setError(null);
            return true;
        }
        textInputLayout.setError("E-mail inválido");
        return false;
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

                if (s != null && s.toString().matches(pattern)) {
                    textInputLayout.setError(null);
                } else {
                    textInputLayout.setError("Insira um e-mail válido");
                }
            }
        });

        return textInputLayout;
    }


}
