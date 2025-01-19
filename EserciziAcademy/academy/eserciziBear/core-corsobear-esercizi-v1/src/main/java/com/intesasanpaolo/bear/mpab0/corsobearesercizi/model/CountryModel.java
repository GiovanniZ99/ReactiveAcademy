package com.intesasanpaolo.bear.mpab0.corsobearesercizi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class CountryModel {

    @Id
    @Column
    private long id;

    @Column
    private String info;

    public CountryModel(long id, String info) {
        this.id = id;
        this.info = info;
    }

    public CountryModel() {
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
