package it.reactive.academy.springMvc.entity;

import it.reactive.academy.springMvc.model.TrasferimentiModel;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
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
}
