package it.reactive.mongomvc.document;

public class Tifoseria {
    private String nomeTifoseria;

    public Tifoseria(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }

    public Tifoseria() {
    }

    public String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }
}
