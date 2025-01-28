package it.reactive.springbatch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class TorneoFileReader extends FlatFileItemReader<String>{
    public TorneoFileReader() {
        this.setResource(new FileSystemResource("file/batchTorneo.txt"));
        this.setLineMapper((line, lineNumber) -> line);
    }
}
