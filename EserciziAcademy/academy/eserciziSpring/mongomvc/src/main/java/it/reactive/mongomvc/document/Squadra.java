package it.reactive.mongomvc.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "squadre")
public class Squadra {
    @Id
    private String id;

    private String nome;

    private String coloriSociali;

    private Set<Giocatore> giocatori;

    private Tifoseria tifoserie;


    public Squadra(String id, String nome, String coloriSociali, Set<Giocatore> giocatori, Tifoseria tifoserie) {
        this.id = id;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
        this.tifoserie = tifoserie;
    }

    public Squadra() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setGiocatori(Set<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }

    public Set<Giocatore> getGiocatori() {
        return giocatori;
    }

    public Tifoseria getTifoserie() {
        return tifoserie;
    }

    public void setTifoserie(Tifoseria tifoserie) {
        this.tifoserie = tifoserie;
    }

}
