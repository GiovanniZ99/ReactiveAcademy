package it.reactive.academy.computer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("ADMIN")
@Configuration
public class ConfigurazioneAdmin {

    @Bean
    public Tastiera Tastiera() {
        return new Tastiera();
    }
}
