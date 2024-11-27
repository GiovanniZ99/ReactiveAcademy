package it.reactive.academy.computer;

public interface SistemaOperativo {
    String getNome();
    String getLinguaggio();
    default String getLineSeparator(){
        return System.lineSeparator();
    }
}
