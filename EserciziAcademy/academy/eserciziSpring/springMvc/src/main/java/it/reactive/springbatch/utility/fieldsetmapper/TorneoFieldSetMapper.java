package it.reactive.springbatch.utility.fieldsetmapper;

import it.reactive.springbatch.entity.TorneoEntity;
import it.reactive.springbatch.repository.TorneoRepository;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.util.Optional;

@Component
public class TorneoFieldSetMapper implements FieldSetMapper<TorneoEntity> {
    private final TorneoRepository torneoRepository;

    @Autowired
    public TorneoFieldSetMapper(TorneoRepository torneoRepository) {
        this.torneoRepository = torneoRepository;
    }

    @Override
    @NonNull
    public TorneoEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        TorneoEntity torneo = new TorneoEntity();
        String nomeTorneo= fieldSet.readString(1).trim();
        Optional<TorneoEntity> torneoOpt = torneoRepository.findByNomeTorneo(nomeTorneo);

        if (torneoOpt.isPresent()) {
            torneo = torneoOpt.get();
        } else {
            torneo.setNomeTorneo(nomeTorneo);
        }

        return torneo;
    }
}
