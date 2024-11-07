package academy.esercizi.esercizio34_1;

public class Intero extends OggettoMatematico implements Raddoppiabile, Triplicabile {

    public Intero(int valore) {
        this.v = valore;
    }

    @Override
    public double getValore() {
        return super.getValore();
    }

    @Override
    public void raddoppia() {
       v*=2;
    }

    @Override
    public void triplica() {
        v*=3;
    }

    public String stampa(){
        return String.valueOf(getValore());
    }

    @Override
    public boolean isDimezzabile() {
        return Raddoppiabile.super.isDimezzabile();
    }
}
