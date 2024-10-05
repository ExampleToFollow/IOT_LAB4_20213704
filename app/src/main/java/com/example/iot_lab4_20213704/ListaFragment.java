package com.example.iot_lab4_20213704;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.iot_lab4_20213704.Adapter.ListaLigasAdapter;
import com.example.iot_lab4_20213704.Beans.Liga;
import com.example.iot_lab4_20213704.Beans.LigaBusqueda;
import com.example.iot_lab4_20213704.Service.ServiceRetrofit;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaFragment() {
        // Required empty public constructor
    }


    public static ListaFragment newInstance(String param1, String param2) {
        ListaFragment fragment = new ListaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_lista, container, false);
        Button botonBuscar = view.findViewById(R.id.boton_buscar_ligas);
        EditText pais = view.findViewById(R.id.pais);
        botonBuscar.setOnClickListener(view1 -> {
            if(pais.getText().toString().isEmpty()){
                buscarSinPais(view);
            }else{
                buscarConPais(view,pais.getText().toString());
            }
        });
        return view;
    }


    //Llena datos
    public void buscarSinPais(View view){
        ServiceRetrofit service = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServiceRetrofit.class);
        service.listarLigas().enqueue(new Callback<LigaBusqueda>() {
            @Override
            public void onResponse(Call<LigaBusqueda> call, Response<LigaBusqueda> response) {
                if(response.isSuccessful()){
                    LigaBusqueda ligasLista = response.body();
                    ListaLigasAdapter adapter = new ListaLigasAdapter();
                    adapter.setContext(getContext());
                    adapter.setLista(ligasLista.getLigas());
                    RecyclerView recyclerView = view.findViewById(R.id.recyclerLista);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void onFailure(Call<LigaBusqueda> call, Throwable t) {
                //No hace nada
            }

        });
    }
    public void buscarConPais(View view,String pais){
    }

}