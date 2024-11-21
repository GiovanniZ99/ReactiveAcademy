package academy.esercizi.esercizio_36_3;

import java.text.ParseException;

public class ErroreVersamento extends ParseException {

    private String numeroConto;
    private double saldo;
    private int importo;

    public ErroreVersamento(String s, int errorOffset, String numeroConto, double saldo, int importo) {
        super(s, errorOffset);
        this.numeroConto = numeroConto;
        this.saldo = saldo;
        this.importo= importo;
    }
    public ErroreVersamento(String s, int errorOffset) {
        super(s, errorOffset);
    }

    public String getNumeroConto() {
        return numeroConto;
    }

    public void setNumeroConto(String numeroConto) {
        this.numeroConto = numeroConto;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getImporto() {
        return importo;
    }

    public void setImporto(int importo) {
        this.importo = importo;
    }
}
