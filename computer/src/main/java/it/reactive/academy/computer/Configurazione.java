package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Configurazione {
    @Value("${hardDisk}")
    private String valore;

    @Bean
    public HardDisk getHardDiskIstance() {
        if ("SSD".equals(this.valore)) {
            return new SSD();
        } else if ("HD".equals(this.valore)) {
            return new HD();
        }
        throw new IllegalArgumentException("Valore variabile d'ambiente non valido");
    }
}
