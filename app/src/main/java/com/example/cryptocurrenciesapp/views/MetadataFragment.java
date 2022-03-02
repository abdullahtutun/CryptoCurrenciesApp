package com.example.cryptocurrenciesapp.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cryptocurrenciesapp.models.MetadataModel;
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

public class MetadataFragment extends Fragment {

    @BindView(R.id.tvSymbol) TextView Symbol;
    @BindView(R.id.tvName) TextView Name;
    @BindView(R.id.tvWebsiteUrl) TextView WebsiteUrl;
    @BindView(R.id.tvBlogUrl) TextView BlogUrl;
    @BindView(R.id.tvGithubUrl) TextView GithubUrl;
    @BindView(R.id.tvTwitterUrl) TextView TwitterUrl;
    @BindView(R.id.tvWhitepaperUrl) TextView WhitepaperUrl;
    ArrayList<MetadataModel> metadataModels;
    private String BASE_URL = "https://api.nomics.com/v1/";
    String id;
    Retrofit retrofit;
    View view;
    Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gson = new GsonBuilder().setLenient().create();

        RetrofitClient.getClient(BASE_URL,gson);

        getIdFromCurrencies();

        loadMetadata(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_metadata, container, false);

        ButterKnife.bind(this,view);

        return view;
    }


    public void getIdFromCurrencies(){
        id = MetadataFragmentArgs.fromBundle(getArguments()).getCurrencyId();
    }


    public void loadMetadata(String id){

        CryptoAPI cryptoAPI = RetrofitClient.getClient(BASE_URL,gson).create(CryptoAPI.class);

        Call<List<MetadataModel>> call = cryptoAPI.getMetadata(id);

        call.enqueue(new Callback<List<MetadataModel>>() {
            @Override
            public void onResponse(Call<List<MetadataModel>> call, Response<List<MetadataModel>> response) {
                if(response.isSuccessful()){
                    List<MetadataModel> responseList = response.body();
                    metadataModels = new ArrayList<>(responseList);


                    changeText();

                    makeNull(metadataModels);
                }
            }
            @Override
            public void onFailure(Call<List<MetadataModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void changeText(){
        Symbol.setText(metadataModels.get(0).id);
        Name.setText(metadataModels.get(0).name);
        WebsiteUrl.setText(metadataModels.get(0).website_url);
        BlogUrl.setText(metadataModels.get(0).blog_url);
        GithubUrl.setText(metadataModels.get(0).github_url);
        TwitterUrl.setText(metadataModels.get(0).twitter_url);
        WhitepaperUrl.setText(metadataModels.get(0).whitepaper_url);
    }

    public void makeNull(List<MetadataModel> metadataModels){

        if(metadataModels.get(0).blog_url==""){
            BlogUrl.setText("null");
        }

    }




}