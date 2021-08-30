package com.example.cryptocurrenciesapp.Service;


import com.example.cryptocurrenciesapp.Models.CurrencyModel;
import com.example.cryptocurrenciesapp.Models.MetadataModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CryptoAPI {

    //https://api.nomics.com/v1/
    //prices?key=f23b2fee5d6ebd000b66782d117e6bc73bcf0938

    @GET("prices?key=f23b2fee5d6ebd000b66782d117e6bc73bcf0938")
    Call<List<CurrencyModel>> getData();

    @GET("currencies?key=f23b2fee5d6ebd000b66782d117e6bc73bcf0938")
    Call<List<MetadataModel>> getMetadata(@Query("ids") String ids);

}


//={currency}&attributes=id,name,description,website_url,blog_url,github_url,twitter_url,whitepaper_url