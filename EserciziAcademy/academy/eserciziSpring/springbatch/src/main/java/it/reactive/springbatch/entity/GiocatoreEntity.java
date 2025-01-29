package it.reactive.springbatch.entity;

import it.reactive.springbatch.model.TrasferimentiModel;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name = "Giocatore.findByName",
        query = "select g from GiocatoreEntity g where g.nomeCognome = :input")
@NamedQuery(name = "Giocatore.findByTeam",
        query = "select g from GiocatoreEntity g where g.squadra.idSquadra = :idSquadra")
@Table(name = "giocatore")
public class GiocatoreEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGiocatore;

    @Column(name = "nome_cognome")
    private String nomeCognome;

    @Column(name = "numero_ammonizioni")
    @ColumnDefault("0")
    private Integer numeroAmmonizioni = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_squadra")
    private SquadraEntity squadra;

    @Transient
    private Set<TrasferimentiModel> trasferimenti;

    public GiocatoreEntity(Integer idGiocatore, String nomeCognome, Integer numeroAmmonizioni,
                           SquadraEntity squadra, Set<TrasferimentiModel> trasferimenti) {
        this.idGiocatore = idGiocatore;
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
        this.squadra = squadra;
        this.trasferimenti = trasferimenti;
    }

    public GiocatoreEntity() {
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

    public SquadraEntity getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraEntity squadra) {
        this.squadra = squadra;
    }

    public Set<TrasferimentiModel> getTrasferimenti() {
        return trasferimenti;
    }

    public void setTrasferimenti(Set<TrasferimentiModel> trasferimenti) {
        this.trasferimenti = trasferimenti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiocatoreEntity giocatore = (GiocatoreEntity) o;
        return Objects.equals(idGiocatore, giocatore.idGiocatore) && Objects.equals(nomeCognome, giocatore.nomeCognome) && Objects.equals(numeroAmmonizioni, giocatore.numeroAmmonizioni) && Objects.equals(squadra, giocatore.squadra) && Objects.equals(trasferimenti, giocatore.trasferimenti);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGiocatore, nomeCognome, numeroAmmonizioni, squadra, trasferimenti);
    }
}
