package com.andresnet.sqliteapp;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerInventarioFragment extends Fragment {
    private RecyclerView recyclerPeluchitos;
    private ArrayList<Peluchito> listadePeluches;
    private BaseSQLite conn;
    private AdapterPeluches adap;
    private RecyclerView.LayoutManager lay;


    public VerInventarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_inventario, container, false);
        recyclerPeluchitos = view.findViewById(R.id.lrecycler);
        listadePeluches = new ArrayList<>();
        recyclerPeluchitos.setHasFixedSize(true);
        lay = new LinearLayoutManager(getActivity());
        recyclerPeluchitos.setLayoutManager(new LinearLayoutManager(getContext()));
        adap = new AdapterPeluches(listadePeluches);
        recyclerPeluchitos.setAdapter(adap);
        recyclerPeluchitos.setLayoutManager(lay);

        conn = new BaseSQLite(getContext(),"inventario", null,1);

        LlenarRecyler();
        return view;
    }

    private void LlenarRecyler() {
        SQLiteDatabase db;
        //db = conn.getWritableDatabase();
        Peluchito peluchito;
        BaseSQLite baseSQLite;
        baseSQLite = new BaseSQLite(
                getActivity(),
                "pelucheList",
                null,
                1);
        db = baseSQLite.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT*FROM inventario"
                                   ,null);
        if (cursor.moveToFirst()){
            do {
                peluchito = new Peluchito(cursor.getInt(1),cursor.getString(2),cursor.getInt(3), cursor.getDouble(4));
                listadePeluches.add(peluchito);
            }while (cursor.moveToNext());{
                adap.notifyDataSetChanged();
            }

        }


    }

}
