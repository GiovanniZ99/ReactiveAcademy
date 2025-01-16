package com.intesasanpaolo.bear.mpab0.corsobearesercizi.model;

public class CountryModel {
    private long id;
    private String info;

    public CountryModel(long id, String info) {
        this.id = id;
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
