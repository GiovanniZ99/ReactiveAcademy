package academy.esercizi.esercizi_18_4;

import java.util.Scanner;
/* Scrivete un programma che simuli una transazione bancaria. Occorre gestire due conti
bancari: un conto corrente (checking account) e un conto di risparmio (savings account).
Per prima cosa chiedete all’utente i saldi iniziali dei due conti, rifiutando saldi negativi.
Poi chiedete quale sia il tipo di operazione da eseguire: versamento (deposit), prelievo
(withdrawal) o bonifico (transfer). Ancora, chiedete il tipo di conto: checking o savings.
Rifiutate transazioni che rendano negativo il saldo di un conto. Infine, visualizzate i saldi
dei due conti dopo l’operazione richiesta.
 */

public class Esercizio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il saldo del conto corrente");
        double saldoCheck = scanner.nextDouble();
        System.out.println("Inserisci il saldo del conto di risparmio");
        double saldoSav = scanner.nextDouble();
        Cliente cliente = new Cliente(saldoCheck, saldoSav);
        if (saldoCheck < 0 || saldoSav < 0) {
            System.out.println("Importi non validi");
        }
        System.out.println("Inserire l'operazione che si desidera effetuare");
        System.out.println("Premere uno per un prelievo sul conto corrente");
        System.out.println("Premere due per un prelievo sul conto risparmio");
        System.out.println("Premere tre per un deposito sul conto corrente");
        System.out.println("Premere quattro per un deposito sul conto risparmio");
        System.out.println("Premere cinque per trasferire una cifra da un conto a un altro");
        int scelta = scanner.nextInt();
        double cifra = scanner.nextDouble();
        switch (scelta) {
            case 1:
                cliente.withdrawal(true, cifra);
                break;
            case 2:
                cliente.withdrawal(false, cifra);
                break;
            case 3:
                cliente.deposit(true, cifra);
                break;
            case 4:
                cliente.deposit(false, cifra);
                break;
            case 5:
                cliente.transfer(true, cifra);
                break;
            case 6:
                cliente.transfer(false ,cifra);
                break;
            default:
                System.out.println("Numero inserito non valido");
        }
    }
}
