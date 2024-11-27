package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Computer {
    Schermo schermo;
    HardDisk hardDisk;
    Tastiera tastiera;

    public Computer(Schermo schermo, HardDisk hardDisk) {
        this.schermo = schermo;
        this.hardDisk = hardDisk;
    }

    @Autowired(required = false)
    public void setTastiera(Tastiera tastiera) {
        this.tastiera = tastiera;
    }

    @Autowired
    @Qualifier("windows")
    SistemaOperativo sistemaOperativo;


    public void saluta() {
        System.out.println("Sono il computer");
        System.out.println("uso lo schermo: " + schermo.getSchermo());
        System.out.println("sono configurato con il sistema operativo: " + sistemaOperativo.getNome());
        System.out.println("ed il linguaggio: " + sistemaOperativo.getLinguaggio());
        System.out.println("Il separatore di linee: " + sistemaOperativo.getLineSeparator());
        if (tastiera != null) {
            System.out.println("uso la tastiera: " + tastiera.tasti());
        } else {
            System.out.println("non hai configurato la tastiera");
        }
        System.out.println("l'hard disk scelto e':" + hardDisk.tipo());
    }

}
