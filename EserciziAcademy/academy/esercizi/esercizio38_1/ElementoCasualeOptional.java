package academy.esercizi.esercizio38_1;

import java.util.Optional;
import java.util.Random;

public class ElementoCasualeOptional {
    private Optional<Integer> valore;
    private final static Random RANDOM = new Random();

    public ElementoCasualeOptional() {
        int randomOrNull = RANDOM.nextInt(2);
        if(randomOrNull == 0){
            this.valore = Optional.empty();
        }else{
            this.valore = Optional.of(RANDOM.nextInt(1901) +100);
        }
    }

    public ElementoCasualeOptional(Integer  valore) {
        this.valore = Optional.ofNullable(valore);
    }

    public Optional<Integer> getValore() {
        return valore;
    }

    public void setValore(Optional<Integer> valore) {
        this.valore = valore;
    }
}
