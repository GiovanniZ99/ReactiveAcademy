package academy.esercizi.esercizi_18_4;

public class Cliente {
    private double saldoCheck;
    private double saldoSav;

    public Cliente(double saldoCheck, double saldoSav) {
        this.saldoCheck = saldoCheck;
        this.saldoSav = saldoSav;
    }

    public void withdrawal(boolean IsSaldoCheck, double prelievo) {
        if (IsSaldoCheck) {
            if (prelievo <= this.saldoCheck) {
                this.saldoCheck -= prelievo;
                System.out.println(this.saldoCheck);
            } else {
                System.out.println("Importo non valido");
            }
        } else {
            if (prelievo <= this.saldoSav) {
                this.saldoSav -= prelievo;
                System.out.println(this.saldoSav);
            } else {
                System.out.println("Importo non valido");
            }
        }
    }

    public void deposit(boolean IsSaldoCheck, double deposito) {
        if (IsSaldoCheck) {
            this.saldoCheck += deposito;
            System.out.println(this.saldoCheck);
        } else {
            this.saldoSav += deposito;
            System.out.println(this.saldoSav);

        }
    }

    public void transfer(boolean IsSaldoCheck, double cifraDaTrasferire) {
        if (IsSaldoCheck) {
            if (cifraDaTrasferire <= this.saldoCheck) {
                this.saldoCheck -= cifraDaTrasferire;
                this.saldoSav += cifraDaTrasferire;
                System.out.println("Il nuovo saldo del conto corrente è" + this.saldoCheck);
                System.out.println("Il nuovo saldo del conto risparmio è" + this.saldoSav);
            } else {
                System.out.println("Importo non valido");
            }
        } else {
            if (cifraDaTrasferire <= this.saldoSav) {
                this.saldoCheck += cifraDaTrasferire;
                this.saldoSav -= cifraDaTrasferire;
                System.out.println("Il nuovo saldo del conto corrente è" + this.saldoCheck);
                System.out.println("Il nuovo saldo del conto risparmio è" + this.saldoSav);
            } else {
                System.out.println("Importo non valido");
            }
        }
    }
    public double getSaldoCheck() {
        return this.saldoCheck;
    }

    public void setSaldoCheck(double saldoCheck) {
        this.saldoCheck = saldoCheck;
    }

    public double getSaldoSav() {
        return this.saldoSav;
    }

    public void setSaldoSav(double saldoSav) {
        this.saldoSav = saldoSav;
    }
}
