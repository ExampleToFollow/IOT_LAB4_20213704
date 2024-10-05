package com.example.iot_lab4_20213704.Beans;

import java.io.Serializable;
import java.util.List;

public class LigaBusqueda implements Serializable {
    private List<Liga> ligas ;
    //Geter y setters
    public List<Liga> getLigas() {
        return ligas;
    }

    public void setLigas(List<Liga> ligas) {
        this.ligas = ligas;
    }


}
