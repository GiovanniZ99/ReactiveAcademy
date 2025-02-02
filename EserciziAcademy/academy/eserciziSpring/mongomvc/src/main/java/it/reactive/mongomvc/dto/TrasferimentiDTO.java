package it.reactive.mongomvc.dto;

public class TrasferimentiDTO {
    private Integer anno;
    private String nomeSquadraStorica;

    public TrasferimentiDTO(Integer anno, String nomeSquadraStorica) {
        this.anno = anno;
        this.nomeSquadraStorica = nomeSquadraStorica;
    }

    public TrasferimentiDTO() {
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

