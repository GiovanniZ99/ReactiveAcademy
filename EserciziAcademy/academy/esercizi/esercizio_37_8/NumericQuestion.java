package academy.esercizi.esercizio_37_8;

public class NumericQuestion  extends Question<Integer>{
    @Override
    public boolean checkAnswer(Integer answer) {
        int risposta;

        try {
            risposta = answer;

        } catch (NumberFormatException e) {
            System.out.println("Le risposte non sono numeri");
            return false;
        }
        return risposta == this.getAnswer();
    }

    @Override
    public void display() {
        System.out.println(this.getText());
    }
}
