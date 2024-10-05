package com.example.iot_lab4_20213704.Service;
import com.example.iot_lab4_20213704.Beans.Liga;
import com.example.iot_lab4_20213704.Beans.LigaBusqueda;
import com.example.iot_lab4_20213704.Beans.PositionBusqueda;
import com.example.iot_lab4_20213704.Beans.ResultadoBusqueda;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ServiceRetrofit {
    @GET("/api/v1/json/3/all_leagues.php")
    Call<LigaBusqueda> listarLigas();

    @GET("/api/v1/json/3/search_all_leagues.php")
    Call<LigaBusqueda> listarLigasBuscar(@Query("c") String pais);

    @GET("/api/v1/json/3/lookuptable.php")
    Call<PositionBusqueda> listarPosiciones(@Query("l") String idLiga,
                                            @Query("s") String season);

    @GET("/api/v1/json/3/eventsround.php")
    Call<ResultadoBusqueda> getEventos(@Query("id") String idLiga,
                                          @Query("r") String ronda,
                                          @Query("s") String season);


}
