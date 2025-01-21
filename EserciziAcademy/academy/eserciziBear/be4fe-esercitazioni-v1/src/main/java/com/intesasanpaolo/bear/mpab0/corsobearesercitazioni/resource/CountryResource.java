package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource;

import com.intesasanpaolo.bear.core.resource.BaseResource;

import java.time.Instant;
import java.util.Objects;

public class CountryResource extends BaseResource {
    private Long key;

    private Instant oraAggiornamento;

    private String lingua;

    public CountryResource() {
    }

    public CountryResource(Long key, Instant oraAggiornamento, String lingua) {
        this.key = key;
        this.oraAggiornamento = oraAggiornamento;
        this.lingua = lingua;
    }

    public Instant getOraAggiornamento() {
        return oraAggiornamento;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public void setOraAggiornamento(Instant oraAggiornamento) {
        this.oraAggiornamento = oraAggiornamento;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CountryResource that = (CountryResource) o;
        return Objects.equals(key, that.key) && Objects.equals(oraAggiornamento, that.oraAggiornamento) && Objects.equals(lingua, that.lingua);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, oraAggiornamento, lingua);
    }
}
