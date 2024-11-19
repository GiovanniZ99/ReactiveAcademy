package academy.esercizi.esercizi0_30_1;

public class FreeResponse extends Question{
    @Override
    public boolean checkAnswer(String answer) {
        return this.getAnswer().equals(answer);
    }

    @Override
    public void display() {

    }
}
