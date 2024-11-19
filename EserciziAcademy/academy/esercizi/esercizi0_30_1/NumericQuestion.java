package academy.esercizi.esercizi0_30_1;

import java.util.InputMismatchException;

public class NumericQuestion extends Question{
    @Override
    public boolean checkAnswer(String answer) {
        int risposta;

            try {
                risposta = Integer.parseInt(answer);

            } catch (NumberFormatException e) {
                System.out.println("Le risposte non sono numeri");
                return false;
            }
        return risposta == Integer.parseInt(this.getAnswer());
    }

    @Override
    public void display() {
        System.out.println(this.getText());
    }
}
