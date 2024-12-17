package it.reactive.academy.springMvc.resource;

public class Tifoseria {

  private Integer idTifoseria;
  private String nomeTifoseria;

    public Tifoseria(){}

    public Tifoseria(Integer idTifoseria, String nomeTifoseria, Squadra squadra) {
        super();
        this.idTifoseria = idTifoseria;
        this.nomeTifoseria = nomeTifoseria;
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
}
