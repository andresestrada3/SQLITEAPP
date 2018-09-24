package com.andresnet.sqliteapp;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarFragment extends Fragment {
    EditText eidd, eNom, eCan,ePre;
    Button bAgre;
    Comunicador enlace;


    public AgregarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar, container, false);
        eidd = view.findViewById(R.id.eID);
        eNom = view.findViewById(R.id.eNombre);
        eCan = view.findViewById(R.id.eCantidad);
        ePre = view.findViewById(R.id.ePrecio);
        bAgre = view.findViewById(R.id.bAgregar);

        bAgre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idd2;
                String nombre1;
                int cant;
                double prec;
                if(eidd.getText().toString().isEmpty()||eNom.getText().toString().isEmpty()||eCan.getText().toString().isEmpty()||ePre.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Llene todos los datos", Toast.LENGTH_SHORT).show();
                }
                else {
                    idd2 = Integer.parseInt(eidd.getText().toString());
                    nombre1 = eNom.getText().toString();
                    cant = Integer.parseInt(eCan.getText().toString());
                    prec = Double.parseDouble(ePre.getText().toString());
                    enlace.envioDatos(idd2, nombre1, cant, prec);
                    Toast.makeText(getActivity(),"Contacto almacenado",Toast.LENGTH_SHORT).show();
                    cleanWidgtes();


                }
            }
        });





        return view;
    }
    private void cleanWidgtes() {
        eidd.setText("");
        eNom.setText("");
        eCan.setText("");
        ePre.setText("");
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
