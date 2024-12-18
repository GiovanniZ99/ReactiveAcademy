package it.reactive.academy.springMvc.dto.extended;

public class TrasferimentiDTOExtended {
    private Integer anno;
    private String nomeSquadraStorica;

    public TrasferimentiDTOExtended(Integer anno, String nomeSquadraStorica) {
        this.anno = anno;
        this.nomeSquadraStorica = nomeSquadraStorica;
    }

    public TrasferimentiDTOExtended() {
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