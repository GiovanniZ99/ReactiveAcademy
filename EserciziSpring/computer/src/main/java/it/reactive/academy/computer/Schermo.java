package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Schermo {
    @Value("${SCHERMO}")
    private String dim;
    public String getSchermo(){
        return dim;
    }
}
