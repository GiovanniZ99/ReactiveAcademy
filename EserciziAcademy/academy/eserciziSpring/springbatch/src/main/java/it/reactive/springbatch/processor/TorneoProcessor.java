package it.reactive.springbatch.processor;

import it.reactive.springbatch.utility.CustomLineTokenizer;
import it.reactive.springbatch.utility.fieldsetmapper.GiocatoreFieldSetMapper;
import it.reactive.springbatch.utility.fieldsetmapper.SquadraFieldSetMapper;
import it.reactive.springbatch.utility.fieldsetmapper.SquadraTorneoFieldSetMapper;
import it.reactive.springbatch.utility.fieldsetmapper.TorneoFieldSetMapper;
import org.springframework.batch.item.ItemProcessor;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TorneoProcessor implements ItemProcessor<String, Object> {
    private final TorneoFieldSetMapper torneoFieldSetMapper;
    private final SquadraFieldSetMapper squadraFieldSetMapper;
    private final GiocatoreFieldSetMapper giocatoreFieldSetMapper;
    private final SquadraTorneoFieldSetMapper squadraTorneoFieldSetMapper;
    private final CustomLineTokenizer customLineTokenizer;

    private final Set<String> processedRows = new HashSet<>();

    @Autowired
    public TorneoProcessor(TorneoFieldSetMapper torneoFieldSetMapper, SquadraFieldSetMapper squadraFieldSetMapper,
                           GiocatoreFieldSetMapper giocatoreFieldSetMapper, SquadraTorneoFieldSetMapper squadraTorneoFieldSetMapper, CustomLineTokenizer customLineTokenizer) {
        this.torneoFieldSetMapper = torneoFieldSetMapper;
        this.squadraFieldSetMapper = squadraFieldSetMapper;
        this.giocatoreFieldSetMapper = giocatoreFieldSetMapper;
        this.squadraTorneoFieldSetMapper = squadraTorneoFieldSetMapper;
        this.customLineTokenizer = customLineTokenizer;
    }

    @Override
    public Object process(@NonNull String item) throws Exception {
        if (processedRows.contains(item)) {
            return null;
        } else {
            processedRows.add(item);
        }

        String tipo = item.trim().substring(0, 2);

        FieldSet fieldSet = customLineTokenizer.tokenize(item);

        return switch (tipo) {
            case "TO" -> torneoFieldSetMapper.mapFieldSet(fieldSet);
            case "SQ" -> squadraFieldSetMapper.mapFieldSet(fieldSet);
            case "GI" -> giocatoreFieldSetMapper.mapFieldSet(fieldSet);
            case "TS" -> squadraTorneoFieldSetMapper.mapFieldSet(fieldSet);
            default -> throw new IllegalArgumentException();
        };
    }
}
