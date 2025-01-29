package it.reactive.springbatch.utility.fieldsetmapper;

import it.reactive.springbatch.entity.SquadraEntity;
import it.reactive.springbatch.entity.SquadraTorneoEntity;
import it.reactive.springbatch.entity.TorneoEntity;
import it.reactive.springbatch.repository.SquadraRepository;
import it.reactive.springbatch.repository.TorneoRepository;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class SquadraTorneoFieldSetMapper implements FieldSetMapper<SquadraTorneoEntity> {
    private final SquadraRepository squadraRepository;

    private final TorneoRepository torneoRepository;

    public SquadraTorneoFieldSetMapper(SquadraRepository squadraRepository, TorneoRepository torneoRepository) {
        this.squadraRepository = squadraRepository;
        this.torneoRepository = torneoRepository;
    }

    @Override
    @NonNull
    public SquadraTorneoEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        SquadraTorneoEntity squadraTorneo = new SquadraTorneoEntity();
        TorneoEntity torneo = new TorneoEntity();
        torneo.setNomeTorneo(fieldSet.readString(1).trim());
        squadraTorneo.setTorneo(torneo);

        SquadraEntity squadra = new SquadraEntity();
        squadra.setNome(fieldSet.readString(2).trim());
        squadraTorneo.setSquadra(squadra);

        return squadraTorneo;
    }
}
