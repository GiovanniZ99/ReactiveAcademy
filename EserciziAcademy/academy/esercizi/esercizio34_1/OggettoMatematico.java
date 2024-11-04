package academy.esercizi.esercizio34_1;

public abstract class OggettoMatematico {
    double v;

    public OggettoMatematico(double v) {
        this.v = v;
    }

    public double getValore(){
        return this.v;
    }
    public String stampa(){
        return String.valueOf(v);
    }
}
