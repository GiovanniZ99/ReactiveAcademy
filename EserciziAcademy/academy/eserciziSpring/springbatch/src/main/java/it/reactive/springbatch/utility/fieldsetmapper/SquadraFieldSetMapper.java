package it.reactive.springbatch.utility.fieldsetmapper;

import it.reactive.springbatch.entity.SquadraEntity;
import it.reactive.springbatch.entity.TifoseriaEntity;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class SquadraFieldSetMapper implements FieldSetMapper<SquadraEntity> {

    @Override
    @NonNull
    public SquadraEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        SquadraEntity squadra = new SquadraEntity();
        squadra.setNome(fieldSet.readString(1).trim());
        squadra.setColoriSociali(fieldSet.readString(2).trim());
        String nomeTifoseria = fieldSet.readString(3).trim();
        if (nomeTifoseria.isBlank()) {
            squadra.setTifoseria(null);
        } else {
            TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();
            tifoseriaEntity.setNomeTifoseria(nomeTifoseria);
            tifoseriaEntity.setSquadra(squadra);
            squadra.setTifoseria(tifoseriaEntity);
        }
        return squadra;
    }
}
