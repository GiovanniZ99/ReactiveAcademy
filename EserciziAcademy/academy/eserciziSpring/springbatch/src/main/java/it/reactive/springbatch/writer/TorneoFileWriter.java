package it.reactive.springbatch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TorneoFileWriter {
    @Bean
    public ItemWriter<Object> setupItemWriter(){
        return new ItemStreamWriter<Object>() {
            @Override
            public void write(Chunk<?> chunk) throws Exception {

            }
        }
    }
}
