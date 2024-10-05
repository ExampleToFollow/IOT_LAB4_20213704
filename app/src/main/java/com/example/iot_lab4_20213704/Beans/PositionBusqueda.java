package com.example.iot_lab4_20213704.Beans;

import java.io.Serializable;
import java.util.ArrayList;

public class PositionBusqueda implements Serializable {
    private ArrayList<Position> table;

    public ArrayList<Position> getTable() {
        return table;
    }

    public void setTable(ArrayList<Position> table) {
        this.table = table;
    }
}
