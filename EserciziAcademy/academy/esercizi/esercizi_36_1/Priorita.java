package academy.esercizi.esercizi_36_1;

public class Priorita {

    public void confrontaImpegni(Agenda ag1, Agenda ag2) {
        int risultato = ag1.getImpegno().compareTo(ag2.getImpegno());

        if (risultato < 0) {
            System.out.printf("Impegno di %s (giorno: %s) è più interessante di %s (giorno: %s)\n",
                    ag1.getImpegno(), ag1.giornoDellaSettimana, ag2.getImpegno(), ag2.giornoDellaSettimana);
        } else if (risultato > 0) {
            System.out.printf("Impegno di %s (giorno: %s) è più interessante di %s (giorno: %s)\n",
                    ag2.getImpegno(), ag2.giornoDellaSettimana, ag1.getImpegno(), ag1.giornoDellaSettimana);
        } else {
            System.out.printf("Gli impegni di %s (giorno: %s) e %s (giorno: %s) sono uguali.\n",
                    ag1.getImpegno(), ag1.giornoDellaSettimana, ag2.getImpegno(), ag2.giornoDellaSettimana);
        }
    }
}

