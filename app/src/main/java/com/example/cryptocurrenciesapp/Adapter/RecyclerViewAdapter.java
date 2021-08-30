package com.example.cryptocurrenciesapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrenciesapp.Models.CurrencyModel;
import com.example.cryptocurrenciesapp.R;
import com.example.cryptocurrenciesapp.Views.CurrenciesFragment;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<CurrencyModel> cryptoList;

    public RecyclerViewAdapter(ArrayList<CurrencyModel> cryptoList) {
        this.cryptoList = cryptoList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {

        holder.textName.setText(cryptoList.get(position).currency);
        holder.textPrice.setText(cryptoList.get(position).price);

        holder.linearLayoutCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrenciesFragment cf = new CurrenciesFragment();
                cf.goToMetadata(v,cryptoList.get(position).currency);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName,textPrice;
        LinearLayout linearLayoutCurrency;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.text_name);
            textPrice = itemView.findViewById(R.id.text_price);
            linearLayoutCurrency = itemView.findViewById(R.id.linearLayoutCurrency);

        }
    }



}
