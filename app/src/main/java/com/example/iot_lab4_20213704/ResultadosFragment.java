package com.example.iot_lab4_20213704;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.iot_lab4_20213704.Adapter.ListaPosicionesAdapter;
import com.example.iot_lab4_20213704.Beans.PositionBusqueda;
import com.example.iot_lab4_20213704.Beans.ResultadoBusqueda;
import com.example.iot_lab4_20213704.Service.ServiceRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultadosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultadosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultadosFragment newInstance(String param1, String param2) {
        ResultadosFragment fragment = new ResultadosFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resultados, container, false);
    }


    public void buscarResultados(View view , String idLiga,String temporada,String ronda){

        ServiceRetrofit service = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServiceRetrofit.class);
        service.getEventos(idLiga, temporada, ronda).enqueue(new Callback<ResultadoBusqueda>() {
            @Override
            public void onResponse(Call<ResultadoBusqueda> call, Response<ResultadoBusqueda> response) {
                if(response.isSuccessful()){
                    ResultadoBusqueda resultadosBusqueda = response.body();
                    if(resultadosBusqueda.getEvents() != null){
                        ListaPosicionesAdapter adapter = new ListaPosicionesAdapter();
                        adapter.setContext(getContext());
                        adapter.setLista(posicionesLista.getTable());
                        RecyclerView recyclerView = view.findViewById(R.id.recyclerPartidos);
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