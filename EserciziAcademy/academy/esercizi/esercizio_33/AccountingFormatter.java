package academy.esercizi.esercizio_33;

public class AccountingFormatter implements NumberFormatter {
    @Override
    public String format(int n) {
        String result = String.valueOf(n).replace("-", "(");
        if (n < 0) {
            return result.concat(")");
        }
        return "Numero non negativo";
    }
}
