package it.reactive.springbatch.configuration;

import it.reactive.springbatch.repository.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class BatchConfig {
    private final GiocatoreRepository giocatoreRepository;
    private final SquadraRepository squadraRepository;
    private final SquadraTorneoRepository squadraTorneoRepository;
    private final TifoseriaRepository tifoseriaRepository;
    private final TorneoRepository torneoRepository;

    public BatchConfig(GiocatoreRepository giocatoreRepository,
                       SquadraRepository squadraRepository,
                       SquadraTorneoRepository squadraTorneoRepository,
                       TifoseriaRepository tifoseriaRepository, TorneoRepository torneoRepository) {
        this.giocatoreRepository = giocatoreRepository;
        this.squadraRepository = squadraRepository;
        this.squadraTorneoRepository = squadraTorneoRepository;
        this.tifoseriaRepository = tifoseriaRepository;
        this.torneoRepository = torneoRepository;
    }

    @Bean
    public Job jobDelete(JobRepository jobRepository,
                         @Qualifier("deleteAll") Step delete) {
        return new JobBuilder("jobDelete", jobRepository)
                .start(delete)
                .build();
    }


    @Bean
    public Step deleteAll(JobRepository jobRepository,
                          @Qualifier("torneoTransactionManager") PlatformTransactionManager transactionManager,
                          @Qualifier("tsDelet") Tasklet tasklet) {
        return new StepBuilder("deleteAll", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean
    @Transactional
    public Tasklet tsDelet() {
        return (contribution, chunkContext) -> {

            squadraTorneoRepository.deleteAll();
            tifoseriaRepository.deleteAll();
            giocatoreRepository.deleteAll();
            torneoRepository.deleteAll();
            squadraRepository.deleteAll();

            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step dbSetup(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                        @Qualifier("setuoFileReader") ItemReader<String> itemReader,
                        @Qualifier("setupFileWriter") ItemWriter<Object> itemWriter,
                        @Qualifier("setupFileProcessor") ItemProcessor<String, Object> itemProcessor) {
        return new StepBuilder("dbSetup", jobRepository)
                .<String, Object>chunk(50, transactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
}