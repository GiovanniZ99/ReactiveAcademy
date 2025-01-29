package it.reactive.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TorneoProcessor implements ItemProcessor<Object, Object> {
    private final Set<Object> righe = Collections.newSetFromMap(new ConcurrentHashMap<>());
    @Override
    public Object process(@NonNull Object item) throws Exception {
        if (righe.contains(item)) {
            return null;
        }
        righe.add(item);

        return item;
    }
}
