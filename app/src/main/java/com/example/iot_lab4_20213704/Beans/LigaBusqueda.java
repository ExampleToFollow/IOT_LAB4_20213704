package com.example.iot_lab4_20213704.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LigaBusqueda implements Serializable {
    private ArrayList<Liga> leagues ;
    private ArrayList<Liga> countries ;

    //Geter y setters
    public ArrayList<Liga> getLigas() {
        return leagues;
    }

    public void setLigas(ArrayList<Liga> ligas) {
        this.leagues = ligas;
    }

    public ArrayList<Liga> getLeagues() {
        return leagues;
    }

    public void setLeagues(ArrayList<Liga> leagues) {
        this.leagues = leagues;
    }

    public ArrayList<Liga> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Liga> countries) {
        this.countries = countries;
    }
}
