package academy.esercizi.esercizi0_30_1;

public class ChioceQuestion extends Question {
    @Override
    public boolean checkAnswer(String answer) {
        return false;
    }

    @Override
    public void display() {
        System.out.println(this.getText());
    }
}
