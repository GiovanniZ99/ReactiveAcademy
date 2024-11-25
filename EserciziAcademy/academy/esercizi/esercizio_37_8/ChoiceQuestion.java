package academy.esercizi.esercizio_37_8;

public class ChoiceQuestion extends Question<String> {
    @Override
    public boolean checkAnswer(String answer) {
        return false;
    }

    @Override
    public void display() {
        System.out.println(this.getText());
    }
}
