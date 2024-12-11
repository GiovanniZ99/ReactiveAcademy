package it.reactive.academy.SpringMvcStep1.resource;

import java.util.Set;

public class Giocatore {

    Integer idGiocatore;
    String nomeCognome;
    Integer numeroAmmonizioni;
    Squadra squadra;
    Set<Trasferimenti> trasferimenti;

    public Giocatore(){}

    public Giocatore(Integer idGiocatore, String nomeCognome, Integer numeroAmmonizioni, Squadra squadra) {
        super();
        this.idGiocatore = idGiocatore;
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
        this.squadra = squadra;
    }

    public Integer getIdGiocatore() {
        return idGiocatore;
    }

    public void setIdGiocatore(Integer idGiocatore) {
        this.idGiocatore = idGiocatore;
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
}
