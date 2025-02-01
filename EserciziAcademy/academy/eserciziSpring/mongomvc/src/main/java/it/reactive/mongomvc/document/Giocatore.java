package it.reactive.mongomvc.document;

public class Giocatore {

    private String nomeCognome;

    private Integer numeroAmmonizioni = 0;

    public Giocatore(){}

    public Giocatore(String nomeCognome, Integer numeroAmmonizioni) {
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
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
}
