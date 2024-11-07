package academy.esercizi.esercizio34_1;

public class Frazione extends OggettoMatematico implements  Raddoppiabile, Triplicabile{
    private final int numeratore;
    private final int denominatore;

    public Frazione(double v, int numeratore, int denominatore) {
        this.numeratore = numeratore;
        this.denominatore = denominatore;
    }
    public boolean isFrazionePropria(){
        return numeratore % denominatore == 0;
    }

    public double frazioneInversa(){
        return (double) this.denominatore /this.denominatore;
    }

    @Override
    public void triplica() {
        v*=3;
    }

    @Override
    public void raddoppia() {
        v*=2;
    }

    @Override
    public boolean isDimezzabile() {
        return (numeratore % 2 == 0) && (denominatore % 2 == 0);
    }

    @Override
    public double getValore() {
        return (double) numeratore /denominatore;
    }

    @Override
    public String stampa() {
        return "Frazione: " + numeratore + "/" + denominatore;
    }

    public int getNumeratore() {
        return numeratore;
    }

    public int getDenominatore() {
        return denominatore;
    }
}
