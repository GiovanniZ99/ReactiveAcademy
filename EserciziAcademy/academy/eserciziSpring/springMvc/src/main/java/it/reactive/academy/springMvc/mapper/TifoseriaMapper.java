package it.reactive.academy.springMvc.mapper;

import it.reactive.academy.springMvc.dto.TifoseriaDTO;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.model.TifoseriaModel;
import it.reactive.academy.springMvc.resource.Tifoseria;

public class TifoseriaMapper {
    public static Tifoseria tifoseriaDtoExtendedToResource(TifoseriaDTOExtended tifoseriaDTOExtended){
        Tifoseria tifoseria = new Tifoseria();
        tifoseria.setIdTifoseria(tifoseriaDTOExtended.getIdTifoseria());
        tifoseria.setNomeTifoseria(tifoseriaDTOExtended.getNomeTifoseria());
        return tifoseria;
    }
    public static TifoseriaDTOExtended tifoseriaToDtoExtended(Tifoseria tifoseria){
        TifoseriaDTOExtended tifoseriaDTOExtended = new TifoseriaDTOExtended();
        tifoseriaDTOExtended.setIdTifoseria(tifoseria.getIdTifoseria());
        tifoseriaDTOExtended.setNomeTifoseria(tifoseria.getNomeTifoseria());
        return tifoseriaDTOExtended;
    }
    public static TifoseriaDTOExtended tifoseriaDTOToDTOExtended(TifoseriaDTO tifoseriaDTO){
        TifoseriaDTOExtended tifoseriaDTOExtended = new TifoseriaDTOExtended();
        tifoseriaDTOExtended.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        return tifoseriaDTOExtended;
    }
    public static TifoseriaModel tifoseriaDtoExtendedToModel(TifoseriaDTOExtended tifoseriaDTOExtended){
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setIdTifoseria(tifoseriaDTOExtended.getIdTifoseria());
        tifoseriaModel.setNomeTifoseria(tifoseriaModel.getNomeTifoseria());
        tifoseriaModel.setSquadra(
                SquadraMapper.squadraDtoExtendedToModel(tifoseriaDTOExtended.getSquadra()));
        return tifoseriaModel;
    }
}
