package academy.esercizi.esercizio_36_3;

import java.text.ParseException;

public class ErroreVersamento extends ParseException {

    public ErroreVersamento(String s, int errorOffset, String numeroConto, double saldo, int importo) {
        super(s, errorOffset);
    }
    public ErroreVersamento(String s, int errorOffset) {
        super(s, errorOffset);
    }
}
