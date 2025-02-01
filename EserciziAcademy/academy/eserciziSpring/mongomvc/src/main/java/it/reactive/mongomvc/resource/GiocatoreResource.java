package it.reactive.mongomvc.resource;

import java.util.Set;

public class GiocatoreResource {

   private Integer idGiocatore;
   private String nomeCognome;
   private Integer numeroAmmonizioni;
   private Set<TrasferimentiResource> trasferimentiResource;

    public GiocatoreResource(){}

    public GiocatoreResource(Integer idGiocatore, String nomeCognome, Integer numeroAmmonizioni) {
        super();
        this.idGiocatore = idGiocatore;
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
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

    public Set<TrasferimentiResource> getTrasferimenti() {
        return trasferimentiResource;
    }

    public void setTrasferimenti(Set<TrasferimentiResource> trasferimentiResource) {
        this.trasferimentiResource = trasferimentiResource;
    }
}
