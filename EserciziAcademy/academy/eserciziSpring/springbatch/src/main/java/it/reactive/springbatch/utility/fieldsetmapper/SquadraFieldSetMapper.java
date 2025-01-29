package it.reactive.springbatch.utility.fieldsetmapper;

import it.reactive.springbatch.entity.SquadraEntity;
import it.reactive.springbatch.entity.TifoseriaEntity;
import it.reactive.springbatch.repository.SquadraRepository;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.util.Optional;

@Component
public class SquadraFieldSetMapper implements FieldSetMapper<SquadraEntity> {

    private final SquadraRepository squadraRepository;

    public SquadraFieldSetMapper(SquadraRepository squadraRepository) {
        this.squadraRepository = squadraRepository;
    }

    @Override
    @NonNull
    public SquadraEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        SquadraEntity squadra = new SquadraEntity();
        squadra.setNome(fieldSet.readString(1).trim());
        Optional<SquadraEntity> squadraOpt = squadraRepository.findByNome(squadra.getNome());
        if(squadraOpt.isPresent()){
         return null;
        }
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
