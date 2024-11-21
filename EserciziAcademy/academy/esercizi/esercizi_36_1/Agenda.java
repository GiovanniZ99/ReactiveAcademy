package academy.esercizi.esercizi_36_1;

public class Agenda implements Comparable<Agenda> {

     enum Giorni {
        LUNEDI,
        MARTEDI,
        MERCOLEDI,
        GIOVEDI,
        VENERDI,
        SABATO,
        DOMENICA;
    }

    enum Impegni{
         DIVANO,
        CALCETTO,
        TEATRO;
    }

    public Giorni giornoDellaSettimana;
    private String impegno;


    public Agenda(Impegni impegno) {
        this.impegno = String.valueOf(impegno);
    }

    public Agenda(Giorni giorniDellaSettimana, String impegno) {
        this.giornoDellaSettimana = giorniDellaSettimana;
        this.impegno = impegno;
    }

    public String getImpegno() {
        return impegno;
    }

    public void setImpegno(String impegno) {
        this.impegno = impegno;
    }

    @Override
    public int compareTo(Agenda o) {
        return this.giornoDellaSettimana.compareTo(o.giornoDellaSettimana);
    }
}
