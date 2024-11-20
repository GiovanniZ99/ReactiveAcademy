package academy.esercizi.esercizio_31_2;

public class Test {
    public static void main(String[] args) {

        ChoiceQuestion choiceQuestion = new ChoiceQuestion();
        choiceQuestion.setText("Qual è la capitale d'Italia?");
        choiceQuestion.setChoice("Roma", 0, true);  // risposta corretta
        choiceQuestion.setChoice("Milano", 1, false);
        choiceQuestion.setChoice("Napoli", 2, false);


        MultipleChoiceQuestion multiChoiceQuestion = new MultipleChoiceQuestion();
        multiChoiceQuestion.setText("Quali sono i colori primari");
        multiChoiceQuestion.setChoice("Rosso", 0, true);
        multiChoiceQuestion.setChoice("Verde", 1, false);
        multiChoiceQuestion.setChoice("Blu", 2, true);

        choiceQuestion.display();
        System.out.println("Risposta corretta? " + choiceQuestion.checkAnswer("1"));

        multiChoiceQuestion.display();
        System.out.println("Risposta corretta? " + multiChoiceQuestion.checkAnswer("1, 3"));
    }
}