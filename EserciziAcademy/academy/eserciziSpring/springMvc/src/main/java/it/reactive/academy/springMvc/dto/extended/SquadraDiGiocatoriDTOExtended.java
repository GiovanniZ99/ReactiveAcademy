package it.reactive.academy.springMvc.dto.extended;

import java.util.List;

public class SquadraDiGiocatoriDTOExtended {

    private Integer idSquadra;

    private String nome;

    private String coloriSociali;

    private List<GiocatoreDTOExtended> listaGiocatori;

    private TifoseriaDTOExtended tifoseria;

    public SquadraDiGiocatoriDTOExtended(Integer idSquadra, String nome, String coloriSociali,
                                         List<GiocatoreDTOExtended> listaGiocatori, TifoseriaDTOExtended tifoseria) {
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.listaGiocatori = listaGiocatori;
        this.tifoseria = tifoseria;
    }

    public SquadraDiGiocatoriDTOExtended() {
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

    public List<GiocatoreDTOExtended> getListaGiocatori() {
        return listaGiocatori;
    }

    public void setListaGiocatori(List<GiocatoreDTOExtended> listaGiocatori) {
        this.listaGiocatori = listaGiocatori;
    }

    public TifoseriaDTOExtended getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaDTOExtended tifoseria) {
        this.tifoseria = tifoseria;
    }
}
