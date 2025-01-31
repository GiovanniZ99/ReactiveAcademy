package it.reactive.springbatch.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
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

    @OneToMany(mappedBy = "torneo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TorneoEntity that = (TorneoEntity) o;
        return Objects.equals(idTorneo, that.idTorneo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTorneo);
    }
}
