package it.reactive.academy.springMvc.dto.extended;

import java.util.Set;

public class GiocatoreDTOExtended {

    private Integer idGiocatore;
    private String nomeCognome;
    private Integer numeroAmmonizioni;
    private SquadraDTOExtended squadra;
    private Set<TrasferimentiDTOExtended> trasferimenti;

    public GiocatoreDTOExtended(){}

    public GiocatoreDTOExtended(Integer idGiocatore, String nomeCognome, Integer numeroAmmonizioni,
                                SquadraDTOExtended squadra, Set<TrasferimentiDTOExtended> trasferimenti) {
        this.idGiocatore = idGiocatore;
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
        this.squadra = squadra;
        this.trasferimenti = trasferimenti;
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

    public SquadraDTOExtended getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraDTOExtended squadra) {
        this.squadra = squadra;
    }

    public Set<TrasferimentiDTOExtended> getTrasferimenti() {
        return trasferimenti;
    }

    public void setTrasferimenti(Set<TrasferimentiDTOExtended> trasferimenti) {
        this.trasferimenti = trasferimenti;
    }
}
