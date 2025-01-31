package it.reactive.springbatch.utility;

import org.springframework.batch.item.file.transform.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomLineTokenizer implements LineTokenizer {
    @Override
    @NonNull
    public FieldSet tokenize(String line) {

        String tipo = Objects.requireNonNull(line).substring(0,2).trim();
        String[] tokens;

        switch (tipo){
            case "TO":
                line = line.trim();
                FixedLengthTokenizer fixedLengthTokenizer = new FixedLengthTokenizer();
                fixedLengthTokenizer.setColumns(new Range(1,2), new Range(3, line.length()));
                tokens = fixedLengthTokenizer.tokenize(line).getValues();
                break;
            case "SQ":
                FixedLengthTokenizer squadraTokenizer = new FixedLengthTokenizer();
                squadraTokenizer.setColumns(
                        new Range(1, 2),
                        new Range(3, 101),
                        new Range(102, 201),
                        new Range(202, line.length())
                );
                tokens = squadraTokenizer.tokenize(line).getValues();
                break;
            case "GI":
                FixedLengthTokenizer giocatoreTokenizer = new FixedLengthTokenizer();
                giocatoreTokenizer.setColumns(
                        new Range(1, 2),
                        new Range(3, 51),
                        new Range(52, 101),
                        new Range(102, line.length())
                );
                tokens = giocatoreTokenizer.tokenize(line).getValues();
                break;
            case "TS":
               FixedLengthTokenizer torneoSquadraTokenizer = new FixedLengthTokenizer();
               torneoSquadraTokenizer.setColumns(
                       new Range(1,2),
                       new Range(3, 101),
                       new Range(102, line.length()));
               tokens = torneoSquadraTokenizer.tokenize(line).getValues();
               break;
            default:
                throw new IllegalArgumentException();
        }
        return new DefaultFieldSet(tokens);
    }
}
