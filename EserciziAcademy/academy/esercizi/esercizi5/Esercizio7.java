package academy.esercizi.esercizi5;

/*Vogliamo rimuovere tutti gli spazi e i trattini presenti nella stringa creditCardNumber:
ad esempio, se la stringa fosse "4123-5678-9012-3450”, dovremmo trasformarla
in "4123567890123450". Non usare i metodi replace e replaceAll sulla classe String ma ciclare
i singoli caratteri
 */
public class Esercizio7 {
    public static void main(String[] args) {
        System.out.println(rimuoviSpazi("4434355455-554  55545"));
    }

    public static String rimuoviSpazi(String creditCardNumber) {
        String numeroSenzaSpaziOTrattini = "";
        for (int i = 0; i < creditCardNumber.length() - 1; i++) {
            if (creditCardNumber.charAt(i) != ' ' && creditCardNumber.charAt(i)!= '-') {
                numeroSenzaSpaziOTrattini += creditCardNumber.charAt(i);
            }
        }

        return numeroSenzaSpaziOTrattini;
    }
}
