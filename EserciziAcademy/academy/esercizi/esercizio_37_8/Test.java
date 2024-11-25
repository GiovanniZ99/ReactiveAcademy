package academy.esercizi.esercizio_37_8;

import academy.esercizi.esercizi0_30_1.FillinQuestion;
import academy.esercizi.esercizi0_30_1.FreeResponse;
import academy.esercizi.esercizi0_30_1.NumericQuestion;
import academy.esercizi.esercizi0_30_1.Question;

/**/
public class Test {
    public static void main(String[] args) {
        academy.esercizi.esercizi0_30_1.Question q = new FillinQuestion();
        q.setAnswer("La capitale dell’Italia è Roma, si trova nella regione Lazio ed ha 2800000 abitanti");
        q.setText("La capitale dell’Italia è ****, si trova nella regione **** ed ha **** abitanti");
        q.display();
        System.out.println(q.checkAnswer("Roma,Lazio,280000"));

        academy.esercizi.esercizi0_30_1.Question q2 = new NumericQuestion();
        q2.setAnswer("25");
        q2.setText("Quanto fa 5 + 20?");
        q2.display();

        System.out.println(q2.checkAnswer("25"));
        System.out.println(q2.checkAnswer("abc"));


        Question q3 = new FreeResponse();
        q3.setAnswer("Roma");
        q3.setText("Qual è la capitale dell'Italia?");

        q3.display();

        System.out.println(q3.checkAnswer("Roma"));
        System.out.println(q3.checkAnswer("Milano"));
    }
}
