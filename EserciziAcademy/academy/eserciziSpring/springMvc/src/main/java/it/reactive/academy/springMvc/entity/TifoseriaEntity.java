package it.reactive.academy.springMvc.entity;

import javax.persistence.*;

@Entity
@Table(name = "tifoseria")
public class TifoseriaEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTifoseria;

    @Column(name = "nome_tifoseria")
    private String nomeTifoseria;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_squadra")
    private SquadraEntity squadra;


    public TifoseriaEntity(Integer idTifoseria, String nomeTifoseria, SquadraEntity squadra) {
        this.idTifoseria = idTifoseria;
        this.nomeTifoseria = nomeTifoseria;
        this.squadra = squadra;
    }

    public TifoseriaEntity() {
    }

    public Integer getIdTifoseria() {
        return idTifoseria;
    }

    public void setIdTifoseria(Integer idTifoseria) {
        this.idTifoseria = idTifoseria;
    }

    public String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }

    public SquadraEntity getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraEntity squadra) {
        this.squadra = squadra;
    }
}
