package it.reactive.springbatch.configuration;

import it.reactive.springbatch.entity.GiocatoreEntity;

import it.reactive.springbatch.model.GiocatoreCSV;
import it.reactive.springbatch.repository.*;
import it.reactive.springbatch.writer.GiocatoriWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

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
    public JobLauncherApplicationRunner jobLauncherApplicationRunner(
            JobLauncher jobLauncher,
            JobExplorer jobExplorer,
            JobRepository jobRepository,
            @Qualifier("jobSetup") Job jobSetup) {

        JobLauncherApplicationRunner runner = new JobLauncherApplicationRunner(jobLauncher, jobExplorer, jobRepository);
        runner.setJobName("jobSetup");
        return runner;
    }
    @Bean
    public JobLauncherApplicationRunner csvExportJobRunner(JobLauncher jobLauncher, JobExplorer jobExplorer, JobRepository jobRepository, @Qualifier("csvExportJob") Job csvExportJob) {
        JobLauncherApplicationRunner runner = new JobLauncherApplicationRunner(jobLauncher, jobExplorer, jobRepository);
        runner.setJobName("csvExportJob");
        return runner;
    }

    @Bean("jobSetup")
    public Job jobSetup(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                        FlatFileItemReader<String> reader,
                        JpaItemWriter<Object> writer,
                        ItemProcessor<String, Object> itemProcessor, @Qualifier("tsDelet") Tasklet tasklet) {
        return new JobBuilder("jobSetup", jobRepository)
                .start(deleteAll(jobRepository, transactionManager, tasklet))
                .next(dbSetup(jobRepository, transactionManager, reader, writer, itemProcessor))
                .build();
    }

    @Bean("csvExportJob")
    public Job csvExportJob(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                             JpaPagingItemReader<GiocatoreEntity> reader,
                            GiocatoriWriter writer,
                             ItemProcessor<GiocatoreEntity, GiocatoreCSV> processor) {
        return new JobBuilder("csvExportJob", jobRepository)
                .start(csvStep(jobRepository, transactionManager, reader, writer, processor))
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
    public Tasklet tsDelet() {
        return (contribution, chunkContext) -> {

            squadraTorneoRepository.deleteAllInBatch();
            tifoseriaRepository.deleteAllInBatch();
            giocatoreRepository.deleteAllInBatch();
            squadraRepository.deleteAllInBatch();
            torneoRepository.deleteAllInBatch();

            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step dbSetup(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                        FlatFileItemReader<String> reader,
                        JpaItemWriter<Object> writer,
                        ItemProcessor<String, Object> itemProcessor) {
        return new StepBuilder("dbSetup", jobRepository)
                .<String, Object>chunk(100, transactionManager)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step csvStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                        JpaPagingItemReader<GiocatoreEntity> reader,
                        GiocatoriWriter writer,
                        ItemProcessor<GiocatoreEntity, GiocatoreCSV> processor) {
        return new StepBuilder("csvStep", jobRepository)
                .<GiocatoreEntity, GiocatoreCSV>chunk(100, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}