package academy.esercizi.esercizio34_1;

public abstract class OggettoMatematico {
   protected double v;


    public double getValore(){
        return this.v;
    }
    public String stampa(){
        return String.valueOf(v);
    }
}
