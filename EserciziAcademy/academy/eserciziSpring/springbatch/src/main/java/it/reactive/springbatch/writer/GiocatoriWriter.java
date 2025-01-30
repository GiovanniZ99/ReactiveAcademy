package it.reactive.springbatch.writer;

import it.reactive.springbatch.model.GiocatoreCSV;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;


@Component
public class GiocatoriWriter extends FlatFileItemWriter<GiocatoreCSV> {

    public GiocatoriWriter() {
        DelimitedLineAggregator<GiocatoreCSV> delimitedLineAggregator = new DelimitedLineAggregator<>();
        delimitedLineAggregator.setDelimiter(";");
        this.setResource(new FileSystemResource("file/giocatori.csv"));
        this.setLineAggregator(delimitedLineAggregator);
        BeanWrapperFieldExtractor<GiocatoreCSV> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{
                "idGiocatore",
                "nomeCognome",
                "numeroAmmonizioni",
                "nomeSquadra",
                "coloriSociali",
                "nomeTifoseria"
        });
        fieldExtractor.afterPropertiesSet();
        delimitedLineAggregator.setFieldExtractor(fieldExtractor);

    }
}
