package academy.esercizi.esercizio_31_2;

public class ChoiceQuestion extends Question {
    private String[] choices = new String[3];
    private int positionCorrect;


    public void setChoice(String answer, int position, boolean isCorrect) {
        if (position >= 0 && position < choices.length) {
            choices[position] = answer;
            if (isCorrect) {
                positionCorrect = position;
            }
        }
    }

    @Override
    public void display() {
        System.out.println(this.getText());
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ": " + choices[i]);
        }
    }

    @Override
    public boolean checkAnswer(String answer) {
        try {
            int answerIndex = Integer.parseInt(answer) - 1;
            return answerIndex == positionCorrect;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
