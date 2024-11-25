package academy.esercizi.esercizio_37_8;

public abstract class Question<E> {
    private E text;
    private E answer;


    public abstract boolean checkAnswer(E answer);

    public abstract void display();


    public E getText() {
        return text;
    }

    public void setText(E text) {
        this.text = text;
    }

    public E getAnswer() {
        return answer;
    }

    public void setAnswer(E answer) {
        this.answer = answer;
    }
}
