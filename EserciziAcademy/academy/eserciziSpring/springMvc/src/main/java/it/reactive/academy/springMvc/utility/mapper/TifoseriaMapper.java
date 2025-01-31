package it.reactive.academy.springMvc.utility.mapper;

import it.reactive.academy.springMvc.dto.TifoseriaDTO;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.entity.TifoseriaEntity;
import it.reactive.academy.springMvc.resource.Tifoseria;

public class TifoseriaMapper {

    private TifoseriaMapper() {
    }

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
    public static TifoseriaDTOExtended tifoseriaEntityToDtoExtended(TifoseriaEntity tifoseriaEntity){
        TifoseriaDTOExtended tifoseriaDTOExtended = new TifoseriaDTOExtended();
        if(tifoseriaEntity.getIdTifoseria() != null) {
            tifoseriaDTOExtended.setIdTifoseria(tifoseriaEntity.getIdTifoseria());
        }
        tifoseriaDTOExtended.setNomeTifoseria(tifoseriaEntity.getNomeTifoseria());
        if(tifoseriaEntity.getSquadra()!=null) {
            tifoseriaDTOExtended.setSquadra(SquadraMapper.squadraEntityToDtoExtendended(tifoseriaEntity.getSquadra()));
        }
        return tifoseriaDTOExtended;
    }
    public static TifoseriaEntity tifoseriaDtoExtendedToEntity(TifoseriaDTOExtended tifoseriaDTOExtended){
        TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();
        tifoseriaEntity.setIdTifoseria(tifoseriaDTOExtended.getIdTifoseria());
        tifoseriaEntity.setNomeTifoseria(tifoseriaDTOExtended.getNomeTifoseria());
        if(tifoseriaDTOExtended.getSquadra()!= null) {
            tifoseriaEntity.setSquadra(SquadraMapper.squadraDtoExtendedToEntity(tifoseriaDTOExtended.getSquadra()));
        }
        return tifoseriaEntity;
    }
}
