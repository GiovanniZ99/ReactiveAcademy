package academy.esercizi.esercizio_31_2;

public abstract class Question {
    private String text;
    private String answer;

    public abstract boolean checkAnswer(String answer);

    public abstract void display();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
