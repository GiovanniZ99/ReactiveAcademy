package it.reactive.springbatch.processor;

import it.reactive.springbatch.entity.GiocatoreEntity;
import it.reactive.springbatch.model.GiocatoreCSV;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class GiocatoreProcessor implements ItemProcessor<GiocatoreEntity, GiocatoreCSV> {
    @Override
    public GiocatoreCSV process(GiocatoreEntity item) throws Exception {
            GiocatoreCSV csv = new GiocatoreCSV();
            csv.setIdGiocatore(item.getIdGiocatore());
            csv.setNomeCognome(item.getNomeCognome());
            csv.setNumeroAmmonizioni(item.getNumeroAmmonizioni());
            if (item.getSquadra() != null) {
                csv.setNomeSquadra(item.getSquadra().getNome());
                csv.setColoriSociali(item.getSquadra().getColoriSociali());
                if (item.getSquadra().getTifoseria() != null) {
                    csv.setNomeTifoseria(item.getSquadra().getTifoseria().getNomeTifoseria());
                }
            }
            return csv;
    }
}