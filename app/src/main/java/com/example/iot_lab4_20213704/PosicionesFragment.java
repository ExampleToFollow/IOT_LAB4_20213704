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
import android.widget.Toast;

import com.example.iot_lab4_20213704.Adapter.ListaLigasAdapter;
import com.example.iot_lab4_20213704.Adapter.ListaPosicionesAdapter;
import com.example.iot_lab4_20213704.Beans.LigaBusqueda;
import com.example.iot_lab4_20213704.Beans.PositionBusqueda;
import com.example.iot_lab4_20213704.Service.ServiceRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosicionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosicionesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PosicionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PosicionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PosicionesFragment newInstance(String param1, String param2) {
        PosicionesFragment fragment = new PosicionesFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_posiciones, container, false);
        Button b = view.findViewById(R.id.boton_buscar_posiciones);
        b.setOnClickListener(view1 -> {
            EditText idLiga = view.findViewById(R.id.idLiga);
            EditText temporada = view.findViewById(R.id.temporada);
            if(idLiga.getText().toString().trim() != null  && temporada.getText().toString().trim() != null   ){
                buscar(view,idLiga.getText().toString() ,temporada.getText().toString());
            }else{
                Toast.makeText(getContext(), "Debes ingresar valores", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    public void buscar(View view   , String idLiga,String temporada){
        ServiceRetrofit service = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServiceRetrofit.class);
        service.listarPosiciones(idLiga, temporada).enqueue(new Callback<PositionBusqueda>() {
            @Override
            public void onResponse(Call<PositionBusqueda> call, Response<PositionBusqueda> response) {
                if(response.isSuccessful()){
                    PositionBusqueda posicionesLista = response.body();
                    if(posicionesLista.getTable() != null){
                        ListaPosicionesAdapter adapter = new ListaPosicionesAdapter();
                        adapter.setContext(getContext());
                        adapter.setLista(posicionesLista.getTable());
                        RecyclerView recyclerView = view.findViewById(R.id.recyclerPosiciones);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }else{
                        Toast.makeText(getContext(), "No se encontraron ligas", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PositionBusqueda> call, Throwable t) {
                //No hace nada
                Toast.makeText(getContext(), "No se encontraron ligas", Toast.LENGTH_SHORT).show();
            }

        });
    }



}