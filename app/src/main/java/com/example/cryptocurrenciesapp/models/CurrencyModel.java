package com.example.cryptocurrenciesapp.models;

import com.google.gson.annotations.SerializedName;

public class CurrencyModel {
    @SerializedName("currency")
    public String currency;

    @SerializedName("price")
    public String price;
}
