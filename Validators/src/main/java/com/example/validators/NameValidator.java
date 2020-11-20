package com.example.validators;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class NameValidator {

    private TextInputLayout textInputLayout;
    private int hintSize;
    private final String pattern = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,5}$";

    public NameValidator(TextInputLayout textInputLayout){
        this.hintSize = textInputLayout.getHint().length();
        this.textInputLayout = textInputLayout;
    }

    public int getHintSize(){
        return hintSize;
    }
    public boolean validate(){
        String name = textInputLayout.getEditText().getText().toString();

        if ( name.matches(pattern) && !name.isEmpty()){
            textInputLayout.setError(null);
            return true;
        }
        textInputLayout.setError("Nome inválido");
        return false;
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
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
                    //textInputLayout.setHint(textInputLayout.getHint().subSequence(0, hintSize));
                    textInputLayout.setError(null);
                } else {

                    //textInputLayout.setHint(textInputLayout.getHint().subSequence(0, hintSize));
                    //textInputLayout.setHint(textInputLayout.getHint() + "*");
                    textInputLayout.setError("Insira um nome válido");
                }
            }
        });

        return textInputLayout;
    }


}
