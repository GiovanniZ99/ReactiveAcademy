package it.reactive.academy.springMvc.entity;

import javax.persistence.*;

@Entity
@Table(name = "squadra")
public class SquadraEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSquadra;

    private String nome;

    @Column(name = "colori_sociali")
    private String coloriSociali;

    public SquadraEntity(Integer idSquadra, String nome, String coloriSociali) {
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
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

}
