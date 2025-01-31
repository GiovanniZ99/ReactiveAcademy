package it.reactive.springbatch.configuration;

import it.reactive.springbatch.entity.GiocatoreEntity;
import it.reactive.springbatch.model.GiocatoreCSV;
import it.reactive.springbatch.repository.*;
import it.reactive.springbatch.utility.Costanti;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
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
            JobRepository jobRepository) {

        return new JobLauncherApplicationRunner(jobLauncher, jobExplorer, jobRepository);
    }

//    @Bean
//    public JobLauncherApplicationRunner csvExportJobRunner(JobLauncher jobLauncher, JobExplorer jobExplorer, JobRepository jobRepository, @Qualifier("csvExportJob") Job csvExportJob) {
//        JobLauncherApplicationRunner runner = new JobLauncherApplicationRunner(jobLauncher, jobExplorer, jobRepository);
//        runner.setJobName("csvExportJob");
//        return runner;
//    }

    @Bean(Costanti.JOB_DB_SETUP_WITH_CSV)
    public Job jobSetupWithCsv(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                        @Qualifier(Costanti.TORNEO_CSV_READER) FlatFileItemReader<String> reader,
                        @Qualifier(Costanti.TORNEO_DB_WRITER) JpaItemWriter<Object> writer,
                        @Qualifier(Costanti.TORNEO_PROCESSOR) ItemProcessor<String, Object> itemProcessor,
                        @Qualifier(Costanti.TASKLET_DELETE) Tasklet tasklet,
                        @Qualifier(Costanti.GIOCATORI_DB_READER) JpaPagingItemReader<GiocatoreEntity> giocatoreReader,
                        @Qualifier(Costanti.GIOCATORI_CSV_WRITER) FlatFileItemWriter<GiocatoreCSV> giocatoreWriter,
                        @Qualifier(Costanti.GIOCATORI_PROCESSOR) ItemProcessor<GiocatoreEntity, GiocatoreCSV> giocatoreProcessor) {
        return new JobBuilder("jobSetup", jobRepository)
                .start(deleteAll(jobRepository, transactionManager, tasklet))
                .next(dbSetup(jobRepository, transactionManager, reader, writer, itemProcessor))
                .next(csvStep(jobRepository, transactionManager, giocatoreReader, giocatoreWriter, giocatoreProcessor))
                .build();
    }

//    @Bean("csvExportJob")
//    public Job csvExportJob(JobRepository jobRepository, PlatformTransactionManager transactionManager,
//                            JpaPagingItemReader<GiocatoreEntity> reader,
//                            @Qualifier() GiocatoriWriter writer,
//                            ItemProcessor<GiocatoreEntity, GiocatoreCSV> processor) {
//        return new JobBuilder("csvExportJob", jobRepository)
//                .start(csvStep(jobRepository, transactionManager, reader, writer, processor))
//                .build();
//    }

    @Bean(name = Costanti.DELETE_ALL_STEP)
    public Step deleteAll(JobRepository jobRepository,
                          @Qualifier(Costanti.TORNEO_TRANSACTION_MANAGER) PlatformTransactionManager transactionManager,
                          @Qualifier(Costanti.TASKLET_DELETE) Tasklet tasklet) {
        return new StepBuilder(Costanti.DELETE_ALL_STEP, jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean(Costanti.TASKLET_DELETE)
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

    @Bean(Costanti.DB_SETUP_STEP)
    public Step dbSetup(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                        @Qualifier(Costanti.TORNEO_CSV_READER) FlatFileItemReader<String> reader,
                        @Qualifier(Costanti.TORNEO_DB_WRITER) JpaItemWriter<Object> writer,
                        @Qualifier(Costanti.TORNEO_PROCESSOR) ItemProcessor<String, Object> itemProcessor) {
        return new StepBuilder(Costanti.DB_SETUP_STEP, jobRepository)
                .<String, Object>chunk(100, transactionManager)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step csvStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                        @Qualifier(Costanti.GIOCATORI_DB_READER) JpaPagingItemReader<GiocatoreEntity> reader,
                        @Qualifier(Costanti.GIOCATORI_CSV_WRITER) FlatFileItemWriter<GiocatoreCSV> writer,
                        @Qualifier(Costanti.GIOCATORI_PROCESSOR) ItemProcessor<GiocatoreEntity, GiocatoreCSV> processor) {
        return new StepBuilder(Costanti.CSV_STEP, jobRepository)
                .<GiocatoreEntity, GiocatoreCSV>chunk(100, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}