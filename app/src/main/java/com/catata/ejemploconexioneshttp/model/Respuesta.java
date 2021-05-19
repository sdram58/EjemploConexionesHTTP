package com.catata.ejemploconexioneshttp.model;

import java.util.List;

public class Respuesta {
    String STATUS;
    String ERROR;
    int COUNT;
    List<Comunidad> DATA;

    public Respuesta(String STATUS, String ERROR, int COUNT, List<Comunidad> DATA) {
        this.STATUS = STATUS;
        this.ERROR = ERROR;
        this.COUNT = COUNT;
        this.DATA = DATA;
    }

    public Respuesta() {
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getERROR() {
        return ERROR;
    }

    public void setERROR(String ERROR) {
        this.ERROR = ERROR;
    }

    public int getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(int COUNT) {
        this.COUNT = COUNT;
    }

    public List<Comunidad> getDATA() {
        return DATA;
    }

    public void setDATA(List<Comunidad> DATA) {
        this.DATA = DATA;
    }
}
