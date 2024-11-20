package academy.esercizi.esercizio_33;

public class BaseFormatter implements NumberFormatter {
    private int n;
    public BaseFormatter(int n) {
        if(n<2 || n>36 ){
            throw new IllegalArgumentException("Il numero deve essere compreso tra 2 e 36");
        }
        this.n = n;
    }

    @Override
    public String format(int b) {
        return Integer.toString(this.n, b);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
