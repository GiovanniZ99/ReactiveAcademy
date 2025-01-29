package it.reactive.springbatch.processor;

import it.reactive.springbatch.entity.SquadraEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TorneoProcessor implements ItemProcessor<Object, Object> {
    private final Set<Object> righe = new HashSet<>();
    @Override
    public Object process(@NonNull Object item) throws Exception {
        if (righe.contains(item)) {
            return null;
        }
        righe.add(item);

        if(item instanceof SquadraEntity) {
            System.out.println("Processing: " + item);
        }
        return item;
    }
}
