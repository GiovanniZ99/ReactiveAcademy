package it.reactive.springbatch.processor;

import it.reactive.springbatch.entity.SquadraEntity;
import it.reactive.springbatch.entity.TorneoEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TorneoFileProcessor {
    @Bean
    public ItemProcessor<String, Object> setupFileProcessor(String line) {
//        return new ItemProcessor<String, Object>() {
//            @Override
//            public Object process(String item) throws Exception {
//                String tipo = line.substring(0,2);
//                String nome = line.substring(2);
//                line.trim();
//                switch (tipo){
//                    case "TO":
//                        TorneoEntity torneo = new TorneoEntity();
//                        torneo.setNomeTorneo(nome);
//                        return torneo;
//                    case"SQ":
//                        SquadraEntity squadra = new SquadraEntity();
//                        String nome = line.s
//                        squadra.setNome(nome);
//                        squadra.setColoriSociali();
//                }
//            }
//        }
//
//    }
}
