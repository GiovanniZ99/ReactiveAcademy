package it.reactive.springbatch.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tifoseria")
@NamedQuery(name = "findByTeam", query = "select t from TifoseriaEntity t where t.squadra.idSquadra = :idSquadra")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TifoseriaEntity that = (TifoseriaEntity) o;
        return Objects.equals(idTifoseria, that.idTifoseria) &&
                Objects.equals(nomeTifoseria, that.nomeTifoseria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTifoseria, nomeTifoseria);
    }
}
