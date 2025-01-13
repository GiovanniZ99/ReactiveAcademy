package it.reactive.academy.springMvc.entity;

import javax.persistence.*;

@Entity
@Table(name = "giocatore")
public class GiocatoreEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGiocatore;

    @Column(name = "nome_cognome")
    private String nomeCognome;

    @Column(name = "numero_ammonizioni")
    private Integer numeroAmmonizioni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_squadra")
    private SquadraEntity squadra;

    public GiocatoreEntity(){}

    public GiocatoreEntity(Integer idGiocatore, String nomeCognome, Integer numeroAmmonizioni, SquadraEntity squadra) {
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

    public SquadraEntity getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraEntity squadra) {
        this.squadra = squadra;
    }
}
