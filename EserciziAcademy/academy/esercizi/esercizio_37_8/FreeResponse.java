package academy.esercizi.esercizio_37_8;

public class FreeResponse extends Question<String>{
    @Override
    public boolean checkAnswer(String answer) {
        return this.getAnswer().equals(answer);
    }

    @Override
    public void display() {

    }
}
