package academy.esercizi.esercizi_18_4;

public class Cliente {
    private double saldoCheck;
    private double saldoSav;

    public Cliente(double saldoCheck, double saldoSav) {
        this.saldoCheck = saldoCheck;
        this.saldoSav = saldoSav;
    }


    public void withdrawal(double conto, double prelievo) {
        if (prelievo <= conto) {
            conto -= prelievo;
            System.out.println(conto);
        } else {
            System.out.println("Importo non valido");
        }
    }

    public void deposit(double conto, double prelievo) {
        if (prelievo > 0) {
            conto += prelievo;
            System.out.println(conto);
        } else {
            System.out.println("Importo non valido");
        }
    }

    public void transfer(double contoMittente, double contoDestinatario, double cifraDaTrasferire) {
        if (cifraDaTrasferire <= contoMittente) {
            contoDestinatario += cifraDaTrasferire;
            contoMittente -= cifraDaTrasferire;
            System.out.println(contoDestinatario);
            System.out.println(contoMittente);
        } else {
            System.out.println("Importo non valido");
        }
    }
    public double getSaldoCheck() {
        return saldoCheck;
    }

    public void setSaldoCheck(double saldoCheck) {
        this.saldoCheck = saldoCheck;
    }

    public double getSaldoSav() {
        return saldoSav;
    }

    public void setSaldoSav(double saldoSav) {
        this.saldoSav = saldoSav;
    }
}
