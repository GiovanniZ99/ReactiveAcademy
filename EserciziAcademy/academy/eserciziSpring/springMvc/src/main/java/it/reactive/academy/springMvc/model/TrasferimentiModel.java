package it.reactive.academy.springMvc.model;

public class TrasferimentiModel {
    private Integer anno;
    private String nomeSquadraStorica;

    public TrasferimentiModel(Integer anno, String nomeSquadraStorica) {
        this.anno = anno;
        this.nomeSquadraStorica = nomeSquadraStorica;
    }

    public TrasferimentiModel() {
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public String getNomeSquadraStorica() {
        return nomeSquadraStorica;
    }

    public void setNomeSquadraStorica(String nomeSquadraStorica) {
        this.nomeSquadraStorica = nomeSquadraStorica;
    }
}
