package com.example.iot_lab4_20213704.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20213704.Beans.Position;
import com.example.iot_lab4_20213704.Beans.Resultado;
import com.example.iot_lab4_20213704.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ListaResultadosAdapter extends RecyclerView.Adapter<ListaResultadosAdapter.ResultadoViewHolder>  {


    private List<Resultado> lista;
    private Context context;
    public List<Resultado> getLista() {
        return lista;
    }
    public void setLista(List<Resultado> lista) {
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

    public class ResultadoViewHolder extends RecyclerView.ViewHolder {
        Resultado elemento;
        public ResultadoViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    @Override
    @NonNull
    public ListaResultadosAdapter.ResultadoViewHolder onCreateViewHolder (@NonNull ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.irv_lista_resultados,parent,false);
        return new ListaResultadosAdapter.ResultadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaResultadosAdapter.ResultadoViewHolder holder , int position){
        Resultado p = lista.get(position);
        holder.elemento = p;
        ((TextView ) holder.itemView.findViewById(R.id.nombre)).setText(p.getStrEvent());
        ((TextView)holder.itemView.findViewById(R.id.ronda)).setText(p.getIntRound());
        ( (TextView) holder.itemView.findViewById(R.id.equipoLocal)).setText(p.getStrHomeTeam());
        ( (TextView) holder.itemView.findViewById(R.id.equipoVisitante)).setText(p.getStrAwayTeam());
        ( (TextView) holder.itemView.findViewById(R.id.resultado)).setText(p.getStrHomeTeam() + " " + p.getIntHomeScore() + " - " + p.getStrAwayTeam() + " " + p.getIntAwayScore());
        ( (TextView) holder.itemView.findViewById(R.id.fechaEncuentro)).setText(p.getDateEvent());
        String ola ="";if(p.getIntSpectators()==null){ola="0";}else{ola=p.getIntSpectators();}
        ( (TextView) holder.itemView.findViewById(R.id.cantidadEspectadores)).setText(ola);
        Picasso.get().load(p.getStrLeagueBadge()).into((ImageView) holder.itemView.findViewById(R.id.imagen));
    }
}
