package it.reactive.springbatch.utility.fieldsetmapper;

import it.reactive.springbatch.entity.TorneoEntity;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class TorneoFieldSetMapper implements FieldSetMapper<TorneoEntity> {
    @Override
    @NonNull
    public TorneoEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        TorneoEntity torneo = new TorneoEntity();
        torneo.setNomeTorneo(fieldSet.readString(1).trim());
        return torneo;
    }
}
