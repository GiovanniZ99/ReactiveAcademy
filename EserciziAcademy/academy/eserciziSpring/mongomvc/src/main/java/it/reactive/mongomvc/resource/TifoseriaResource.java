package it.reactive.mongomvc.resource;

public class TifoseriaResource {

  private String nomeTifoseria;

    public TifoseriaResource(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }

    public TifoseriaResource(){}

    public String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }
}
