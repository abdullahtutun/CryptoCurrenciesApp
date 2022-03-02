package com.example.cryptocurrenciesapp.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cryptocurrenciesapp.adapter.RecyclerViewAdapter;
import com.example.cryptocurrenciesapp.models.CurrencyModel;
import com.example.cryptocurrenciesapp.R;
import com.example.cryptocurrenciesapp.service.CryptoAPI;
import com.example.cryptocurrenciesapp.service.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CurrenciesFragment extends Fragment {

    ArrayList<CurrencyModel> currencyModels;
    private String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecyclerViewAdapter recyclerViewAdapter;
    @BindView(R.id.recyclerView) RecyclerView RecyclerView;
    View view;
    Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_currencies, container, false);

        ButterKnife.bind(this,view);

        gson = new GsonBuilder().setLenient().create();

        RetrofitClient.getClient(BASE_URL,gson);

        loadData();

        return view;
    }

    public void loadData(){

        CryptoAPI cryptoAPI = RetrofitClient.getClient(BASE_URL,gson).create(CryptoAPI.class);

        Call<List<CurrencyModel>> call = cryptoAPI.getData();

        call.enqueue(new Callback<List<CurrencyModel>>() {
            @Override
            public void onResponse(Call<List<CurrencyModel>> call, Response<List<CurrencyModel>> response) {
                if (response.isSuccessful()){
                    List<CurrencyModel> responseList = response.body();
                    currencyModels = new ArrayList<>(responseList);

                    RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerViewAdapter = new RecyclerViewAdapter(currencyModels);
                    RecyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CurrencyModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void goToMetadata(View view,String id){

        CurrenciesFragmentDirections.ActionCurrenciesFragmentToMetadataFragment action = CurrenciesFragmentDirections.actionCurrenciesFragmentToMetadataFragment("BTC");
        action.setCurrencyId(id);
        
        Navigation.findNavController(view).navigate(action);

    }
}