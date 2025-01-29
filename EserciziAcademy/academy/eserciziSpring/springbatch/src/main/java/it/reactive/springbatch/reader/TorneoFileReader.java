package it.reactive.springbatch.reader;

import it.reactive.springbatch.utility.CustomLineTokenizer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class TorneoFileReader extends FlatFileItemReader<String> {

    @Override
    public void afterPropertiesSet() throws Exception {
        setResource(new ClassPathResource("file/batchTorneo.txt"));

        DefaultLineMapper<String> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new CustomLineTokenizer());
        lineMapper.setFieldSetMapper((FieldSetMapper<String>) fieldSet -> fieldSet.readString(0));

        setLineMapper(lineMapper);
    }
}
