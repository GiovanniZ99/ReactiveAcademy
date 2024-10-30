package academy.esercizi.esercizio_18_5;

public class Conto {
    private double saldo;
    private double interessi;

    public Conto(double saldo, double interessi) {
        this.saldo = saldo;
        this.interessi = interessi;
    }

    public double calcolaInteresse(double saldo,double interessi){
        return saldo + (saldo * interessi);
    }

    public double getSaldo() {
        return saldo;
    }

    public double getInteressi() {
        return interessi;
    }
}
