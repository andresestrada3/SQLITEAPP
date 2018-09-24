package com.andresnet.sqliteapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterPeluches extends RecyclerView.Adapter<AdapterPeluches.AdapterViewHolder> {
    private ArrayList<Peluchito> listadePeluches;


    

    public AdapterPeluches(ArrayList<Peluchito> listadePeluches) {
        this.listadePeluches = listadePeluches;
    }


    @NonNull
    @Override
    public AdapterPeluches.AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new AdapterViewHolder(itemView);





    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        Peluchito peluchito = listadePeluches.get(position);
        holder.bindPeluchitos(peluchito);
    }

    @Override
    public int getItemCount() {
        return listadePeluches.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tdatos;


        public AdapterViewHolder(View itemView) {
            super(itemView);
            tdatos = itemView.findViewById(R.id.ltdatos);
        }

        public void bindPeluchitos(Peluchito peluchito) {
            tdatos.setText("Datos: " +peluchito.datos());
        }
    }
}
