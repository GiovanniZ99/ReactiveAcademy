package academy.esercizi.esercizio34_1;

public interface Raddoppiabile {
    String DESCRIZIONE_CLASSE = "Questa interfaccia raddoppia il valore di un oggetto" +
            " raddoppiabile";

    void raddoppia();

    default boolean isDimezzabile(){
        throw new RuntimeException("NON SONO ANCORA STATO IMPLEMENTATO");
    }

    static String descivi(){
        return DESCRIZIONE_CLASSE;
    }
}
