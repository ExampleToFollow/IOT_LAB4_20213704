package com.example.iot_lab4_20213704;

import static android.content.Context.SENSOR_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iot_lab4_20213704.Adapter.ListaPosicionesAdapter;
import com.example.iot_lab4_20213704.Adapter.ListaResultadosAdapter;
import com.example.iot_lab4_20213704.Beans.PositionBusqueda;
import com.example.iot_lab4_20213704.Beans.Resultado;
import com.example.iot_lab4_20213704.Beans.ResultadoBusqueda;
import com.example.iot_lab4_20213704.Service.ServiceRetrofit;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.content.Context;
import android.hardware.Sensor;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadosFragment extends Fragment implements SensorEventListener {

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
    String active = "no";
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
        mSensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.fragment_resultados, container, false);
        Button b = view.findViewById(R.id.buscar);
        b.setOnClickListener(view1->{
            EditText idLiga = view.findViewById(R.id.idLiga);
            EditText season =  view.findViewById(R.id.season);
            EditText ronda =  view.findViewById(R.id.ronda);

            if(!idLiga.getText().toString().trim().isEmpty() && !season.getText().toString().trim().isEmpty() && !ronda.getText().toString().trim().isEmpty()){
                buscarResultados(view,idLiga.getText().toString(),season.getText().toString(),ronda.getText().toString());
                //Activamos el sensor
                mSensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            }else{
                Toast.makeText(getContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
        supreme = view;
        return view;
    }

    public void buscarResultados(View view , String idLiga,String temporada,String ronda){

        ServiceRetrofit service = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServiceRetrofit.class);
        service.getEventos(idLiga, ronda, temporada).enqueue(new Callback<ResultadoBusqueda>() {
            @Override
            public void onResponse(Call<ResultadoBusqueda> call, Response<ResultadoBusqueda> response) {
                if(response.isSuccessful()){
                    ResultadoBusqueda resultadosBusqueda = response.body();
                    if(resultadosBusqueda.getEvents() != null){
                        ListaResultadosAdapter adapter = new ListaResultadosAdapter();
                        adapter.setContext(getContext());
                        adapter.setLista(resultadosBusqueda.getEvents());
                        RecyclerView recyclerView = view.findViewById(R.id.recyclerPartidos);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }else{
                        Toast.makeText(getContext(), "No se encontraron ligas", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultadoBusqueda> call, Throwable t) {
                //No hace nada
                Toast.makeText(getContext(), "No se encontraron ligas", Toast.LENGTH_SHORT).show();
            }
            //No hace nada

        });
    }

    View supreme = null;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        if(Math.sqrt(Math.pow(sensorEvent.values[0] ,2)  +  Math.pow(sensorEvent.values[1] ,2)  + Math.pow(sensorEvent.values[2] ,2)) >20 ){
            //Mostramos el dialog
            if(active.equals("no")){
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Supero el umbral de 20m/s2")
                        .setMessage("¿Eliminará sus resultados?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            //Eliminamos los datos
                            ListaResultadosAdapter adapter = new ListaResultadosAdapter();
                            adapter.setContext(getContext());
                            adapter.setLista(new ArrayList<Resultado>());
                            RecyclerView recyclerView = supreme.findViewById(R.id.recyclerPartidos);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            active = "no";
                        })
                        .setNegativeButton("Nou", (dialog, which) -> {
                            active = "no";
                        })
                        .show();
                active = "si";
            }

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}