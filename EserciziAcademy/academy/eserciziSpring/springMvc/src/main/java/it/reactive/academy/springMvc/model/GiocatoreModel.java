package it.reactive.academy.springMvc.model;

import java.util.Set;

public class GiocatoreModel {
    private Integer idGiocatore;
    private String nomeCognome;
    private Integer numeroAmmonizioni;
    private SquadraModel squadra;
    private Set<TrasferimentiModel> trasferimenti;

    public GiocatoreModel(Integer idGiocatore, String nomeCognome, Integer numeroAmmonizioni,
                          SquadraModel squadra, Set<TrasferimentiModel> trasferimenti) {
        this.idGiocatore = idGiocatore;
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
        this.squadra = squadra;
        this.trasferimenti = trasferimenti;
    }

    public GiocatoreModel() {
    }

    public void setIdGiocatore(Integer idGiocatore) {
        this.idGiocatore = idGiocatore;
    }

    public Integer getIdGiocatore() {
        return idGiocatore;
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

    public SquadraModel getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraModel squadra) {
        this.squadra = squadra;
    }

    public Set<TrasferimentiModel> getTrasferimenti() {
        return trasferimenti;
    }

    public void setTrasferimenti(Set<TrasferimentiModel> trasferimenti) {
        this.trasferimenti = trasferimenti;
    }
}
