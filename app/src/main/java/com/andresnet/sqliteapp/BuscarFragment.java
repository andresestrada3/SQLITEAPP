package com.andresnet.sqliteapp;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {
    private Button bBusqueda;
    private EditText eNom;
    private TextView tBusqueda;
    Comunicador enlace;


    public BuscarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_buscar, container, false);
        bBusqueda = view.findViewById(R.id.bBuscar);
        eNom = view.findViewById(R.id.eBuscar);
        tBusqueda = view.findViewById(R.id.tbNombre);
        bBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eNom.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Digite el Nombre", Toast.LENGTH_SHORT).show();

                }
                else {
                    String nombre = eNom.getText().toString();
                    enlace.envioDatosBus(nombre);
                }

            }
        });
        Bundle bundle = getArguments();
        String bNom = bundle.getString("nombre");
        int bid = bundle.getInt("id");
        int cant = bundle.getInt("cantidad");
        double pre = bundle.getDouble("precio");
        tBusqueda.setText("\nID: " + bid + "\nNombre: " + bNom + "\nCantidad: " + cant + "\nPrecio: " + pre);








        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            enlace = (Comunicador) activity;
        }catch(ClassCastException e){

        }
    }

}
