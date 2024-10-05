package com.example.iot_lab4_20213704.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20213704.Beans.Liga;
import com.example.iot_lab4_20213704.Beans.Position;
import com.example.iot_lab4_20213704.Beans.PositionBusqueda;
import com.example.iot_lab4_20213704.R;

import java.util.List;

public class ListaPosicionesAdapter extends RecyclerView.Adapter<ListaPosicionesAdapter.PositionViewHolder> {
    private List<Position> lista;
    private Context context;
    public List<Position> getLista() {
        return lista;
    }
    public void setLista(List<Position> lista) {
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

    public class PositionViewHolder extends RecyclerView.ViewHolder {
        Position elemento;
        public PositionViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    @Override
    @NonNull
    public ListaPosicionesAdapter.PositionViewHolder onCreateViewHolder (@NonNull ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.irv_elemento_posicion,parent,false);
        return new ListaPosicionesAdapter.PositionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPosicionesAdapter.PositionViewHolder holder , int position){
        Position p = lista.get(position);
        holder.elemento = p;
        //Aqui va toda la logica de la vista
        TextView nombre =  holder.itemView.findViewById(R.id.nombre);
        TextView ranking =  holder.itemView.findViewById(R.id.ranking);
        ImageView image =  holder.itemView.findViewById(R.id.image);
        TextView victorias_empates_derrotas =  holder.itemView.findViewById(R.id.victorias_empates_derrotas);
        TextView goles_anotados_concedidos_diferencia =  holder.itemView.findViewById(R.id.goles_anotados_concedidos_diferencia);
        nombre.setText(p.getStrTeam());
        ranking.setText(p.getIntRank());
        victorias_empates_derrotas.setText("Victorias : " + p.getIntWin() + ", Empates : " + p.getIntDraw() + ", Derrotas : " + p.getIntLoss());
        goles_anotados_concedidos_diferencia.setText("Goles anotados : " + p.getIntGoalsFor() + ", Goles conceidos : " + p.getIntGoalsAgainst() + ", Diferencia de goles : " + p.getIntGoalDifference());
        Picasso.get().load(p.getStrBadge()).into(image);
    }

}
