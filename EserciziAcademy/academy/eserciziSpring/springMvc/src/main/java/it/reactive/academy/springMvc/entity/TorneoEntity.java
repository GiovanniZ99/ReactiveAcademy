package it.reactive.academy.springMvc.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "torneo")
public class TorneoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTorneo;

    @Column(name = "nome_torneo")
    private String nomeTorneo;

    @ManyToMany
    @JoinTable(name = "squadra_torneo", joinColumns = @JoinColumn(name = "id_torneo"), inverseJoinColumns = @JoinColumn(name = "id_squadra"))
    private Set<SquadraEntity> squadre;

    public TorneoEntity(){}

    public TorneoEntity(Integer idTorneo, String nomeTorneo, Set<SquadraEntity> squadre) {
        this.idTorneo = idTorneo;
        this.nomeTorneo = nomeTorneo;
        this.squadre = squadre;
    }

    public Integer getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Integer idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public Set<SquadraEntity> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<SquadraEntity> squadre) {
        this.squadre = squadre;
    }
}
