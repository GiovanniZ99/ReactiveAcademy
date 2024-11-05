package academy.esercizi.esercizi3;

/*Create un array bidimensionale che rappresenti la scacchiera del «gioco del tris»:
deve avere tre righe e tre colonne e ciascuna casella può contenere le stringhe «x», «o»
oppure « ». Scrivere un metodo di assegnazione che inserisca una «x» nell’angolo superiore
destro della scacchiera, valorizzare le altre posizioni in maniera casuale. Quali sono gli
elementi che si trovano sulla diagonale che collega la casella superiore sinistra e la casella
inferiore destra?
 */
public class Esercizio3_3 {
    public static void main(String[] args) {
        stampaMatrice(creaMatriceTris());
    }

    public static String[][] creaMatriceTris() {
        String[][] tris = new String[3][3];

        for (int i = 0; i < tris.length; i++) {
            for (int j = 0; j < tris[i].length; j++) {
                tris[i][j] = String.valueOf(generaOpzioni()[0]);
            }
        }
        tris[0][2] = "x";
        System.out.println("Gli elementi sono: " + tris[0][0] + tris[1][1] + tris[2][2]);
        return tris;
    }

    public static char[] generaOpzioni() {
        char[] opzioni = new char[3];
        java.util.Random random = new java.util.Random();
        char[] valoriPossibili = {'x', 'o', ' '};

        for (int i = 0; i < opzioni.length; i++) {
            int indiceOpzioni = random.nextInt(valoriPossibili.length);
            opzioni[i] = valoriPossibili[indiceOpzioni];
        }
        return opzioni;
    }

    public static void stampaMatrice(String[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + ' ');
            }
            System.out.println();
        }
    }

}
