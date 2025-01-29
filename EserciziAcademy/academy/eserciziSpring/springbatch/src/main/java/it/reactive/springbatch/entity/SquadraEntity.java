package it.reactive.springbatch.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "squadra")
public class SquadraEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSquadra;

    @Column
    private String nome;

    @Column(name = "colori_sociali")
    private String coloriSociali;

    @OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GiocatoreEntity> giocatori;

    @OneToOne(mappedBy = "squadra", cascade = CascadeType.ALL, orphanRemoval = true)
    private TifoseriaEntity tifoseria;

    public SquadraEntity(Integer idSquadra, String nome, String coloriSociali, Set<GiocatoreEntity> giocatori, TifoseriaEntity tifoseria) {
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
        this.tifoseria = tifoseria;
    }

    public SquadraEntity() {
    }

    public Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(Integer idSquadra) {
        this.idSquadra = idSquadra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }

    public Set<GiocatoreEntity> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoreEntity> giocatori) {
        this.giocatori = giocatori;
    }

    public TifoseriaEntity getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaEntity tifoseria) {
        this.tifoseria = tifoseria;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquadraEntity that = (SquadraEntity) o;
        return Objects.equals(idSquadra, that.idSquadra) && Objects.equals(nome, that.nome) && Objects.equals(coloriSociali, that.coloriSociali) && Objects.equals(giocatori, that.giocatori) && Objects.equals(tifoseria, that.tifoseria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSquadra, nome, coloriSociali, giocatori, tifoseria);

    }
}
