package it.reactive.academy.springMvc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "torneo")
public class TorneoEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTorneo;

    @Column(name = "nome_torneo")
    private String nomeTorneo;

    @OneToMany(mappedBy = "torneo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SquadraTorneoEntity> squadraTornei;

    public TorneoEntity(Integer idTorneo, String nomeTorneo, Set<SquadraTorneoEntity> squadraTornei) {
        this.idTorneo = idTorneo;
        this.nomeTorneo = nomeTorneo;
        this.squadraTornei = squadraTornei;
    }

    public TorneoEntity() {
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

    public Set<SquadraTorneoEntity> getSquadraTornei() {
        return squadraTornei;
    }

    public void setSquadraTornei(Set<SquadraTorneoEntity> squadraTornei) {
        this.squadraTornei = squadraTornei;
    }
}
