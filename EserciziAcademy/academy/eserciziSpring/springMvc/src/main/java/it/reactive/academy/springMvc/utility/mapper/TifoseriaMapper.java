package it.reactive.academy.springMvc.utility.mapper;

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

    public static TifoseriaDTOExtended tifoseriaDTOToDTOExtended(TifoseriaDTO tifoseriaDTO){
        TifoseriaDTOExtended tifoseriaDTOExtended = new TifoseriaDTOExtended();
        tifoseriaDTOExtended.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        return tifoseriaDTOExtended;
    }
    public static TifoseriaDTOExtended tifoseriaModelToDtoExtended(TifoseriaModel tifoseriaModel){
        TifoseriaDTOExtended tifoseriaDTOExtended = new TifoseriaDTOExtended();
        if(tifoseriaModel.getIdTifoseria() != null) {
            tifoseriaDTOExtended.setIdTifoseria(tifoseriaModel.getIdTifoseria());
        }
        tifoseriaDTOExtended.setNomeTifoseria(tifoseriaModel.getNomeTifoseria());
        if(tifoseriaModel.getSquadra()!=null) {
            tifoseriaDTOExtended.setSquadra(SquadraMapper.squadraModelToDtoExtendended(tifoseriaModel.getSquadra()));
        }
        return tifoseriaDTOExtended;
    }
    public static TifoseriaModel tifoseriaDtoExtendedToModel(TifoseriaDTOExtended tifoseriaDTOExtended){
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setIdTifoseria(tifoseriaDTOExtended.getIdTifoseria());
        tifoseriaModel.setNomeTifoseria(tifoseriaDTOExtended.getNomeTifoseria());
        if(tifoseriaDTOExtended.getSquadra()!= null) {
            tifoseriaModel.setSquadra(SquadraMapper.squadraDtoExtendedToModel(tifoseriaDTOExtended.getSquadra()));
        }
        return tifoseriaModel;
    }
}
