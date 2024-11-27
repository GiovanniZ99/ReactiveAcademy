package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Windows implements SistemaOperativo{
    @Value("&{sistemaoperativo.linguaggio")
    String linguaggio;
    @Override
    public String getNome() {
        return "Windows";
    }

    @Override
    public String getLinguaggio() {
        return linguaggio;
    }
}
