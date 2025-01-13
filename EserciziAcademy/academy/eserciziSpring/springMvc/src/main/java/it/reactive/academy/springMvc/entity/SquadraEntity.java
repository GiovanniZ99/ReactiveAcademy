package it.reactive.academy.springMvc.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "squadra")
public class SquadraEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSquadra;
    @Column(name = "nome_cognome")
    private String nome;
    @Column(name = "colori_sociali")
    private String coloriSociali;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "squadra")
    private Set<GiocatoreEntity> giocatori;

    public SquadraEntity(){}

    public SquadraEntity(Integer idSquadra, String nome, String coloriSociali, Set<GiocatoreEntity> giocatori) {
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
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
}
