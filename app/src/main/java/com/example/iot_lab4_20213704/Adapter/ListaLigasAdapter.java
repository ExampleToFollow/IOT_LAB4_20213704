package com.example.iot_lab4_20213704.Adapter;

import com.example.iot_lab4_20213704.Beans.Liga;
import com.example.iot_lab4_20213704.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaLigasAdapter  extends RecyclerView.Adapter<ListaLigasAdapter.LigaViewHolder>{
    private List<Liga> lista;
    private Context context;
    public List<Liga> getLista() {
        return lista;
    }
    public void setLista(List<Liga> lista) {
        this.lista = lista;
    }
    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount(){
        return lista.size();
    }

    public class LigaViewHolder extends RecyclerView.ViewHolder {
        Liga elemento;
        public LigaViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    @Override
    @NonNull
    public LigaViewHolder onCreateViewHolder (@NonNull ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.irv_lista_ligas,parent,false);
        return new LigaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LigaViewHolder holder , int position){
        Liga liga = lista.get(position);
        holder.elemento = liga;
        //Aqui va toda la logica de la vista
        String nameLiga  = liga.getStrLeague();
        String idLiga = liga.getIdLeague();
        String nameSport = liga.getStrSport();
        String nameLigaAlternative  = liga.getStrLeagueAlternate();
        //Seteamos en la vista;
        TextView idLigaText = holder.itemView.findViewById(R.id.idLiga);
        TextView nombreLigaText = holder.itemView.findViewById(R.id.nombreLiga);
        TextView deporteLigaText = holder.itemView.findViewById(R.id.deporteLiga);
        TextView nombreAlternativoText = holder.itemView.findViewById(R.id.nombreAlternativo);

        idLigaText.setText(idLiga);
        nombreLigaText.setText(nameLiga);
        deporteLigaText.setText(nameSport);
        nombreAlternativoText.setText(nameLigaAlternative);

    }


}
