package academy.esercizi.esercizio34_1;

public class Operazione extends OggettoMatematico {
    private double op1;
    private double op2;
    private char op;

    public Operazione(double v, double op1, double op2, char op) {
        super(v);
        this.op1 = op1;
        this.op2 = op2;
        this.op = op;
    }

    @Override
    public double getValore() {
        switch (op) {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            case '/':
                return op1 / op2;
            case '*':
                return op1 * op2;
            default:
                return -1;
        }
    }

    @Override
    public String stampa() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(op1)
                .append(" ")
                .append(op)
                .append(" ")
                .append(op2)
                .append(" = ")
                .append(getValore());

        return stringBuilder.toString();
    }

    public double getOp1() {
        return op1;
    }

    public double getOp2() {
        return op2;
    }

    public char getOp() {
        return op;
    }
}
