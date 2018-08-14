package com.example.Entities.dto;

import com.example.Entities.Statistics;

public class ClubsEstadisticasDTO {

    private String nombre_equipo;
    Statistics estadisticas;

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    public Statistics getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(Statistics estadisticas) {
        this.estadisticas = estadisticas;
    }
}
