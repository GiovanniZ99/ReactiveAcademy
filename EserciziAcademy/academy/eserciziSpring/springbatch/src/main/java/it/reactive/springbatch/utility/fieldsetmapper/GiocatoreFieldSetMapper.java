package it.reactive.springbatch.utility.fieldsetmapper;

import it.reactive.springbatch.entity.GiocatoreEntity;
import it.reactive.springbatch.entity.SquadraEntity;
import it.reactive.springbatch.repository.SquadraRepository;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class GiocatoreFieldSetMapper implements FieldSetMapper<GiocatoreEntity> {
    private final SquadraRepository squadraRepository;

    @Autowired
    public GiocatoreFieldSetMapper(SquadraRepository squadraRepository) {
        this.squadraRepository = squadraRepository;
    }

    @Override
    @NonNull
    public GiocatoreEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        GiocatoreEntity giocatore = new GiocatoreEntity();
        giocatore.setNomeCognome(fieldSet.readString(1) + fieldSet.readString(2).trim());

        String nomeSquadra = fieldSet.readString(3).trim();

        SquadraEntity squadra = squadraRepository.findByNome(nomeSquadra)
                .orElseGet(() -> {
                    SquadraEntity newSquadra = new SquadraEntity();
                    newSquadra.setNome(nomeSquadra);
                    newSquadra.setColoriSociali("default");
                    return squadraRepository.save(newSquadra);
                });

        giocatore.setSquadra(squadra);

        return giocatore;
    }
}
