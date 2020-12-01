package com.example.bancoafvapp.utils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class FormatUtils {


    public static String currency(double value){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setMaximumFractionDigits(3);
        numberFormat.setCurrency(Currency.getInstance(Locale.getDefault()));
        return numberFormat.format(value);
    }

}
