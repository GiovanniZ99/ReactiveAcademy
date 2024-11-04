package academy.esercizi.esercizio34_1;
/*(*) Definisci una opportuna gerarchia di classi per rappresentare valori numerici:
La classe astratta OggettoMatematico. Contiene:
il metodo double getValore() che restituisce il valore (memorizzato in una variabile d'istanza
chiamata v)
il metodo String stampa() che restituisce una stringa che descrive il valore.
L’interfaccia Raddoppiabile. Contiene:
La firma del metodo void raddoppia();
L’implementazione di default del metodo boolean isDimezzabile che solleva un’eccezione per
metodo non implementato; se non si sono ancora viste le eccezioni basti sapere che il corpo del
metodo abbia solamente il seguente statement: throw new RuntimeException("NON SONO ANCORA STATO
IMPLEMENTATO");
Una costante Stringa DESCRIZIONE_CLASSE con il valore "Questa interfaccia raddoppia il valore
di un oggetto Raddoppiabile"
L’implementazione del metodo static String descrivi() che restituisce DESCRIZIONE_CLASSE
L’interfaccia Triplicabile. Contiene:
La firma del metodo void triplica()
La classe Intero che estende OggettoMatematico ed implementa Raddoppiabile e Triplicabile.
Rappresenta un valore intero. Contiene:
una variabile di istanza valore: private int v che memorizza il valore intero associato
all'oggetto (ereditata da OggettoMatematico)
un costruttore pubblico con un argomento valore che assegna il valore alla variabile di istanza
v.
il metodo pubblico double getValore() che restituisce il valore associato all'oggetto
(sovrascritto ed implementato richiamando quello di OggettoMatematico)
il metodo String stampa() che restituisce una stringa del valore intero associato all'oggetto
(ereditato da OggettoMatematico)
l’implementazione del metodo raddoppia() che agisce sul valore della variabile d’istanza v
l’implementazione del metodo triplica() che agisce sul valore della variabile d’istanza v
nessuna azione per il metodo isDimezzabile()
La classe Frazione che estende OggettoMatematico ed implementa Raddoppiabile e Triplicabile e
rappresenta una frazione. Contiene:
due variabili di istanza private int numeratore e private int denominatore
un costruttore pubblico con due argomenti: numeratore e denominatore;
il metodo pubblico boolean isFrazionePropria() che controlla se la frazione è una frazione propria (cioè se il rapporto numeratore / denominatore ha resto 0).
il metodo Frazione inversa() che non ha parametri che restituisce la frazione inversa della
frazione rappresentata da this, ottenuta scambiando numeratore e denominatore.
i getter per numeratore e denominatore
il metodo ereditato stampa() che deve essere sovrascritto per restituire una stringa nella forma
(numeratore/denominatore).
il metodo ereditato getValore() che restituisce il valore double associato alla frazione.
l’implementazione del metodo raddoppia() che agisce sul valore della variabile d’istanza v
(v è ereditata da OggettoMatematico)
l’implementazione del metodo triplica() che agisce sul valore della variabile d’istanza v
l’override del metodo isDimezzabile() che restituisce se il metodo è effettivamente dimezzabile
La classe Operazione che estende OggettoMatematico e rappresenta un’operazione aritmetica
tra numeri ed una operazione a scelta tra addizione, sottrazione, moltiplicazione e divisione.
Contiene:
tre variabili di istanza private: op1 (operando 1) op2 (operando 2) char op (operazione) che
rappresentano i due operandi e l'operazione scelta tra + - * /
un costruttore pubblico con i tre argomenti op1,op2,op.
i getter delle variabili d’istanza
il metodo ereditato getValore() che restituisce il valore double calcolato mediante l'espressione
il metodo ereditato stampa() sovrascritto per restituiree una una stringa nella forma «op1 op
op2 = valore»  usando uno StringBuilder e richiamando getValore()
*/
public class Esercizio1 {
    public static void main(String[] args) {

    }
}
