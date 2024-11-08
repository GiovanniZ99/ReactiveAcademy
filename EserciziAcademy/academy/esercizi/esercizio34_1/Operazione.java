package academy.esercizi.esercizio34_1;

public class Operazione extends OggettoMatematico {
    private double op1;
    private double op2;
    private char op;

    public Operazione (double op1, double op2, char op) {
        this.op1 = op1;
        this.op2 = op2;
        this.op = op;
    }

    @Override
    public double getValore() {
        switch (this.op) {
            case '+':
                return this.op1 + this.op2;
            case '-':
                return this.op1 - this.op2;
            case '/':
                return this.op1 / this.op2;
            case '*':
                return this.op1 * this.op2;
            default:
                return -1;
        }
    }

    @Override
    public String stampa() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.op1)
                .append(" ")
                .append(this.op)
                .append(" ")
                .append(this.op2)
                .append(" = ")
                .append(getValore());

        return stringBuilder.toString();
    }

    public double getOp1() {
        return this.op1;
    }

    public double getOp2() {
        return this.op2;
    }

    public char getOp() {
        return this.op;
    }
}
