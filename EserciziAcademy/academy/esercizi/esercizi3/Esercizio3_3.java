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
       generaOpzioni();
    }


    public static char[][] generaOpzioni() {
        char[][] opzioni = new char[3][3];

        java.util.Random random = new java.util.Random();
        opzioni[0][2] = 'x';
        boolean check = false;
        for (int i = 0; i < opzioni.length; i++) {
            for (int j = 0; j < opzioni[i].length; j++) {
                while (opzioni[i][j] == '\u0000') {
                    int x = random.nextInt(opzioni.length);
                    int y = random.nextInt(opzioni.length);
                    if (opzioni[x][y] == '\u0000') {
                        if (!check) {
                            opzioni[x][y] = 'o';
                            check = true;
                            stampaMatrice(opzioni);
                            System.out.println();
                        } else {
                            opzioni[x][y] = 'x';
                            check = false;
                            stampaMatrice(opzioni);
                            System.out.println();
                        }
                        if(trisColonnaX(opzioni, y) || trisRigaX(opzioni, x)
                        || trisRigaO(opzioni, x) || trisColonnaO(opzioni, y)
                        || trisDiagonale(opzioni) || trisDiagonaleSecondaria(opzioni)){
                            return opzioni;
                        }
                    }
                }
            }

        }
        return opzioni;
    }

    public static boolean trisColonnaX(char[][] matrice, int y) {
                boolean tris = false;
        for (int i = 0; i < matrice[0].length; i++) {
            if(matrice[i][y] == 'x'){
                tris = true;
            }else{
                return false;
            }
        }

      return tris;
   }
    public static boolean trisColonnaO(char[][] matrice, int y) {
        boolean tris = false;
        for (int i = 0; i < matrice[0].length; i++) {
            if(matrice[i][y] == 'o'){
                tris = true;
            }else{
                return false;
            }
        }

        return tris;
    }


    public static boolean trisRigaX(char[][] matrice, int x) {
        boolean tris = false;
        for (int i = 0; i < matrice[0].length; i++) {
            if(matrice[x][i] == 'x'){
                tris = true;
            }else{
                return false;
            }
        }

        return tris;
    }
    public static boolean trisRigaO(char[][] matrice, int x) {
        boolean tris = false;
        for (int i = 0; i < matrice[0].length; i++) {
            if(matrice[x][i] == 'o'){
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


    public static void stampaMatrice(char[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }
}
