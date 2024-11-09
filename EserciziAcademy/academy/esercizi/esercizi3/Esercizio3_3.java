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
       generaGiocate();
    }


    public static void generaGiocate() {
        char[][] tris = new char[3][3];

        java.util.Random random = new java.util.Random();
        tris[0][2] = 'x';
        boolean check = false;
        for (int i = 0; i < tris.length; i++) {
            for (int j = 0; j < tris[i].length; j++) {
                while (tris[i][j] == '\u0000') {
                    int x = random.nextInt(tris.length);
                    int y = random.nextInt(tris.length);
                    if (tris[x][y] == '\u0000') {
                        if (!check) {
                            tris[x][y] = 'o';
                            check = true;
                        } else {
                            tris[x][y] = 'x';
                            check = false;
                        }
                        stampaTris(tris);
                        System.out.println();
                        if(trisColonna(tris, y, 'x') || trisRiga(tris, x, 'x')
                                || trisColonna(tris, y, 'o')|| trisRiga(tris,x, 'o')
                        || trisDiagonale(tris) || trisDiagonaleSecondaria(tris)){
                            return;
                        }
                    }
                }
            }

        }
    }

    public static boolean trisColonna(char[][] matrice, int y, char xo) {
                boolean tris = false;
        for (int i = 0; i < matrice[0].length; i++) {
            if(matrice[i][y] == xo){
                tris = true;
            }else{
                return false;
            }
        }

      return tris;
   }


    public static boolean trisRiga(char[][] matrice, int x, char xo) {
        boolean tris = false;
        for (int i = 0; i < matrice[0].length; i++) {
            if(matrice[x][i] == xo){
                tris = true;
            }else{
                return false;
            }
        }

        return tris;
    }

    public static boolean trisDiagonale(char[][] matrice) {
        boolean tris = false;

            if(matrice[0][0] == 'x' && matrice[1][1] == 'x' && matrice[2][2] == 'x'){
                tris = true;
            }else if(matrice[0][0] == 'o' && matrice[1][1] == 'o' && matrice[2][2] == 'o'){
                tris = true;
            }
        return tris;
    }

    public static boolean trisDiagonaleSecondaria(char[][] matrice) {
        boolean tris = false;

        if (matrice[0][2] == 'x' && matrice[1][1] == 'x' && matrice[2][0] == 'x') {
            tris = true;
        } else if (matrice[0][2] == 'o' && matrice[1][1] == 'o' && matrice[2][0] == 'o') {
            tris = true;
        }

        return tris;
    }


    public static void stampaTris(char[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }
}
