package com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource;

import com.intesasanpaolo.bear.core.resource.BaseResource;

public class CountryResource extends BaseResource {
    private long id;
    private String name;
    private String language;
    private String continent;

    public CountryResource(long id, String name, String language, String continent) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.continent = continent;
    }

    public CountryResource() {
    }

    public long getIdLong() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
