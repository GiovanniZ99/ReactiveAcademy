package it.reactive.springbatch.utility;

public class Costanti {
    public static final String DATA_SOURCE_TORNEO = "dataSourceTorneo";
    public static final String ENTITY_MANAGER_TORNEO = "torneoEntityManager";
    public static final String TORNEO_TRANSACTION_MANAGER = "torneoTransactionManager";
    public static final String ENTITIES_PACKAGE = "it.reactive.springbatch.entity";
    public static final String PREFIX_PROPERTIES = "spring.datasource.torneo";
    public static final String H2_DATASOURCE = "h2Datasource";
    public static final String H2_CREATE_SCHEMA = "classpath:org/springframework/batch/core/schema-drop-h2.sql";
    public static final String H2_DROP_SCHEMA =  "classpath:org/springframework/batch/core/schema-h2.sql";
    public static final String JOB_DB_SETUP_WITH_CSV = "jobDbSetupWithCsv";
    public static final String DELETE_ALL_STEP = "deleteAllStep";
    public static final String TASKLET_DELETE = "taskletDelete";
    public static final String DB_SETUP_STEP = "dbSetupStep";
    public static final String CSV_STEP = "csvStep";
    public static final String TORNEO_CSV_READER = "torneoCsvReader";
    public static final String TORNEO_PROCESSOR = "torneoProcessor";
    public static final String TORNEO_DB_WRITER = "torneoDbWriter";
    public static final String GIOCATORI_DB_READER = "giocatoriDbReader";
    public static final String GIOCATORI_PROCESSOR = "giocatoriProcessor";
    public static final String GIOCATORI_CSV_WRITER = "giocatoriCsvWriter";

    private Costanti() {
    }
}
