package it.reactive.springbatch.utility.fieldsetmapper;

import it.reactive.springbatch.entity.GiocatoreEntity;
import it.reactive.springbatch.entity.SquadraEntity;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class GiocatoreFieldSetMapper implements FieldSetMapper<GiocatoreEntity> {
    @Override
    @NonNull
    public GiocatoreEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        GiocatoreEntity giocatore = new GiocatoreEntity();
        giocatore.setNomeCognome(fieldSet.readString(1) + fieldSet.readString(2).trim());

        SquadraEntity squadraGiocatore = new SquadraEntity();
        squadraGiocatore.setNome(fieldSet.readString(3).trim());
        giocatore.setSquadra(squadraGiocatore);

        return giocatore;
    }
}
