package academy.esercizi.esercizio_37_8;

/*Modificare il progetto per gestire i quiz ed implementare le classi con l’uso dei generics per
gestire le differenze tra risposta salvata in un numero, in una lista o come String.
Per le specifiche complete è possibile guardare anche il file «EsercizioQuestionari.docx»*/
public class Test {
    public static void main(String[] args) {
        Question <String> q = new FillinQuestion();
        q.setAnswer("La capitale dell’Italia è Roma, si trova nella regione Lazio ed ha 2800000 abitanti");
        q.setText("La capitale dell’Italia è ****, si trova nella regione **** ed ha **** abitanti");
        q.display();
        System.out.println(q.checkAnswer("Roma,Lazio,280000"));

     Question <Integer> q2 = new NumericQuestion();
        q2.setAnswer(25);
        q2.setText(Integer.valueOf("Quanto fa 5 + 20?"));
        q2.display();

        System.out.println(q2.checkAnswer(25));

        Question <String>q3 = new FreeResponse();
        q3.setAnswer("Roma");
        q3.setText("Qual è la capitale dell'Italia?");

        q3.display();

        System.out.println(q3.checkAnswer("Roma"));
        System.out.println(q3.checkAnswer("Milano"));
    }
}
