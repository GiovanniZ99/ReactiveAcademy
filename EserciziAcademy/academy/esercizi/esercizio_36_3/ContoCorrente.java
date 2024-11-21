package academy.esercizi.esercizio_36_3;

public class ContoCorrente {
    private double saldo;
    private String numeroConto;

    public ContoCorrente(double saldo, String numeroConto) {
        this.saldo = saldo;
        this.numeroConto = numeroConto;
    }

    public double versa(int importo) throws ErroreVersamento {
        if(importo<=1000){
        this.saldo+=importo;
        return this.saldo;
        }
            throw new ErroreVersamento("Importo superiore a 1000", importo);
    }
    public double preleva(int importo) throws ErroreVersamento{
        if(importo<= this.saldo){
            this.saldo -= importo;
            return this.saldo;
        }
        throw new ErroreVersamento("Importo superiore al saldo", importo, this.numeroConto, this.saldo, importo);
    }


    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumeroConto() {
        return numeroConto;
    }

    public void setNumeroConto(String numeroConto) {
        this.numeroConto = numeroConto;
    }
}
