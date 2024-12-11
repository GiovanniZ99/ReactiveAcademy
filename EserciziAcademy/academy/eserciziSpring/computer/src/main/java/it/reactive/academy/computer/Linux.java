package it.reactive.academy.computer;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("linux")
public class Linux implements SistemaOperativo{
    @Value("&{sistemaoperativo.linguaggio")
    String linguaggio;

    @Override
    public String getNome() {
        return "Linux";
    }

    @Override
    public String getLinguaggio() {
        return linguaggio;
    }
}
