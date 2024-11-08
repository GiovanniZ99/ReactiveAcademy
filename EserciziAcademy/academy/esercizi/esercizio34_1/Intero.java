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
       this.v*=2;
    }

    @Override
    public void triplica() {
        v*=3;
    }


}
