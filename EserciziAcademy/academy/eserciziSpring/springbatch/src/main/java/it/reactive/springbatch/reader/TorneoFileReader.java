package it.reactive.springbatch.reader;

import it.reactive.springbatch.utility.CustomLineTokenizer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class TorneoFileReader extends FlatFileItemReader<String> {

    @Override
    public void afterPropertiesSet() throws Exception {
        setResource(new ClassPathResource("file/batchTorneo.txt"));

        DefaultLineMapper<String> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new CustomLineTokenizer());
        lineMapper.setFieldSetMapper(new FieldSetMapper<String>() {
            @Override
            @NonNull
            public String mapFieldSet(@NonNull FieldSet fieldSet) throws BindException {
                StringBuilder sb = new StringBuilder();
                for (String value : fieldSet.getValues()) {
                    sb.append(value);
                }
                return sb.toString();
            }
        });

        setLineMapper(lineMapper);
    }
}
