package it.reactive.springbatch.utility.fieldsetmapper;

import it.reactive.springbatch.entity.SquadraEntity;
import it.reactive.springbatch.entity.SquadraTorneoEntity;
import it.reactive.springbatch.entity.SquadraTorneoId;
import it.reactive.springbatch.entity.TorneoEntity;
import it.reactive.springbatch.repository.SquadraRepository;
import it.reactive.springbatch.repository.TorneoRepository;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class SquadraTorneoFieldSetMapper implements FieldSetMapper<SquadraTorneoEntity> {
    private final SquadraRepository squadraRepository;

    private final TorneoRepository torneoRepository;

    @Autowired
    public SquadraTorneoFieldSetMapper(SquadraRepository squadraRepository, TorneoRepository torneoRepository) {
        this.squadraRepository = squadraRepository;
        this.torneoRepository = torneoRepository;
    }

    @Override
    @NonNull
    public SquadraTorneoEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        String nomeTorneo = fieldSet.readString(1).trim();
        String nomeSquadra = fieldSet.readString(2).trim();

        TorneoEntity torneo = torneoRepository.findByNomeTorneo(nomeTorneo)
                .orElseGet(() -> {
                    TorneoEntity newTorneo = new TorneoEntity();
                    newTorneo.setNomeTorneo(nomeTorneo);
                    return torneoRepository.save(newTorneo);
                });

        SquadraEntity squadra = squadraRepository.findByNome(nomeSquadra)
                .orElseGet(() -> {
                    SquadraEntity newSquadra = new SquadraEntity();
                    newSquadra.setNome(nomeSquadra);
                    newSquadra.setColoriSociali("default");
                    return squadraRepository.save(newSquadra);
                });

        SquadraTorneoId squadraTorneoId = new SquadraTorneoId();
        squadraTorneoId.setIdTorneo(torneo.getIdTorneo());
        squadraTorneoId.setIdSquadra(squadra.getIdSquadra());

        SquadraTorneoEntity squadraTorneo = new SquadraTorneoEntity();
        squadraTorneo.setId(squadraTorneoId);
        squadraTorneo.setTorneo(torneo);
        squadraTorneo.setSquadra(squadra);

        return squadraTorneo;
    }
}
