package it.reactive.academy.springMvc.entity;

import javax.persistence.*;

@Entity
@Table(name = "torneo")
public class TorneoEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTorneo;

    @Column(name = "nome_torneo")
    private String nomeTorneo;

    public TorneoEntity(Integer idTorneo, String nomeTorneo) {
        this.idTorneo = idTorneo;
        this.nomeTorneo = nomeTorneo;
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

}
