package academy.esercizi.esercizio_31_2;

public class MultipleChoiceQuestion extends ChoiceQuestion{
    private String[] choices = new String[3];
    private int[] positionCorrect;

    public void setChoice(String answer, int position, boolean isCorrect) {
        if (position >= 0 && position < choices.length) {
            choices[position] = answer;
            if (isCorrect) {
                for (int i = 0; i < positionCorrect.length; i++) {
                    if (positionCorrect[i] == 0) {
                        positionCorrect[i] = position;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void display() {
        super.display();
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ": " + choices[i]);
        }
    }

    @Override
    public boolean checkAnswer(String answer) {
        String[] answers = answer.split(",");
        for (String ans : answers) {
            try {
                int answerIndex = Integer.parseInt(ans.trim()) - 1;
                boolean isValid = false;
                for (int correctIndex : positionCorrect) {
                    if (answerIndex == correctIndex) {
                        isValid = true;
                        break;
                    }
                }
                if (!isValid) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

}
