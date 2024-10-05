package com.example.iot_lab4_20213704.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LigaBusqueda implements Serializable {
    private ArrayList<Liga> leagues ;

    //Geter y setters
    public ArrayList<Liga> getLigas() {
        return leagues;
    }

    public void setLigas(ArrayList<Liga> ligas) {
        this.leagues = ligas;
    }


}
