package it.reactive.academy.springMvc.model;

import it.reactive.academy.springMvc.resource.Squadra;
import it.reactive.academy.springMvc.resource.Trasferimenti;

import java.util.Set;

public class GiocatoreResource {
  private String nomeCognome;
  private Integer numeroAmmonizioni;
  private Squadra squadra;
  private Set<Trasferimenti> trasferimenti;

    public GiocatoreResource(String nomeCognome, Integer numeroAmmonizioni, Squadra squadra, Set<Trasferimenti> trasferimenti) {
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
        this.squadra = squadra;
        this.trasferimenti = trasferimenti;
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public Integer getNumeroAmmonizioni() {
        return numeroAmmonizioni;
    }

    public void setNumeroAmmonizioni(Integer numeroAmmonizioni) {
        this.numeroAmmonizioni = numeroAmmonizioni;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    public Set<Trasferimenti> getTrasferimenti() {
        return trasferimenti;
    }

    public void setTrasferimenti(Set<Trasferimenti> trasferimenti) {
        this.trasferimenti = trasferimenti;
    }
}
