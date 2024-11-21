package academy.esercizi.esercizi_36_1;

public class Test {
    public static void main(String[] args) {
        Agenda ag1=new Agenda(Agenda.Giorni.MARTEDI, "teatro");
        Agenda ag2=new Agenda(Agenda.Giorni.LUNEDI, "calcetto");
        Agenda ag3=new Agenda(Agenda.Giorni.DOMENICA, "divano");
        Agenda ag4=new Agenda(Agenda.Giorni.LUNEDI, "teatro");

        System.out.printf("%s %s %d\n",ag1.giornoDellaSettimana,ag2.giornoDellaSettimana, ag1.compareTo(ag2));
        System.out.printf("%s %s %d\n",ag1.giornoDellaSettimana,ag3.giornoDellaSettimana,   ag1.compareTo(ag3) );
        System.out.printf("%s %s %d\n",ag2.giornoDellaSettimana,ag3.giornoDellaSettimana, ag2.compareTo(ag3));
        System.out.printf("%s %s %d\n",ag1.giornoDellaSettimana,ag4.giornoDellaSettimana, ag1.compareTo(ag4));
        System.out.printf("%s %s %d\n",ag2.giornoDellaSettimana,ag4.giornoDellaSettimana,  ag2.compareTo(ag4));

        Agenda ag5 = new Agenda(Agenda.Impegni.DIVANO);
        Agenda ag6 = new Agenda(Agenda.Impegni.TEATRO);
        Priorita pr = new Priorita();
        pr.confrontaImpegni(ag5, ag6);
    }
}
