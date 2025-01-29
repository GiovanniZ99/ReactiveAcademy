package it.reactive.springbatch.reader;

import it.reactive.springbatch.utility.CustomLineTokenizer;
import it.reactive.springbatch.utility.fieldsetmapper.GiocatoreFieldSetMapper;
import it.reactive.springbatch.utility.fieldsetmapper.SquadraFieldSetMapper;
import it.reactive.springbatch.utility.fieldsetmapper.SquadraTorneoFieldSetMapper;
import it.reactive.springbatch.utility.fieldsetmapper.TorneoFieldSetMapper;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class TorneoFileReader extends FlatFileItemReader<Object> {
    private final TorneoFieldSetMapper torneoFieldSetMapper;
    private final SquadraFieldSetMapper squadraFieldSetMapper;
    private final GiocatoreFieldSetMapper giocatoreFieldSetMapper;
    private final SquadraTorneoFieldSetMapper squadraTorneoFieldSetMapper;

    @Autowired
    public TorneoFileReader(TorneoFieldSetMapper torneoFieldSetMapper, SquadraFieldSetMapper squadraFieldSetMapper,
                            GiocatoreFieldSetMapper giocatoreFieldSetMapper, SquadraTorneoFieldSetMapper squadraTorneoFieldSetMapper) {
        this.torneoFieldSetMapper = torneoFieldSetMapper;
        this.squadraFieldSetMapper = squadraFieldSetMapper;
        this.giocatoreFieldSetMapper = giocatoreFieldSetMapper;
        this.squadraTorneoFieldSetMapper = squadraTorneoFieldSetMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setResource(new ClassPathResource("file/batchTorneo.txt"));

        DefaultLineMapper<Object> lineMapper = new DefaultLineMapper<>();

        lineMapper.setLineTokenizer(new CustomLineTokenizer());

        lineMapper.setFieldSetMapper(new FieldSetMapper<Object>() {
            @Override
            @NonNull
            public Object mapFieldSet(@NonNull FieldSet fieldSet) throws BindException {
                String tipo = fieldSet.readString(0).trim();
                return switch (tipo) {
                    case "TO" -> torneoFieldSetMapper.mapFieldSet(fieldSet);
                    case "SQ" -> squadraFieldSetMapper.mapFieldSet(fieldSet);
                    case "GI" -> giocatoreFieldSetMapper.mapFieldSet(fieldSet);
                    case "TS" -> squadraTorneoFieldSetMapper.mapFieldSet(fieldSet);
                    default -> throw new IllegalArgumentException("Tipo di record non valido");
                };
            }
        });

        setLineMapper(lineMapper);
    }
}
